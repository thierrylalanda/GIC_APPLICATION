package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.EcartinventaireMS;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.EcartinventaireMSFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MisaJourProduits", urlPatterns = {"/MisaJourProduits"})
public class MisaJourProduits extends HttpServlet {

    @EJB
    private EcartinventaireMSFacadeLocal ecartinventaireMSFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
         HttpSession session = request.getSession();
        if (session.getAttribute("id") != null) {
           
            int idMS = (int) session.getAttribute("id_magasin");
            String code = request.getParameter("code");

            String quantite = request.getParameter("qte");

            List<StockproduitMS> sms = stockproduitMSFacade.ProduitCritique(idMS);
                List<StockproduitMS> smss = stockproduitMSFacade.ProduitWarmin(idMS);
                if (!sms.isEmpty()) {
                    String etat = "danger";
                    request.setAttribute("etat", etat);
                    session.removeAttribute("tail");
                    session.setAttribute("tail", sms.size());
                }
                if (!smss.isEmpty()) {
                    String etat1 = "warning";
                    request.setAttribute("etat1", etat1);
                    session.removeAttribute("taill");
                    session.setAttribute("taill", smss.size());
                }
            
            if (!code.isEmpty() && !quantite.isEmpty()) {
                String ee = new String();
                ee = String.valueOf(quantite);
                int somm = Integer.valueOf(ee).intValue();

                StockproduitMS stockproduitMS = stockproduitMSFacade.findByCodeProduitByidMS(idMS, code);

                int quant = stockproduitMS.getQuantite();

                stockproduitMS.setQuantite(somm);
                stockproduitMS.setPrixTotal(stockproduitMS.getPrixUnitaire() * somm);

                EcartinventaireMS ecartinventaireMS = new EcartinventaireMS();
                ecartinventaireMS.setCategorie(stockproduitMS.getCategorie().getNomCategorie());
                ecartinventaireMS.setCodeProduit(code);
                ecartinventaireMS.setDesignation(stockproduitMS.getDesignation());
                ecartinventaireMS.setDate(new date_du_jour().dateJour());
                ecartinventaireMS.setEcatQuantite(quant - somm);
                ecartinventaireMS.setLastQuantite(quant);
                ecartinventaireMS.setNewQuantite(somm);
                ecartinventaireMS.setPrixUnitaire(stockproduitMS.getPrixUnitaire());
                ecartinventaireMS.setPrixTotal(ecartinventaireMS.getEcatQuantite() * ecartinventaireMS.getPrixUnitaire());
                ecartinventaireMS.setIdMS(new MagasinSecondaire(idMS));

                stockproduitMSFacade.edit(stockproduitMS);
                ecartinventaireMSFacade.create(ecartinventaireMS);

                response.setContentType("application/text");
                response.getWriter().print(quantite);

            } else {
                Message message = new Message("une Erreur est survenue");
                request.setAttribute("message", message);
                //request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

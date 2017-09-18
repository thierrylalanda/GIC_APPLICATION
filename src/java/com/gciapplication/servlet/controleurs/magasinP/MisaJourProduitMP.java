/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.entity.EcartinventaireMP;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.session.entity.EcartinventaireMPFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author messi
 */
@WebServlet(name = "MisaJourProduitMP", urlPatterns = {"/MisaJourProduitMP"})
public class MisaJourProduitMP extends HttpServlet {

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private EcartinventaireMPFacadeLocal ecartinventaireMPFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    // List<CategorieproduitMP> list = new ArrayList<>();
    List<EcartinventaireMP> listEcart = new ArrayList<>();

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

            int idMP, niveau = 0;
            String redirect;
            if (session.getAttribute("id_magasinP") == null) {
                idMP = Integer.parseInt(request.getParameter("id_magasinP"));

            } else {
                idMP = (int) session.getAttribute("id_magasinP");
            }
            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            if (niveau == 5) {
                MagasinPrincipal principal = magasinPrincipalFacade.find(idMP);
                request.setAttribute("magasin", principal);
                request.setAttribute("magasinP", principal);

                redirect = "/WEB-INF/administrateur/main1.jsp";

            } else {
                redirect = "/WEB-INF/magasignierP/main.jsp";

            }

            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
//afficher tous le stock du MP
            if (action.equalsIgnoreCase("all")) {
                MagasinPrincipal mp = magasinPrincipalFacade.find(idMP);
                // list = mp.getCategorieproduitMPList();
                List<StockproduitMP> l = stockproduitMPFacade.findByidMP(idMP);
                request.setAttribute("listeStockMP", l);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                //enregistrer la mis ajour
            } else if (action.equalsIgnoreCase("update")) {

                String code = request.getParameter("code");
                String quantite = request.getParameter("qte");

                if (!code.isEmpty() && !quantite.isEmpty()) {
                    String ee = new String();
                    ee = String.valueOf(quantite);
                    int somm = Integer.valueOf(ee).intValue();

                    StockproduitMP stockproduitMP = stockproduitMPFacade.findByCodeProduitByidMP(idMP, code);

                    int quant = stockproduitMP.getQuantite();

                    stockproduitMP.setQuantite(somm);
                    stockproduitMP.setPrixTotal(stockproduitMP.getPrixUnitaire() * somm);
//on calcul l'ecart observer lors de la mis ajour du Stock 
                    EcartinventaireMP ecartinventaireMP = new EcartinventaireMP();
                    ecartinventaireMP.setCategorie(stockproduitMP.getCategorie().getNomCategorie());
                    ecartinventaireMP.setCodeProduit(code);
                    ecartinventaireMP.setDesignation(stockproduitMP.getDesignation());
                    ecartinventaireMP.setDate(date_du_jour.dateJour());
                    ecartinventaireMP.setEcatQuantite(quant - somm);
                    ecartinventaireMP.setLastQuantite(quant);
                    ecartinventaireMP.setNewQuantite(somm);
                    ecartinventaireMP.setPrixUnitaire(stockproduitMP.getPrixUnitaire());
                    ecartinventaireMP.setPrixTotal(ecartinventaireMP.getEcatQuantite() * ecartinventaireMP.getPrixUnitaire());
                    ecartinventaireMP.setIdMP(new MagasinPrincipal(idMP));

                    stockproduitMPFacade.edit(stockproduitMP);
                    ecartinventaireMPFacade.create(ecartinventaireMP);
                    
                    critiqueMP(request, session, idMP);
                    
                    response.setContentType("application/text");
                    response.getWriter().print(quantite);

                } else {
                    Message message = new Message("une Erreur est survenue");
                    request.setAttribute("message", message);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
            } else if (action.equalsIgnoreCase("historique")) {
                listEcart.clear();
                listEcart = ecartinventaireMPFacade.findAllById(idMP);

                request.setAttribute("InventaireMP", "OK");
                request.setAttribute("listEcartMP", listEcart);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("periode")) {
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                // date_du_jour date = new date_du_jour();
                Date d = date_du_jour.dateConvert(date1);
                Date dd = date_du_jour.dateConvert(date2);
                listEcart.removeAll(listEcart);
                listEcart = ecartinventaireMPFacade.findByPeriode(d, dd, idMP);

                request.setAttribute("listEcartMP", listEcart);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    void critiqueMP(HttpServletRequest request, HttpSession session, int idMP) {
        List<StockproduitMP> smp = stockproduitMPFacade.ProduitCritique(idMP);
        List<StockproduitMP> smpp = stockproduitMPFacade.ProduitWarmin(idMP);
        if (!smp.isEmpty()) {

            request.setAttribute("etat", "danger");
            session.setAttribute("tail", smp.size());
            session.setAttribute("etat", "danger");
        }
        if (!smpp.isEmpty()) {

            request.setAttribute("etat1", "warning");
            session.setAttribute("taill", smpp.size());
            session.setAttribute("etat1", "warning");
        }
    }

}

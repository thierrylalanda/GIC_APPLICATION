package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.entity.Bordereau;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.CommandeMP;
import com.gciapplication.entity.Founisseur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.session.entity.BordereauFacadeLocal;
import com.gciapplication.session.entity.CommandeMPFacadeLocal;
import com.gciapplication.session.entity.FounisseurFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
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
@WebServlet(name = "bordereau", urlPatterns = {"/bordereau"})
public class bordereauMP extends HttpServlet {

    @EJB
    private CommandeMPFacadeLocal commandeMPFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private BordereauFacadeLocal bordereauFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private FounisseurFacadeLocal founisseurFacade;

    List<Bordereau> lists = new ArrayList<>();
    List<Founisseur> founisseur;
    List<CategorieproduitMP> categorieMP;
    List<CommandeMP> list1;

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

            int idMP = (int) session.getAttribute("id_magasinP");
            Personnel personnel = (Personnel) session.getAttribute("personnel");
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            List<CommandeMP> list3 = commandeMPFacade.findByIdMPAndLivraison(idMP, "OK");
            request.setAttribute("listCommandeOK", list3);
            founisseur = founisseurFacade.findAll();
            request.setAttribute("founisseurs", founisseur);
            categorieMP = (List<CategorieproduitMP>) session.getAttribute("categorieMP");

            if (action.equalsIgnoreCase("receptionCommandeFournisseur")) {
                int idfournisseur = Integer.parseInt(request.getParameter("fournisseur"));
                Founisseur fournisseur = founisseurFacade.find(idfournisseur);
                list1 = commandeMPFacade.findByIdMPAndIdFournisseur(fournisseur.getIdFounisseur(), idMP, "encour");
                List<CommandeMP> list2 = commandeMPFacade.findByIdMPAndIdFournisseur(fournisseur.getIdFounisseur(), idMP, "incomplet");
                for (CommandeMP commandeMP : list2) {
                    list1.add(commandeMP);
                }
                list2.clear();

                request.setAttribute("listCommande", list1);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("receptionPartielCommandeFournisseur")) {
                int idcommande = Integer.parseInt(request.getParameter("commande"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                CommandeMP cmp = commandeMPFacade.find(idcommande);
                cmp.setLivraison("incomplet");
                cmp.setDerniereLivraison(date_du_jour.dateJour());
                cmp.setQuantiteCommande(cmp.getQuantiteCommande() - quantite);
                StockproduitMP smp = stockproduitMPFacade.findByCodeProduitByidMP(idMP, cmp.getCodeProduit());
                smp.setQuantite(quantite + smp.getQuantite());
                stockproduitMPFacade.edit(smp);
                commandeMPFacade.edit(cmp);

                Bordereau bordereau = new Bordereau();
                bordereau.setDate(date_du_jour.dateJour());
                bordereau.setDesignation(cmp.getDesignation());
                bordereau.setQuantite(cmp.getQuantiteCommande());
                bordereau.setTypeProduit(smp.getCategorie().getNomCategorie());
                bordereau.setPrixUnitaire(cmp.getPrixUnitaire());
                bordereau.setCodeProduit(cmp.getCodeProduit());
                bordereau.setPrixTotal(bordereau.getPrixUnitaire() * bordereau.getQuantite());
                bordereau.setIdFounisseur(cmp.getIdSA());
                bordereau.setMagasin(cmp.getIdMP());
                bordereau.setVisaMagasigner("OK");
                bordereau.setQuantiteInit(smp.getQuantite());
                bordereau.setQuantiteApres(smp.getQuantite() + cmp.getQuantiteCommande());
                bordereau.setRecepteur(personnel.getNomPrenom());
                bordereauFacade.create(bordereau);

                list1.remove(cmp);
                critiqueMP(request, session, idMP);

                response.setContentType("application/text");
                response.getWriter().print(String.valueOf(cmp.getQuantiteCommande()));

            } else if (action.equalsIgnoreCase("receptionCompletCommandeFournisseur")) {
                for (CommandeMP com : list1) {
                    com.setLivraison("OK");
                    com.setDerniereLivraison(date_du_jour.dateJour());
                    commandeMPFacade.edit(com);
                    StockproduitMP smp = stockproduitMPFacade.findByCodeProduitByidMP(idMP, com.getCodeProduit());
                    smp.setQuantite(com.getQuantiteCommande() + smp.getQuantite());
                    stockproduitMPFacade.edit(smp);

                    Bordereau bordereau = new Bordereau();
                    bordereau.setDate(date_du_jour.dateJour());
                    bordereau.setDesignation(com.getDesignation());
                    bordereau.setQuantite(com.getQuantiteCommande());
                    bordereau.setTypeProduit(smp.getCategorie().getNomCategorie());
                    bordereau.setPrixUnitaire(com.getPrixUnitaire());
                    bordereau.setCodeProduit(com.getCodeProduit());
                    bordereau.setPrixTotal(bordereau.getPrixUnitaire() * bordereau.getQuantite());
                    bordereau.setIdFounisseur(com.getIdSA());
                    bordereau.setMagasin(com.getIdMP());
                    bordereau.setVisaMagasigner("OK");
                    bordereau.setQuantiteInit(smp.getQuantite());
                    bordereau.setQuantiteApres(smp.getQuantite() + com.getQuantiteCommande());
                    bordereau.setRecepteur(personnel.getNomPrenom());
                    bordereauFacade.create(bordereau);

                }
                list1.clear();
                critiqueMP(request, session, idMP);

                Message message = new Message("Commandes Complètement Receptionnées Avec Succès");
                request.setAttribute("message", message);

                request.setAttribute("listCommande", list1);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
            } else {
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
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

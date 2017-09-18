/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.entity.Bordereau;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.MouvementProduits;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.TransfertStock;
import com.gciapplication.session.entity.BordereauFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MouvementProduitsFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
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
@WebServlet(name = "ListeProduitMP", urlPatterns = {"/ListeProduitMP"})
public class ListeProduitMP extends HttpServlet {

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private MouvementProduitsFacadeLocal mouvementProduitsFacade;
    @EJB
    private BordereauFacadeLocal bordereauFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    List<CategorieproduitMP> categorie;
    List<TransfertStock> lists = new ArrayList<>();
    List<Bordereau> bordereaus = new ArrayList<>();
    List<MouvementProduits> listMouvement = new ArrayList<>();
    List<String> name = new ArrayList<>();
    List<OperationConsommateur> listOP = new ArrayList<>();

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
            String vue = request.getParameter("vue");
            String action = request.getParameter("action");

            int idMP;
            String redirect;
            if (session.getAttribute("id_magasinP") == null) {
                idMP = Integer.parseInt(request.getParameter("id_magasinP"));

            } else {
                idMP = (int) session.getAttribute("id_magasinP");
            }

            MagasinPrincipal principal = magasinPrincipalFacade.find(idMP);
            request.setAttribute("magasin", principal);
            request.setAttribute("magasinP", principal);

            int niveau = 0;

            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            switch (niveau) {
                case 5:
                    //lists.clear();
                    listOP.clear();
                    List<OperationConsommateur> o = operationConsommateurFacade.findAllByIdMP(idMP);
                    listOP = operationConsommateurFacade.findAllByIdMP(idMP);
                    //List<Bordereau> bs = bordereauFacade.
                    List<TransfertStock> lis = transfertStockFacade.findAllByIdMP(idMP);
                    for (OperationConsommateur op : o) {
                        TransfertStock ts = new TransfertStock();
                        ts.setCodeProduit(op.getCodeProduit());
                        ts.setDate(op.getDate());
                        ts.setDesignation(op.getDesignation());
                        ts.setQuantite(op.getQuantite());
                        ts.setPrixTotal(op.getPrixTotal());
                        ts.setPrixUnitaire(op.getPrix());
                        ts.setTypeProduit(op.getCategorie());
                        ts.setMs(new MagasinSecondaire(1, "rien", op.getPersonnel().getNomPrenom()));
                        lis.add(ts);
                    }
                    name = transfertStockFacade.findAllDinstinctByIdNameMS(idMP);
                    request.setAttribute("operations", lis);
                    request.setAttribute("MS", name);
                    redirect = "/WEB-INF/administrateur/main1.jsp";
                    break;
                case 4:
                    request.setAttribute("idMP", Integer.parseInt(request.getParameter("id_magasinP")));
                    System.out.println("ces bien");
                    if (request.getParameter("etat") != null) {
                        request.setAttribute("etat", "OK");
                    } else if (request.getParameter("mouv") != null) {
                        request.setAttribute("historique", "OK");
                    } else if (request.getParameter("stat") != null) {
                        request.setAttribute("state", "OK");
                        request.setAttribute("statis", "OK");
                    } else {
                        request.setAttribute("categorie", principal.getCategorieproduitMPList());
                        request.setAttribute("stock", "OK");
                    }
                    redirect = "/WEB-INF/controleurs/main.jsp";

                    break;
                default:
                    redirect = "/WEB-INF/magasignierP/main.jsp";
                    break;
            }

            //je recherche le magsinier Principal 
            //il faudrat mettre une condition pour eviter les aller et retour dans la BD
            if (action.equalsIgnoreCase("statistiqueMP")) {
                request.setAttribute("StatistiqueMP", "OK");
                request.setAttribute("for", "MP");
                request.setAttribute("vue", vue);

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            }else if (action.equalsIgnoreCase("rapport")) {
                request.removeAttribute("stock");
                request.setAttribute("rapportmp", "ok");
               // mp = magasinPrincipalFacade.find(Integer.parseInt(request.getParameter("id_magasinP")));
                request.setAttribute("categories", principal.getCategorieproduitMPList());
                
                request.setAttribute("vue", vue);

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("seeInfoMP")) {
                request.setAttribute("info", "OK");
                request.setAttribute("sorti", "OK");
                request.setAttribute("vue", vue);
                
                critiqueMP(request, session, idMP);
                
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("all")) {
                List<StockproduitMP> stockMP = stockproduitMPFacade.findByidMP(idMP);
                //categorie = principal.getCategorieproduitMPList();
                request.setAttribute("stockMP", stockMP);

                Historique(idMP, request);
                List<StockproduitMP> stock = stockproduitMPFacade.ProduitCritique(idMP);
                request.setAttribute("stock", stock);
                request.setAttribute("active", "OK");
                request.setAttribute("all", "tous");
                request.setAttribute("actions", "all");
                request.setAttribute("sorti", "OK");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                //recherche priodique des transfert deja effectuer par le MP
            } else if (action.equalsIgnoreCase("date")) {
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                Date d = date_du_jour.dateConvert(date1);
                Date dd = date_du_jour.dateConvert(date2);
                double total = 0;
                lists.clear();
                lists = transfertStockFacade.findByPeriode(d, dd, idMP);
                for (int i = 0; i < lists.size(); i++) {
                    TransfertStock oc = lists.get(i);
                    total += oc.getPrixTotal();
                }
                request.setAttribute("sorti", "ONE");
                request.setAttribute("active", "OK");
                request.setAttribute("total", total);
                String actions = "periode";
                request.setAttribute("name", name);
                request.setAttribute("actions", actions);
                request.setAttribute("operation", lists);

                request.removeAttribute("operations");
                request.setAttribute("operations", lists);

                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
// pour rechercher le code d'un produit connaissant sa designation
            } else if (action.equalsIgnoreCase("code")) {
                String designation = request.getParameter("designation");
                StockproduitMP mS = stockproduitMPFacade.findByDesignationByidMP(idMP, designation);
                String code = mS.getCodeProduit();
                response.setContentType("application/text");
                response.getWriter().print(code);

            } else if (action.equalsIgnoreCase("alertProduit")) {
                session.removeAttribute("id_magasin");
                List<StockproduitMP> smp, smpp;
                smp = stockproduitMPFacade.ProduitCritique(idMP);
                smpp = stockproduitMPFacade.ProduitWarmin(idMP);

                System.out.println("OK");
                if (!smp.isEmpty()) {
                    String etat = "danger";
                    request.setAttribute("etat", etat);
                    session.setAttribute("tail", smp.size());
                    session.setAttribute("etat", etat);
                }
                if (!smpp.isEmpty()) {
                    String etat1 = "warning";
                    request.setAttribute("etat1", etat1);
                    session.setAttribute("taill", smpp.size());
                    session.setAttribute("etat1", etat1);
                }

                session.setAttribute("sms", smp);
                session.setAttribute("smss", smpp);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("One")) {

                String magasin = request.getParameter("magasinS");
                lists.clear();
                lists = transfertStockFacade.findAllByNameMs(magasin);

                request.removeAttribute("operations");
                request.setAttribute("operations", lists);

                request.setAttribute("operation", lists);
                request.setAttribute("name", name);
                request.setAttribute("sorti", "ONE");
                request.setAttribute("active", "OK");
                request.setAttribute("actions", "periode");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("one_produit")) {
                String designation = request.getParameter("designation");
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");

                if (!date1.isEmpty() && !date2.isEmpty()) {

                    Date d = date_du_jour.dateConvert(date1);
                    Date dd = date_du_jour.dateConvert(date2);

                    lists.clear();
                    listMouvement.clear();
                    bordereaus.clear();
                    listOP.clear();
                    List<OperationConsommateur> o = operationConsommateurFacade.findAllByIdMP(idMP);
                    listOP = operationConsommateurFacade.findAllByIdMP(idMP);
                    lists = transfertStockFacade.findByPeriodeMP(d, dd, idMP, designation);
                    for (TransfertStock transf : lists) {
                        List<MouvementProduits> listMouvementT = mouvementProduitsFacade.findAllByIdMagasin(idMP, transf.getIdTransfert());
                        for (MouvementProduits mouvementProduits : listMouvementT) {
                            listMouvement.add(mouvementProduits);
                        }

                    }

                    for (OperationConsommateur Op : listOP) {//il faut voir ca sa ne passe resultat incomplet
                        if (Op.getDate().after(d) && Op.getDate().before(dd) && Op.getDesignation().equalsIgnoreCase(designation)) {
                            MouvementProduits mp = new MouvementProduits();
                            TransfertStock ts = new TransfertStock();
                            MagasinPrincipal mp1 = new MagasinPrincipal();
                            mp.setQuantiteInit(Op.getStock());
                            mp.setQuantiteApres(Op.getStockApres());
                            mp.setOperateur(Op.getOperateur());
                            mp.setDateOperation(Op.getDate());
                            ts.setCodeProduit(Op.getCodeProduit());
                            ts.setDesignation(Op.getDesignation());
                            ts.setTypeProduit(Op.getCategorie());
                            ts.setQuantite(Op.getQuantite());
                            mp1.setNomMagasin(Op.getMagasinP().getNomMagasin());
                            ts.setMp(mp1);
                            mp.setIdTransfert(ts);
                            listMouvement.add(mp);

                        }
                    }
                    bordereaus = bordereauFacade.findByPeriodeMP(d, dd, idMP, designation);

                } else {

                    lists.removeAll(lists);
                    listMouvement.removeAll(listMouvement);
                    bordereaus.removeAll(bordereaus);
                    lists = transfertStockFacade.findByDesignationMP(designation, idMP);
                    for (TransfertStock transf : lists) {
                        List<MouvementProduits> listMouvementT = mouvementProduitsFacade.findAllByIdMagasin(idMP, transf.getIdTransfert());
                        for (MouvementProduits mouvementProduits : listMouvementT) {
                            listMouvement.add(mouvementProduits);
                        }

                    }

                    for (OperationConsommateur Op : listOP) {
                        if (Op.getDesignation().equalsIgnoreCase(designation)) {

                            MouvementProduits mp = new MouvementProduits();
                            TransfertStock ts = new TransfertStock();
                            MagasinPrincipal mp1 = new MagasinPrincipal();
                            mp.setQuantiteInit(Op.getStock());
                            mp.setQuantiteApres(Op.getStockApres());
                            mp.setOperateur(Op.getOperateur());
                            mp.setDateOperation(Op.getDate());
                            ts.setCodeProduit(Op.getCodeProduit());
                            ts.setDesignation(Op.getDesignation());
                            ts.setTypeProduit(Op.getCategorie());
                            ts.setQuantite(Op.getQuantite());
                            mp1.setNomMagasin(Op.getMagasinP().getNomMagasin());
                            ts.setMp(mp1);
                            mp.setIdTransfert(ts);
                            listMouvement.add(mp);
                        }
                    }
                    bordereaus = bordereauFacade.findByDesignationMP(designation, idMP);

                }
                if (request.getParameter("mouv") != null) {
                    request.setAttribute("mouvementP", "OK");
                    request.setAttribute("sortie", "OK");
                } else {
                    request.setAttribute("mouvementP", "OK");
                    request.setAttribute("sortie", "ON");
                }

                if (request.getParameter("viewMP") != null) {

                    request.setAttribute("Entrer", "yes");
                } else {

                    request.setAttribute("info", "periode");
                }

                request.setAttribute("designation", designation);
                request.setAttribute("operation", listMouvement);
                request.setAttribute("listetransfert", bordereaus);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("all_produit")) {
                Historique(idMP, request);

                if (request.getParameter("viewMP") != null) {

                    request.setAttribute("Entrer", "yes");
                } else {

                    request.setAttribute("info", "periode");
                }
                request.setAttribute("actions", "periode");
                //  request.setAttribute("state", "OK");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            } else {

                lists.clear();
                lists = transfertStockFacade.findAllByIdMP(idMP);
                name = transfertStockFacade.findAllDinstinctByIdNameMS(idMP);
                request.setAttribute("operation", lists);
                // request.setAttribute("operations", lists);
                request.setAttribute("name", name);
                request.setAttribute("sorti", "ONE");
                request.setAttribute("active", "OK");
                request.setAttribute("actions", "periode");
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
                    
                    
                    session.removeAttribute("critiques");
                    session.setAttribute("critiques", "yes");
                    session.removeAttribute("stockp");
                    session.setAttribute("stockp", smp);
                }
                if (!smpp.isEmpty()) {
                   
                    session.removeAttribute("warming");
                    session.setAttribute("warming", "yes");
                    session.removeAttribute("stockwp");
                    session.setAttribute("stockwp", smpp);
                }
    }

    protected void Historique(int idMP, HttpServletRequest request) {
        // lists.clear();
        //listMouvement.clear();
        //bordereaus.clear();
        List<MouvementProduits> listMouvements = new ArrayList<>();
        List<TransfertStock> listt = transfertStockFacade.findByVisaMPByidMP("OK", idMP);
        List<OperationConsommateur> listOPP = operationConsommateurFacade.findAllByIdMP(idMP);
        for (TransfertStock transf : listt) {

            List<MouvementProduits> listMouvementT = mouvementProduitsFacade.findAllByIdMagasin(idMP, transf.getIdTransfert());
            for (MouvementProduits mouvementProduits : listMouvementT) {
                listMouvements.add(mouvementProduits);
            }

            // listMouvement.add(mp);
        }

        for (OperationConsommateur Op : listOPP) {
            MouvementProduits mp = new MouvementProduits();
            TransfertStock ts = new TransfertStock();
            MagasinPrincipal mp1 = new MagasinPrincipal();
            mp.setQuantiteInit(Op.getStock());
            mp.setQuantiteApres(Op.getStockApres());
            mp.setOperateur(Op.getOperateur());
            mp.setDateOperation(Op.getDate());
            ts.setCodeProduit(Op.getCodeProduit());
            ts.setDesignation(Op.getDesignation());
            ts.setTypeProduit(Op.getCategorie());
            ts.setQuantite(Op.getQuantite());
            mp1.setNomMagasin(Op.getMagasinP().getNomMagasin());
            ts.setMp(mp1);
            mp.setIdTransfert(ts);
            listMouvements.add(mp);
        }
        MagasinPrincipal magasinp = magasinPrincipalFacade.find(idMP);
        List<Bordereau> bordereaux = bordereauFacade.findAllByIdMP(idMP);
        request.setAttribute("magasin", magasinp);
        request.setAttribute("operation", listMouvements);
        request.setAttribute("listetransfert", bordereaux);
        if (request.getParameter("mouv") != null) {
            request.setAttribute("mouvementP", "OK");
            request.setAttribute("sortie", "OK");
        } else {
            // request.setAttribute("mouvementP", "OK");
            request.setAttribute("sortie", "ON");
        }
    }
}
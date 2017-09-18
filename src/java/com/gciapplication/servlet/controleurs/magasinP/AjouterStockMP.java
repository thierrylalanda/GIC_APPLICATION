/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.session.entity.AffectationmagasinPFacadeLocal;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
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
@WebServlet(name = "AjouterStockMP", urlPatterns = {"/AjouterStockMP"})
public class AjouterStockMP extends HttpServlet {

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private AffectationmagasinPFacadeLocal affectationmagasinPFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    List<StockproduitMP> listStock = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("id") != null) {
            String vue = request.getParameter("vue");
            String action = request.getParameter("action");
            request.setAttribute("vue", vue);

            int idMP;
            String redirect;
            if (session.getAttribute("id_magasinP") == null) {
                idMP = Integer.parseInt(request.getParameter("id_magasinP"));

            } else {
                idMP = (int) session.getAttribute("id_magasinP");
            }

            int niveau = 0;

            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            if (niveau == 5) {
                List<MagasinPrincipal> mps = magasinPrincipalFacade.findAll();
                //List<AffectationmagasinP> MagasinsP = affectationmagasinPFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsP", mps);
                request.setAttribute("listMP", mps);
                request.setAttribute("isform", "yes");
                redirect = "/WEB-INF/administrateur/main1.jsp";
            } else {
                redirect = "/WEB-INF/magasignierP/main.jsp";
            }

            if (action.equalsIgnoreCase("formulaire")) {
                int idcategorie = Integer.parseInt(request.getParameter("categorie"));
                // String code = request.getParameter("code");
                int idarticle = Integer.parseInt(request.getParameter("designation"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                // int prix = Integer.parseInt(request.getParameter("pu"));
                String date = request.getParameter("date");
                Article art = articleFacade.find(idarticle);
                CategorieproduitMP catms = categorieproduitMPFacade.find(idcategorie);
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(catms.getNomCategorie());
                List<StockproduitMP> smp = stockproduitMPFacade.findByidMP(catms.getMagasinPrincipal().getIdMagasin());
                boolean repete = false;
                if (!smp.isEmpty()) {
                    for (StockproduitMP stockproduitMP : smp) {
                        if (stockproduitMP.getCodeProduit().equalsIgnoreCase(art.getCode())) {
                            repete = true;
                            break;
                        }
                    }
                }
                if (!repete) {

                    StockproduitMP p = new StockproduitMP();

                    p.setDesignation(art.getDesignation());
                    p.setCodeProduit(art.getCode());
                    p.setQuantite(quantite);
                    p.setCategorie(catms);
                    p.setPrixUnitaire(art.getPrix());
                    p.setPrixTotal(art.getPrix() * p.getQuantite());
                    p.setDateLivraison(date_du_jour.dateConvert(date));
                    p.setPrixTotal(p.getQuantite() * p.getPrixUnitaire());
                    stockproduitMPFacade.create(p);
                    listStock.add(p);
                    catms.setStockproduitMPList(listStock);
                    categorieproduitMPFacade.edit(catms);
                    critiqueMP(request, session, idMP);

                    // Message message = new Message("Produits Enregistrer Avec Succes");
                    request.setAttribute("magasin", catms.getMagasinPrincipal());
                    //request.setAttribute("message", message);
                    request.setAttribute("categos", catms);
                    request.setAttribute("categoriesMP", categorieproduitMPFacade.findCatByidMP(catms.getMagasinPrincipal().getIdMagasin()));
                    request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                    request.setAttribute("listeStock", listStock);
                    request.setAttribute("form", "OK");
                    request.setAttribute("forme", "ONE");
                    request.setAttribute("parametre", "OK");
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                } else {

                    Message message = new Message("Cet Article Exite Déjà Dans Ce Magasin");
                    request.setAttribute("infomessage", message.getMessage());
                    request.setAttribute("magasin", catms.getMagasinPrincipal());
                    //request.setAttribute("message", message);
                    request.setAttribute("categos", catms);
                    request.setAttribute("categoriesMP", categorieproduitMPFacade.findCatByidMP(catms.getMagasinPrincipal().getIdMagasin()));
                    request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                    request.setAttribute("listeStock", listStock);
                    request.setAttribute("form", "OK");
                    request.setAttribute("forme", "ONE");
                    request.setAttribute("parametre", "OK");
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
            } else if (action.equalsIgnoreCase("udatestockMP")) {
                int idstock = Integer.parseInt(request.getParameter("id_stock"));
                String desi = request.getParameter("designation");
                StockproduitMP sms = stockproduitMPFacade.find(idstock);
                
                CategorieproduitMP catms = categorieproduitMPFacade.find(sms.getCategorie().getIdCategorie());
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(catms.getNomCategorie());
                request.setAttribute("designation", articleFacade.findAllByDesignation(desi));
                request.setAttribute("stockup", sms);
                request.setAttribute("categos", catms);
                 request.setAttribute("magasin", catms.getMagasinPrincipal());
                request.setAttribute("categoriesMP", categorieproduitMPFacade.findCatByidMP(catms.getMagasinPrincipal().getIdMagasin()));
                request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                request.setAttribute("listeStock", listStock);
                request.setAttribute("form", "OK");
                request.setAttribute("forme", "ONE");
                request.setAttribute("updateStock", "yes");
                request.setAttribute("parametre", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("SaveudatestockMP")) {
                int idcategorie = Integer.parseInt(request.getParameter("categorie"));
                // String code = request.getParameter("code");
                int idarticle = Integer.parseInt(request.getParameter("designation"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                int idstock = Integer.parseInt(request.getParameter("id_stock"));
                String date = request.getParameter("date");
                Article art = articleFacade.find(idarticle);

                CategorieproduitMP catms = categorieproduitMPFacade.find(idcategorie);
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(catms.getNomCategorie());

                StockproduitMP p = stockproduitMPFacade.find(idstock);

                p.setDesignation(art.getDesignation());
                p.setCodeProduit(art.getCode());
                p.setQuantite(quantite);
                p.setCategorie(catms);
                p.setPrixUnitaire(art.getPrix());
                p.setPrixTotal(art.getPrix() * p.getQuantite());
                p.setDateLivraison(date_du_jour.dateConvert(date));
                p.setPrixTotal(p.getQuantite() * p.getPrixUnitaire());
                stockproduitMPFacade.edit(p);
                listStock.remove(p);
                listStock.add(p);

                request.setAttribute("magasin", catms.getMagasinPrincipal());
                request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                request.setAttribute("categos", catms);
                request.setAttribute("categoriesMP", categorieproduitMPFacade.findCatByidMP(catms.getMagasinPrincipal().getIdMagasin()));
                request.setAttribute("listeStock", listStock);
                request.setAttribute("form", "OK");
                request.setAttribute("forme", "ONE");
                request.setAttribute("parametre", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deleteArticleformulaire")) {
                int idstock = Integer.parseInt(request.getParameter("id_stock"));
                StockproduitMP sms = stockproduitMPFacade.find(idstock);
                stockproduitMPFacade.remove(sms);
                listStock.remove(sms);
                request.setAttribute("stockup", sms);
                request.setAttribute("categoriesMP", categorieproduitMPFacade.findCatByidMP(sms.getCategorie().getMagasinPrincipal().getIdMagasin()));
                request.setAttribute("magasin", sms.getCategorie().getMagasinPrincipal());
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(sms.getCategorie().getNomCategorie());
                request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                request.setAttribute("categos", sms.getCategorie());
                request.setAttribute("listeStock", listStock);
                request.setAttribute("form", "OK");
                request.setAttribute("forme", "ONE");
                request.setAttribute("parametre", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            } else {
                listStock.clear();
                Message message = new Message("Tous les Articles Ont étés Enregistrer Avec Succès");
                request.setAttribute("message2", message);
                request.setAttribute("form", "OK");
                request.setAttribute("forme", "ONE");
                request.setAttribute("parametre", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            }

        } else {
            response.sendRedirect("/login");
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

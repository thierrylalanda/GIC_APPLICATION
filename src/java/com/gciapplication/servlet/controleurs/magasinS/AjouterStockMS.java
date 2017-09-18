/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.AffectationmagasinSFacadeLocal;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
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
@WebServlet(name = "AjouterStockMS", urlPatterns = {"/AjouterStockMS"})
public class AjouterStockMS extends HttpServlet {

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private AffectationmagasinSFacadeLocal affectationmagasinSFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    List<StockproduitMS> listStock = new ArrayList<>();

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

            int idMS;
            String redirect;
            if (session.getAttribute("id_magasin") == null) {
                idMS = Integer.parseInt(request.getParameter("id_magasin"));

            } else {
                idMS = (int) session.getAttribute("id_magasin");
            }

            int niveau = 0;

            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            if (niveau == 5) {
                List<MagasinSecondaire> MagasinsS = magasinSecondaireFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("isform", "yes");
                request.setAttribute("MagasinsS", MagasinsS);
                redirect = "/WEB-INF/administrateur/main1.jsp";
            } else {
                redirect = "/WEB-INF/magasignerS/main.jsp";
            }

            if (action.equalsIgnoreCase("formulaire")) {
                int idcategorie = Integer.parseInt(request.getParameter("categorie"));
                // String code = request.getParameter("code");
                int idarticle = Integer.parseInt(request.getParameter("designation"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                // int prix = Integer.parseInt(request.getParameter("pu"));
                String date = request.getParameter("date");
                Article art = articleFacade.find(idarticle);

                CategorieproduitMS catms = categorieproduitMSFacade.find(idcategorie);
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(catms.getNomCategorie());
                List<StockproduitMS> smp = stockproduitMSFacade.findStockMP(catms.getMagasinSecondaire().getIdMagasin());
                boolean repete = false;
                if (!smp.isEmpty()) {
                    for (StockproduitMS stockproduitMP : smp) {
                        if (stockproduitMP.getCodeProduit().equalsIgnoreCase(art.getCode())) {
                            repete = true;
                            break;
                        }
                    }
                }
                if (!repete) {

                    StockproduitMS p = new StockproduitMS();

                    p.setDesignation(art.getDesignation());
                    p.setCodeProduit(art.getCode());
                    p.setQuantite(quantite);
                    p.setCategorie(catms);
                    p.setPrixUnitaire(art.getPrix());
                    p.setPrixTotal(art.getPrix() * p.getQuantite());
                    p.setDateLivraison(date_du_jour.dateConvert(date));
                    p.setPrixTotal(p.getQuantite() * p.getPrixUnitaire());
                    stockproduitMSFacade.create(p);
                    listStock.add(stockproduitMSFacade.findLastInsert());

                    catms.setStockproduitMSList(listStock);
                    categorieproduitMSFacade.edit(catms);
                    critique(request, session, idMS);

                    //request.setAttribute("categories", categorie);
                    request.setAttribute("magasin", catms.getMagasinSecondaire());
                    request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                    request.setAttribute("categos", catms);
                    request.setAttribute("categoriesMS", categorieproduitMSFacade.findCatByidMS(catms.getMagasinSecondaire().getIdMagasin()));
                    request.setAttribute("listeStock", listStock);
                    request.setAttribute("form", "OK");
                    request.setAttribute("forme", "ONE");
                    request.setAttribute("parametre", "OK");
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                } else {

                    Message message = new Message("Cet Article Exite Déjà Dans Ce Magasin");
                    request.setAttribute("infomessage", message.getMessage());
                    request.setAttribute("magasin", catms.getMagasinSecondaire());
                    request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                    request.setAttribute("categos", catms);
                    request.setAttribute("categoriesMS", categorieproduitMSFacade.findCatByidMS(catms.getMagasinSecondaire().getIdMagasin()));
                    request.setAttribute("listeStock", listStock);
                    request.setAttribute("form", "OK");
                    request.setAttribute("forme", "ONE");
                    request.setAttribute("parametre", "OK");
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
            } else if (action.equalsIgnoreCase("udatestockMS")) {
                int idstock = Integer.parseInt(request.getParameter("id_stock"));
                String desi = request.getParameter("designation");
                StockproduitMS sms = stockproduitMSFacade.find(idstock);

                request.setAttribute("designation", articleFacade.findAllByDesignation(desi));
                request.setAttribute("stockup", sms);
                request.setAttribute("categoriesMS", categorieproduitMSFacade.findCatByidMS(sms.getCategorie().getMagasinSecondaire().getIdMagasin()));
                request.setAttribute("magasin", sms.getCategorie().getMagasinSecondaire());
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(sms.getCategorie().getNomCategorie());
                request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                request.setAttribute("categos", sms.getCategorie());
                request.setAttribute("listeStock", listStock);
                request.setAttribute("form", "OK");
                request.setAttribute("forme", "ONE");
                request.setAttribute("updateStock", "yes");
                request.setAttribute("parametre", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("SaveudatestockMS")) {
                int idcategorie = Integer.parseInt(request.getParameter("categorie"));
                // String code = request.getParameter("code");
                int idarticle = Integer.parseInt(request.getParameter("designation"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                int idstock = Integer.parseInt(request.getParameter("id_stock"));
                String date = request.getParameter("date");
                System.out.println(date);
                Article art = articleFacade.find(idarticle);

                CategorieproduitMS catms = categorieproduitMSFacade.find(idcategorie);
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(catms.getNomCategorie());

                StockproduitMS p = stockproduitMSFacade.find(idstock);

                p.setDesignation(art.getDesignation());
                p.setCodeProduit(art.getCode());
                p.setQuantite(quantite);
                p.setCategorie(catms);
                p.setPrixUnitaire(art.getPrix());
                p.setPrixTotal(art.getPrix() * p.getQuantite());
                p.setDateLivraison(date_du_jour.dateConvert(date));
                p.setPrixTotal(p.getQuantite() * p.getPrixUnitaire());
                stockproduitMSFacade.edit(p);
                listStock.remove(p);
                listStock.add(p);

                request.setAttribute("magasin", catms.getMagasinSecondaire());
                request.setAttribute("articles", articleFacade.findAllByCategorieProduit(catp.getIdCategorieProduit()));
                request.setAttribute("categos", catms);
                request.setAttribute("categoriesMS", categorieproduitMSFacade.findCatByidMS(catms.getMagasinSecondaire().getIdMagasin()));
                request.setAttribute("listeStock", listStock);
                request.setAttribute("form", "OK");
                request.setAttribute("forme", "ONE");
                request.setAttribute("parametre", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deleteArticleformulaire")) {
                int idstock = Integer.parseInt(request.getParameter("id_stock"));
                StockproduitMS sms = stockproduitMSFacade.find(idstock);
                stockproduitMSFacade.remove(sms);
                listStock.remove(sms);
                request.setAttribute("stockup", sms);
                request.setAttribute("categoriesMS", categorieproduitMSFacade.findCatByidMS(sms.getCategorie().getMagasinSecondaire().getIdMagasin()));
                request.setAttribute("magasin", sms.getCategorie().getMagasinSecondaire());
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
                Message message = new Message("Tous les Articles Ont étés Enregistrers Avec Succès");
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

    void critique(HttpServletRequest request, HttpSession session, int idMS) {
        List<StockproduitMS> sms = stockproduitMSFacade.ProduitCritique(idMS);
        List<StockproduitMS> smss = stockproduitMSFacade.ProduitWarmin(idMS);
        if (!sms.isEmpty()) {

            request.setAttribute("etat", "danger");
            session.removeAttribute("tail");
            session.setAttribute("tail", sms.size());
        }
        if (!smss.isEmpty()) {

            request.setAttribute("etat1", "warning");
            session.removeAttribute("taill");
            session.setAttribute("taill", smss.size());
        }
    }
}

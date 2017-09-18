/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.Administrator;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.Service;
import com.gciapplication.entity.Site;
import com.gciapplication.entity.StatistiqueArticlesDirection;
import com.gciapplication.entity.StatistiqueArticlesMagasinMp;
import com.gciapplication.entity.StatistiqueArticlesMagasinMs;
import com.gciapplication.entity.StatistiqueArticlesPersonnel;
import com.gciapplication.entity.StatistiqueArticlesRegion;
import com.gciapplication.entity.StatistiqueArticlesService;
import com.gciapplication.entity.StatistiqueArticlesSite;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.DirectionFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.ServiceFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesDirectionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesMagasinMpFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesMagasinMsFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesPersonnelFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesRegionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesServiceFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesSiteFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author messi
 */
@WebServlet(name = "parametre", urlPatterns = {"/parametre"})
public class parametre extends HttpServlet {

    @EJB
    private StatistiqueArticlesMagasinMpFacadeLocal statistiqueArticlesMagasinMpFacade;

    @EJB
    private StatistiqueArticlesSiteFacadeLocal statistiqueArticlesSiteFacade;

    @EJB
    private StatistiqueArticlesServiceFacadeLocal statistiqueArticlesServiceFacade;

    @EJB
    private StatistiqueArticlesRegionFacadeLocal statistiqueArticlesRegionFacade;

    @EJB
    private StatistiqueArticlesPersonnelFacadeLocal statistiqueArticlesPersonnelFacade;

    @EJB
    private StatistiqueArticlesMagasinMsFacadeLocal statistiqueArticlesMagasinMsFacade;

    @EJB
    private StatistiqueArticlesDirectionFacadeLocal statistiqueArticlesDirectionFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private DirectionFacadeLocal directionFacade;

    @EJB
    private SiteFacadeLocal siteFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

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
            //Personnel p = (Personnel) session.getAttribute("personnel");
            int niveau = 0;
            String redirect;
            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            if (niveau == 4) {
                redirect = "/WEB-INF/controleurs/main.jsp";
            } else {
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("directions", directionFacade.findAll());
                request.setAttribute("services", serviceFacade.findAll());
                redirect = "/WEB-INF/administrateur/main1.jsp";
            }
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            request.setAttribute("vue", vue);
            List<Region> r = regionFacade.findAll();
            request.setAttribute("regions", r);
            request.setAttribute("categories", categorieProduitFacade.findAll());
            List<CategorieProduit> catProduit = categorieProduitFacade.findAll();
            request.setAttribute("type_categorie", catProduit);
            // les Reporting commence ici
            if (action.equalsIgnoreCase("getRepporting")) {

                String categorie = request.getParameter("categorie");
                String dates = request.getParameter("interval");
                String[] d = dates.split("-");
                String datedebut = d[0];
                String datefin = d[1];
                int idmag;
                Date d1 = date_du_jour.dateConvert(datedebut);
                Date d2 = date_du_jour.dateConvert(datefin);
                System.out.println(d1);
                System.out.println(d2);
                List<StatistiqueArticlesDirection> ldirection = new ArrayList<>();
                List<StatistiqueArticlesRegion> lregion = new ArrayList<>();
                List<StatistiqueArticlesService> lservice = new ArrayList<>();
                List<StatistiqueArticlesSite> lsite = new ArrayList<>();
                List<StatistiqueArticlesPersonnel> lpersonnel = new ArrayList<>();
                List<StatistiqueArticlesMagasinMs> lmagasinMs = new ArrayList<>();
                List<StatistiqueArticlesMagasinMp> lmagasinMp = new ArrayList<>();

                CategorieProduit catProduits = categorieProduitFacade.findByTypeCategorie(categorie);

                if (request.getParameter("region") != null) {
                    int region = Integer.parseInt(request.getParameter("region"));
                    JSONArray AllConsommation = new JSONArray();
                    for (Article art : catProduits.getArticleList()) {
                        StatistiqueArticlesRegion articlesRegion = statistiqueArticlesRegionFacade.findAllByArticlesByPeriode(region, art.getDesignation(), d1, d2);
                        if (articlesRegion != null) {
                            lregion.add(articlesRegion);
                        }

                    }
                   
                    double total = 0;
                    for (StatistiqueArticlesRegion cat : lregion) {
                        if (cat.getQuantite() != 0) {
                            total += cat.getPrixtotal();
                            JSONArray perso = new JSONArray();
                            try {
                                perso.put(cat.getArticles()).put(cat.getQuantite()).put(cat.getPrixtotal());
                            } catch (JSONException ex) {
                                Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            AllConsommation.put(perso);
                        }

                    }
                    try {
                        AllConsommation.put(total);
                    } catch (JSONException ex) {
                        Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(AllConsommation);
                    response.setContentType("application/json");
                    response.getWriter().print(AllConsommation);

                } else if (request.getParameter("id_magasin") != null) {
                    idmag = Integer.parseInt(request.getParameter("id_magasin"));
                    JSONArray AllConsommation = new JSONArray();
                    for (Article art : catProduits.getArticleList()) {
                        StatistiqueArticlesMagasinMs articlesMagasin = statistiqueArticlesMagasinMsFacade.findAllByArticlesByPeriode(idmag, art.getDesignation(), d1, d2);
                        if (articlesMagasin != null) {
                            lmagasinMs.add(articlesMagasin);
                        }
                        System.out.println(art.getDesignation());
                    }
                    double total = 0;
                    for (StatistiqueArticlesMagasinMs cat : lmagasinMs) {
                        if (cat.getQuantite() != 0) {
                            total += cat.getPrixtotal();
                            try {
                                JSONArray perso = new JSONArray();
                                perso.put(cat.getArticles()).put(cat.getQuantite()).put(cat.getPrixtotal());

                                AllConsommation.put(perso);

                            } catch (JSONException ex) {
                                Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    try {
                        AllConsommation.put(total);
                    } catch (JSONException ex) {
                        Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(AllConsommation);
                    response.setContentType("application/json");
                    response.getWriter().print(AllConsommation);

                } else if (request.getParameter("personnel") != null) {

                    int idpersonnel = Integer.parseInt(request.getParameter("personnel"));
                    JSONArray AllConsommation = new JSONArray();
                    for (Article art : catProduits.getArticleList()) {
                        StatistiqueArticlesPersonnel articlespersonnel = statistiqueArticlesPersonnelFacade.findAllByArticlesByPeriode(idpersonnel, art.getDesignation(), d1, d2);
                       
                            lpersonnel.add(articlespersonnel);
                     

                    }
                    double total = 0;
                    for (StatistiqueArticlesPersonnel cat : lpersonnel) {
                        if (cat.getQuantite() != 0) {
                            total += cat.getPrixtotal();
                            try {
                                JSONArray perso = new JSONArray();
                                perso.put(cat.getArticles()).put(cat.getQuantite()).put(cat.getPrixtotal());

                                AllConsommation.put(perso);

                            } catch (JSONException ex) {
                                Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    try {
                        AllConsommation.put(total);
                    } catch (JSONException ex) {
                        Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(AllConsommation);

                } else if (request.getParameter("service") != null) {
                    int idservice = Integer.parseInt(request.getParameter("service"));

                    JSONArray AllConsommation = new JSONArray();
                    for (Article art : catProduits.getArticleList()) {
                        StatistiqueArticlesService articlesService = statistiqueArticlesServiceFacade.findAllByArticlesByPeriode(idservice, art.getDesignation(), d1, d2);
                        if (articlesService != null) {
                            lservice.add(articlesService);
                        }

                    }
                    double total = 0;
                    for (StatistiqueArticlesService cat : lservice) {
                        if (cat.getQuantite() != 0) {
                            total += cat.getPrixtotal();
                            try {
                                JSONArray perso = new JSONArray();
                                perso.put(cat.getArticles()).put(cat.getQuantite()).put(cat.getPrixtotal());

                                AllConsommation.put(perso);

                            } catch (JSONException ex) {
                                Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    try {
                        AllConsommation.put(total);
                    } catch (JSONException ex) {
                        Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(AllConsommation);

                } else if (request.getParameter("site") != null) {
                    int idsite = Integer.parseInt(request.getParameter("site"));

                    JSONArray AllConsommation = new JSONArray();
                    for (Article art : catProduits.getArticleList()) {
                        StatistiqueArticlesSite articlesSite = statistiqueArticlesSiteFacade.findAllByArticlesByPeriode(idsite, art.getDesignation(), d1, d2);
                        if (articlesSite != null) {
                            lsite.add(articlesSite);
                        }
                        System.out.println(art.getDesignation());
                    }
                    double total = 0;
                    for (StatistiqueArticlesSite cat : lsite) {
                        if (cat.getQuantite() != 0) {
                            total += cat.getPrixtotal();
                            try {
                                JSONArray perso = new JSONArray();
                                perso.put(cat.getArticles()).put(cat.getQuantite()).put(cat.getPrixtotal());

                                AllConsommation.put(perso);

                            } catch (JSONException ex) {
                                Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    try {
                        AllConsommation.put(total);
                    } catch (JSONException ex) {
                        Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(AllConsommation);

                } else if (request.getParameter("direction") != null) {
                    int iddirection = Integer.parseInt(request.getParameter("direction"));
                    
                    JSONArray AllConsommation = new JSONArray();
                    for (Article art : catProduits.getArticleList()) {
                        StatistiqueArticlesDirection direction = statistiqueArticlesDirectionFacade.findAllByArticlesByPeriode(iddirection, art.getDesignation(), d1, d2);
                        if (direction != null) {
                            ldirection.add(direction);
                        }

                    }
                    double total = 0;
                    for (StatistiqueArticlesDirection cat : ldirection) {
                        if (cat.getQuantite() != 0) {
                            total += cat.getPrixtotal();
                            JSONArray perso = new JSONArray();
                            perso.put(cat.getArticles()).put(cat.getQuantite()).put(cat.getPrixtotal());
                            AllConsommation.put(perso);
                        }

                    }
                    try {
                        AllConsommation.put(total);
                    } catch (JSONException ex) {
                        Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(AllConsommation);

                } else if (request.getParameter("id_magasinP") != null) {
                    idmag = Integer.parseInt(request.getParameter("id_magasinP"));

                    JSONArray AllConsommation = new JSONArray();
                    for (Article art : catProduits.getArticleList()) {
                        StatistiqueArticlesMagasinMp articlesMagasinMp = statistiqueArticlesMagasinMpFacade.findAllByArticlesByPeriode(idmag, art.getDesignation(), d1, d2);
                        if (articlesMagasinMp != null) {
                            lmagasinMp.add(articlesMagasinMp);
                        }

                    }
                    double total = 0;
                    for (StatistiqueArticlesMagasinMp cat : lmagasinMp) {
                        if (cat.getQuantite() != 0) {
                            total += cat.getPrixtotal();
                            try {
                                JSONArray perso = new JSONArray();
                                perso.put(cat.getArticles()).put(cat.getQuantite()).put(cat.getPrixtotal());

                                AllConsommation.put(perso);

                            } catch (JSONException ex) {
                                Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    try {
                        AllConsommation.put(total);
                    } catch (JSONException ex) {
                        Logger.getLogger(parametre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(AllConsommation);
                }

            }
            if (action.equalsIgnoreCase("redirect")) {

                request.setAttribute("articles", articleFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("Addarticles")) {
                int categorie = Integer.parseInt(request.getParameter("categorie"));
                String Code = request.getParameter("code");
                String designation = request.getParameter("designation");
                double prix = Double.parseDouble(request.getParameter("pu"));
                int critique = Integer.parseInt(request.getParameter("stock_alerte"));
                List<Article> listarticle = articleFacade.findAll();
                boolean error = false;
                if (!listarticle.isEmpty()) {

                    for (Article arti : listarticle) {
                        if (arti.getCode().equalsIgnoreCase(Code)) {
                            error = true;
                            break;
                        }
                    }

                }
                if (!error) {

                    Article article = new Article();
                    article.setCode(Code);
                    article.setPrix(prix);
                    article.setDesignation(designation);
                    article.setCategorie(new CategorieProduit(categorie));
                    article.setCritique(critique);
                    articleFacade.create(article);

                    CategorieProduit ca = categorieProduitFacade.find(categorie);
                    List<Article> l = articleFacade.findAllByCategorieProduit(categorie);
                    ca.setArticleList(l);
                    categorieProduitFacade.edit(ca);

                    request.setAttribute("articles", articleFacade.findAll());
                    request.setAttribute("actif", "actif");
                    request.setAttribute("categories", categorieProduitFacade.findAll());
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                } else {
                    Message message = new Message("Cet Article Existe Deja");
                    request.setAttribute("message", message);
                    request.setAttribute("articles", articleFacade.findAll());
                    request.setAttribute("actif", "actif");
                    request.setAttribute("categories", categorieProduitFacade.findAll());
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
            } else if (action.equalsIgnoreCase("Editearticles")) {
                int article = Integer.parseInt(request.getParameter("article"));
                Article a = articleFacade.find(article);
                List<Article> list = articleFacade.findAll();
                list.remove(a);
                List<CategorieProduit> cp = categorieProduitFacade.findAll();
                request.setAttribute("allcategorieProduits", cp);
                request.setAttribute("update", "modifier");
                request.setAttribute("article", a);
                request.setAttribute("articles", list);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("editCategorie")) {
                int cat = Integer.parseInt(request.getParameter("id_categorie"));
                CategorieProduit a = categorieProduitFacade.find(cat);

                List<CategorieProduit> categorieProduits = categorieProduitFacade.findAll();
                request.setAttribute("type_categorie", categorieProduits);
                request.setAttribute("categorie", a);
                request.setAttribute("update", "oui");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("updateCategorie")) {
                int cat = Integer.parseInt(request.getParameter("id_categorie"));
                String designation = request.getParameter("type_categorie");
                String[] articl = request.getParameterValues("all_article");
                CategorieProduit catproduit = categorieProduitFacade.find(cat);
                List<Article> as = new ArrayList<>();
                for (String arti : articl) {
                    as.add(articleFacade.find(Integer.parseInt(arti)));
                }
                catproduit.getArticleList().removeAll(as);
                for (Article a : catproduit.getArticleList()) {
                    articleFacade.remove(a);
                }
                catproduit.setArticleList(articleFacade.findAllByCategorieProduit(cat));
                catproduit.setTypeCategorie(designation);
                categorieProduitFacade.edit(catproduit);
                List<CategorieProduit> categorieProduits = categorieProduitFacade.findAll();
                request.setAttribute("type_categorie", categorieProduits);

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deleteCategorie")) {
                int cat = Integer.parseInt(request.getParameter("id_categorie"));
                CategorieProduit a = categorieProduitFacade.find(cat);
                categorieProduitFacade.remove(a);
                List<CategorieProduit> categorieProduits = categorieProduitFacade.findAll();
                request.setAttribute("type_categorie", categorieProduits);

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("SaveUpdatearticles")) {
                int id_article = Integer.parseInt(request.getParameter("article"));
                Article article = articleFacade.find(id_article);
                String Code = request.getParameter("code");
                int id_catego = Integer.parseInt(request.getParameter("categorie"));
                String designation = request.getParameter("designation");
                double prix = Double.parseDouble(request.getParameter("pu"));
                int critique = Integer.parseInt(request.getParameter("stock_alerte"));
                article.setCode(Code);
                article.setPrix(prix);
                article.setDesignation(designation);
                article.setCritique(critique);
                article.setCategorie(categorieProduitFacade.find(id_catego));
                articleFacade.edit(article);
                List<MagasinPrincipal> Magasin = magasinPrincipalFacade.findAll();

                for (MagasinPrincipal maga : Magasin) {
                    StockproduitMP smp = stockproduitMPFacade.findByDesignationByidMP(maga.getIdMagasin(), article.getDesignation());
                    smp.setPrixUnitaire(article.getPrix());
                    stockproduitMPFacade.edit(smp);
                }
                List<MagasinSecondaire> MagasinMS = magasinSecondaireFacade.findAll();
                for (MagasinSecondaire maga : MagasinMS) {
                    StockproduitMS smp = stockproduitMSFacade.findByDesignationByidMS(maga.getIdMagasin(), article.getDesignation());
                    smp.setPrixUnitaire(article.getPrix());
                    stockproduitMSFacade.edit(smp);
                }

                request.setAttribute("articles", articleFacade.findAll());
                request.setAttribute("categories", categorieProduitFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deletearticles")) {
                int article = Integer.parseInt(request.getParameter("article"));
                Article a = articleFacade.find(article);
                articleFacade.remove(a);
                request.setAttribute("articles", articleFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("AjoutArticleMS")) {

                int id_article = Integer.parseInt(request.getParameter("article"));
                int idMS = Integer.parseInt(request.getParameter("magasin"));
                int quantiter = Integer.parseInt(request.getParameter("quantite"));
                String date = request.getParameter("date");

                Article article = articleFacade.find(id_article);
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                List<CategorieproduitMS> l = categorieproduitMSFacade.findCatByidMS(idMS);
                for (CategorieproduitMS cat : l) {
                    if (cat.getNomCategorie().equalsIgnoreCase(article.getCategorie().getTypeCategorie())) {
                        StockproduitMS s = new StockproduitMS();
                        s.setCategorie(new CategorieproduitMS(cat.getIdCategorie()));
                        s.setCodeProduit(article.getCode());
                        s.setDesignation(article.getDesignation());
                        s.setPrixUnitaire(article.getPrix());
                        s.setDateLivraison(date_du_jour.dateConvert(date));
                        s.setQuantite(quantiter);
                        s.setPrixTotal(s.getQuantite() * s.getPrixUnitaire());
                        stockproduitMSFacade.create(s);
                        cat.setStockproduitMSList(stockproduitMSFacade.findStockByIdCategorie(cat.getIdCategorie()));
                        categorieproduitMSFacade.edit(cat);
                        ms.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin()));
                        magasinSecondaireFacade.edit(ms);
                        break;
                    }
                }
                request.setAttribute("stockMS", stockproduitMSFacade.findStockMP(idMS));
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("AjoutArticleMP")) {
                int id_article = Integer.parseInt(request.getParameter("article"));
                int idMP = Integer.parseInt(request.getParameter("magasin"));
                int quantiter = Integer.parseInt(request.getParameter("quantite"));
                String date = request.getParameter("date");

                Article article = articleFacade.find(id_article);
                MagasinPrincipal ms = magasinPrincipalFacade.find(idMP);
                List<CategorieproduitMP> l = categorieproduitMPFacade.findCatByidMP(idMP);
                for (CategorieproduitMP cat : l) {
                    if (cat.getNomCategorie().equalsIgnoreCase(article.getCategorie().getTypeCategorie())) {
                        StockproduitMP s = new StockproduitMP();
                        s.setCategorie(new CategorieproduitMP(cat.getIdCategorie()));
                        s.setCodeProduit(article.getCode());
                        s.setDesignation(article.getDesignation());
                        s.setPrixUnitaire(article.getPrix());
                        s.setDateLivraison(date_du_jour.dateConvert(date));
                        s.setQuantite(quantiter);
                        s.setPrixTotal(s.getQuantite() * s.getPrixUnitaire());
                        stockproduitMPFacade.create(s);
                        cat.setStockproduitMPList(stockproduitMPFacade.findStockByIdCategorie(cat.getIdCategorie()));
                        categorieproduitMPFacade.edit(cat);
                        ms.setCategorieproduitMPList(categorieproduitMPFacade.findCatByidMP(ms.getIdMagasin()));
                        magasinPrincipalFacade.edit(ms);
                        break;
                    }
                }
                request.setAttribute("stockMP", stockproduitMPFacade.findByidMP(idMP));
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("editeMS")) {
                int idMS = Integer.parseInt(request.getParameter("idMagasin"));
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                l.remove(ms);
                if (l.isEmpty()) {
                    l.add(ms);
                }
                request.setAttribute("updateMags", "oui");
                request.setAttribute("parametre", "OK");
                // request.setAttribute("form", "ONE");
                request.setAttribute("MagasinsS", l);
                request.setAttribute("magasin", ms);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("SaveUpdateMS")) {
                int idMS = Integer.parseInt(request.getParameter("magasin"));
                int idsite = Integer.parseInt(request.getParameter("site"));
                String magasin = request.getParameter("nom_magasin");
                String description = request.getParameter("description");
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                ms.setSite(siteFacade.find(idsite));
                ms.setNomMagasin(magasin);
                ms.setDescription(description);
                magasinSecondaireFacade.edit(ms);
                request.setAttribute("form", "ONE");
                request.setAttribute("parametre", "OK");
                request.setAttribute("MagasinsS", magasinSecondaireFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("editeMP")) {
                int idMP = Integer.parseInt(request.getParameter("id_magasinP"));
                MagasinPrincipal mp = magasinPrincipalFacade.find(idMP);
                List<MagasinPrincipal> l = magasinPrincipalFacade.findAll();
                l.remove(mp);
                if (l.isEmpty()) {
                    l.add(mp);
                }
                request.setAttribute("MagasinsP", l);
                // request.setAttribute("form", "NON");
                request.setAttribute("parametre", "OK");
                request.setAttribute("magasin", mp);
                request.setAttribute("updateMagp", "oui");
                request.setAttribute("forme", "ONE");
                request.setAttribute("all", "yes");
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("SaveUpdateMP")) {
                int idMP = Integer.parseInt(request.getParameter("magasin"));
                List<Region> regions = regionFacade.findAll();
                String magasin = request.getParameter("nom_magasin");
                int idsite = Integer.parseInt(request.getParameter("site"));
                String description = request.getParameter("description");
                MagasinPrincipal ms = magasinPrincipalFacade.find(idMP);
                ms.setNomMagasin(magasin);
                ms.setDescription(description);
                magasinPrincipalFacade.edit(ms);
                ms.setSite(siteFacade.find(idsite));
                //request.setAttribute("form", "NON");
                request.setAttribute("parametre", "OK");
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsP", magasinPrincipalFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("tranfertCategorieMS")) {
                int idM1 = Integer.parseInt(request.getParameter("magasin1"));
                int idM2;
                if (Integer.parseInt(request.getParameter("magasin2")) != Integer.parseInt(request.getParameter("magasin1"))) {
                    String[] idcat = request.getParameterValues("categorie");
                    List<Butget> butget = butgetFacade.findAllBudgetByidMS(idM1);

                    if (request.getParameter("magasin2") != null) {
                        idM2 = Integer.parseInt(request.getParameter("magasin2"));
                        for (String cat : idcat) {
                            CategorieproduitMS s = categorieproduitMSFacade.find(Integer.parseInt(cat));
                            s.setMagasinSecondaire(new MagasinSecondaire(idM2));
                            categorieproduitMSFacade.edit(s);
                            for (Butget butg : butget) {
                                if (butg.getTypeBudget().equalsIgnoreCase(s.getNomCategorie())) {
                                    butg.setMagasin(new MagasinSecondaire(idM2));
                                    butgetFacade.edit(butg);
                                    break;

                                }
                            }
                        }
                    } else {
                        for (String cat : idcat) {
                            CategorieproduitMS s = categorieproduitMSFacade.find(Integer.parseInt(cat));

                            categorieproduitMSFacade.remove(s);
                            for (Butget butg : butget) {
                                if (butg.getTypeBudget().equalsIgnoreCase(s.getNomCategorie())) {

                                    butgetFacade.remove(butg);
                                    break;

                                }
                            }
                        }
                    }
                } else {
                    Message message = new Message("Veiller Selectionner Au Moins Deux Magasins Secondaires Differents");
                    request.setAttribute("message1", message);
                }
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsS", l);
                request.setAttribute("parametre", "OK");
                request.setAttribute("all", "yes");
                request.setAttribute("sorti", "OK");
                request.setAttribute("form", "ONE");
                request.setAttribute("forme", "ONE");
                request.setAttribute("transf", "OK");

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("tranfertCategorieMP")) {
                //int idM1 = Integer.parseInt(request.getParameter("magasin1"));
                int idM2 = Integer.parseInt(request.getParameter("magasin2"));
                String[] idcat = request.getParameterValues("categorie");
                if (Integer.parseInt(request.getParameter("magasin2")) != Integer.parseInt(request.getParameter("magasin1"))) {
                    for (String cat : idcat) {
                        CategorieproduitMP s = categorieproduitMPFacade.find(Integer.parseInt(cat));
                        s.setMagasinPrincipal(new MagasinPrincipal(idM2));
                        categorieproduitMPFacade.edit(s);

                    }
                } else {
                    Message message = new Message("Veiller Selectionner Au Moins Deux Magasins Principaux Differents");
                    request.setAttribute("message1", message);
                }
                List<MagasinPrincipal> mps = magasinPrincipalFacade.findAll();
                //  List<AffectationmagasinP> MagasinsP = affectationmagasinPFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsP", mps);
                request.setAttribute("listMP", mps);
                request.setAttribute("form", "NON");
                request.setAttribute("forme", "ONE");
                request.setAttribute("all", "yes");
                request.setAttribute("parametre", "OK");
                request.setAttribute("transf", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("getArticlesByCategorieMP")) {

                int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
                CategorieproduitMP CatP = categorieproduitMPFacade.find(idCategorie);
                CategorieProduit c = categorieProduitFacade.findByTypeCategorie(CatP.getNomCategorie());

                List<Article> permissions = articleFacade.findAllByCategorieProduit(c.getIdCategorieProduit());
                //System.out.println(permissions);
                JSONArray Allpages = new JSONArray();
                permissions.stream().map((permission) -> {
                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdArticle()).put(permission.getDesignation());
                    return perso;
                }).forEach((perso) -> {
                    Allpages.put(perso);
                });

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("allservice")) {
                request.setAttribute("stat", "service");
                request.setAttribute("service", serviceFacade.findAll());
                request.setAttribute("region", regionFacade.findAll());
                request.setAttribute("site", siteFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("allsite")) {
                request.setAttribute("stat", "site");
                request.setAttribute("site", siteFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("allregion")) {
                request.setAttribute("stat", "region");
                request.setAttribute("region", regionFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("consommationUser")) {

                request.setAttribute("services", serviceFacade.findAll());
                request.setAttribute("regions", regionFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("consouser", "oui");

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("consommationService")) {
                int idservice = Integer.parseInt(request.getParameter("service"));
                Service service = serviceFacade.find(idservice);
                List<OperationConsommateur> list = new ArrayList<>();
                service.getPersonnelList().stream().forEach((personnel) -> {
                    personnel.getOperationConsommateurList().stream().forEach((operation) -> {
                        list.add(operation);
                    });
                });
                List<OperationConsommateur> list1 = new ArrayList<>();
                List<OperationConsommateur> list2 = new ArrayList<>();
                List<CategorieProduit> l = categorieProduitFacade.findAll();
                l.stream().forEach((catego) -> {
                    double somme = 0;
                    int quantite = 0;
                    OperationConsommateur oc = new OperationConsommateur();
                    for (OperationConsommateur operation : list) {
                        if (catego.getTypeCategorie().equalsIgnoreCase(operation.getCategorie())) {
                            oc.setCategorie(operation.getCategorie());
                            somme += operation.getPrixTotal();
                            quantite += operation.getQuantite();
                            oc.setPrixTotal(somme);
                            oc.setQuantite(quantite);

                        }
                    }
                    if (somme > 0 && quantite > 0) {
                        list1.add(oc);
                    }
                });

                request.setAttribute("operation", list1);

                //  list1.clear();
                l.stream().forEach((catego) -> {
                    catego.getArticleList().stream().forEach((ar) -> {
                        double somme = 0;
                        int quantite = 0;
                        // OperationConsommateur occ = new OperationConsommateur();
                        OperationConsommateur oc = new OperationConsommateur();
                        for (OperationConsommateur operation : list) {
                            if (ar.getCode().equalsIgnoreCase(operation.getCodeProduit())) {
                                //  occ = operation;
                                oc.setCategorie(operation.getCategorie());
                                oc.setDesignation(operation.getDesignation());
                                oc.setCodeProduit(operation.getCodeProduit());
                                somme += operation.getPrixTotal();
                                quantite += operation.getQuantite();
                                oc.setPrixTotal(somme);
                                oc.setQuantite(quantite);
                                oc.setPrix(operation.getPrix());

                            }
                        }
                        if (somme > 0 && quantite > 0) {
                            list2.add(oc);
                        }
                    });
                });

                request.setAttribute("stat", "service");
                //request.setAttribute("vue", "consoService");
                request.setAttribute("statservice", "oui");
                request.setAttribute("services", serviceFacade.findAll());
                request.setAttribute("regions", regionFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("operationArticle", list2);
                request.setAttribute("conso", "le service " + service.getNomService());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("consommationSite")) {
                int idsite = Integer.parseInt(request.getParameter("site"));
                Site site = siteFacade.find(idsite);
                List<OperationConsommateur> list = new ArrayList<>();
                site.getServiceList().stream().forEach((service) -> {
                    service.getPersonnelList().stream().forEach((personnel) -> {
                        personnel.getOperationConsommateurList().stream().forEach((operation) -> {
                            list.add(operation);
                        });
                    });
                });

                List<OperationConsommateur> list1 = new ArrayList<>();
                List<CategorieProduit> l = categorieProduitFacade.findAll();
                l.stream().forEach((catego) -> {
                    double somme = 0;
                    int quantite = 0;
                    OperationConsommateur oc = new OperationConsommateur();
                    for (OperationConsommateur operation : list) {
                        if (catego.getTypeCategorie().equalsIgnoreCase(operation.getCategorie())) {
                            oc.setCategorie(operation.getCategorie());
                            somme += operation.getPrixTotal();
                            quantite += operation.getQuantite();
                            oc.setPrixTotal(somme);
                            oc.setQuantite(quantite);

                        }
                    }
                    if (somme > 0 && quantite > 0) {
                        list1.add(oc);
                    }
                });
                request.setAttribute("operation", list1);

                list1.clear();

                l.stream().forEach((catego) -> {
                    catego.getArticleList().stream().forEach((ar) -> {
                        double somme = 0;
                        int quantite = 0;
                        //OperationConsommateur occ = new OperationConsommateur();
                        OperationConsommateur oc = new OperationConsommateur();
                        for (OperationConsommateur operation : list) {
                            if (ar.getCode().equalsIgnoreCase(operation.getCodeProduit())) {
                                //  occ = operation;
                                oc.setCategorie(operation.getCategorie());
                                oc.setDesignation(operation.getDesignation());
                                oc.setCodeProduit(operation.getCodeProduit());
                                somme += operation.getPrixTotal();
                                quantite += operation.getQuantite();
                                oc.setPrixTotal(somme);
                                oc.setQuantite(quantite);
                                oc.setPrix(operation.getPrix());

                            }
                        }
                        if (somme > 0 && quantite > 0) {
                            list1.add(oc);
                        }
                    });
                });
                request.setAttribute("site", site);
                request.setAttribute("stat", "site");
                request.setAttribute("statsite", "oui");
                request.setAttribute("services", serviceFacade.findAll());
                request.setAttribute("regions", regionFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("operationArticle", list1);
                request.setAttribute("conso", "le site " + site.getNomSite());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("consommationRegion")) {
                int idregion = Integer.parseInt(request.getParameter("region"));
                Region region = regionFacade.find(idregion);
                List<OperationConsommateur> list = new ArrayList<>();
                region.getSiteList().stream().forEach((site) -> {
                    site.getServiceList().stream().forEach((service) -> {
                        service.getPersonnelList().stream().forEach((personnel) -> {
                            personnel.getOperationConsommateurList().stream().forEach((operation) -> {
                                list.add(operation);
                            });
                        });
                    });
                });
                List<OperationConsommateur> list1 = new ArrayList<>();
                List<CategorieProduit> l = categorieProduitFacade.findAll();
                l.stream().forEach((catego) -> {
                    double somme = 0;
                    int quantite = 0;
                    OperationConsommateur oc = new OperationConsommateur();
                    for (OperationConsommateur operation : list) {
                        if (catego.getTypeCategorie().equalsIgnoreCase(operation.getCategorie())) {
                            oc.setCategorie(operation.getCategorie());
                            somme += operation.getPrixTotal();
                            quantite += operation.getQuantite();
                            oc.setPrixTotal(somme);
                            oc.setQuantite(quantite);

                        }
                    }
                    if (somme > 0 && quantite > 0) {
                        list1.add(oc);
                    }
                });
                request.setAttribute("operation", list1);

                list1.clear();

                l.stream().forEach((catego) -> {
                    catego.getArticleList().stream().forEach((ar) -> {
                        double somme = 0;
                        int quantite = 0;
                        // OperationConsommateur occ = new OperationConsommateur();
                        OperationConsommateur oc = new OperationConsommateur();
                        for (OperationConsommateur operation : list) {
                            if (ar.getCode().equalsIgnoreCase(operation.getCodeProduit())) {
                                //  occ = operation;
                                oc.setCategorie(operation.getCategorie());
                                oc.setDesignation(operation.getDesignation());
                                oc.setCodeProduit(operation.getCodeProduit());
                                somme += operation.getPrixTotal();
                                quantite += operation.getQuantite();
                                oc.setPrixTotal(somme);
                                oc.setQuantite(quantite);
                                oc.setPrix(operation.getPrix());

                            }
                        }
                        if (somme > 0 && quantite > 0) {
                            list1.add(oc);
                        }
                    });
                });
                request.setAttribute("region", region);
                request.setAttribute("stat", "region");
                request.setAttribute("statregion", "oui");
                request.setAttribute("services", serviceFacade.findAll());
                request.setAttribute("regions", regionFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("operationArticle", list1);
                request.setAttribute("conso", "la region " + region.getNomRegion());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("consommationSociete")) {

                List< Region> regions = regionFacade.findAll();
                List<OperationConsommateur> list = new ArrayList<>();
                regions.stream().forEach((region) -> {
                    region.getSiteList().stream().forEach((site) -> {
                        site.getServiceList().stream().forEach((service) -> {
                            service.getPersonnelList().stream().forEach((personnel) -> {
                                personnel.getOperationConsommateurList().stream().forEach((operation) -> {
                                    list.add(operation);
                                });
                            });
                        });
                    });
                });

                List< OperationConsommateur> list1 = new ArrayList<>();
                List<CategorieProduit> l = categorieProduitFacade.findAll();
                l.stream().forEach((catego) -> {
                    double somme = 0;
                    int quantite = 0;
                    OperationConsommateur oc = new OperationConsommateur();
                    for (OperationConsommateur operation : list) {
                        if (catego.getTypeCategorie().equalsIgnoreCase(operation.getCategorie())) {
                            oc.setCategorie(operation.getCategorie());
                            somme += operation.getPrixTotal();
                            quantite += operation.getQuantite();
                            oc.setPrixTotal(somme);
                            oc.setQuantite(quantite);

                        }
                    }
                    if (somme > 0 && quantite > 0) {
                        list1.add(oc);
                    }
                });
                request.setAttribute("operation", list1);

                list1.clear();

                l.stream().forEach((catego) -> {
                    catego.getArticleList().stream().forEach((ar) -> {
                        double somme = 0;
                        int quantite = 0;
                        // OperationConsommateur occ = null;
                        OperationConsommateur oc = new OperationConsommateur();
                        for (OperationConsommateur operation : list) {
                            if (ar.getCode().equalsIgnoreCase(operation.getCodeProduit())) {
                                // occ = operation;
                                oc.setCategorie(operation.getCategorie());
                                oc.setDesignation(operation.getDesignation());
                                oc.setCodeProduit(operation.getCodeProduit());
                                somme += operation.getPrixTotal();
                                quantite += operation.getQuantite();
                                oc.setPrixTotal(somme);
                                oc.setQuantite(quantite);
                                oc.setPrix(operation.getPrix());

                            }
                        }
                        if (somme > 0 && quantite > 0) {
                            list1.add(oc);
                        }
                    });
                });
                request.setAttribute("stat", "societe");
                request.setAttribute("statsociete", "oui");
                request.setAttribute("services", serviceFacade.findAll());
                request.setAttribute("regions", regionFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("operationArticle", list1);
                request.setAttribute("conso", "la societe");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deletteArticleStockMP")) {

                int idarticle = Integer.parseInt(request.getParameter("idarticle"));
                int idmagasin = Integer.parseInt(request.getParameter("id_Magasin"));
                Article art = articleFacade.find(idarticle);
                StockproduitMP smp = stockproduitMPFacade.findByDesignationByidMP(idmagasin, art.getDesignation());

                stockproduitMPFacade.remove(smp);
                List<StockproduitMP> liststockMP = stockproduitMPFacade.findByidMP(smp.getCategorie().getMagasinPrincipal().getIdMagasin());
                MagasinPrincipal mag = magasinPrincipalFacade.find(idmagasin);
                JSONArray Allpages = new JSONArray();
                mag.getCategorieproduitMPList().stream().map((permission) -> {
                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdCategorie()).put(permission.getNomCategorie());
                    return perso;
                }).forEach((perso) -> {
                    Allpages.put(perso);
                });

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("deletteArticleStockMS")) {

                int idarticle = Integer.parseInt(request.getParameter("idarticle"));
                int idmagasin = Integer.parseInt(request.getParameter("id_Magasin"));
                Article art = articleFacade.find(idarticle);
                StockproduitMS smp = stockproduitMSFacade.findByDesignationByidMS(idmagasin, art.getDesignation());
                stockproduitMSFacade.remove(smp);
                MagasinSecondaire mag = magasinSecondaireFacade.find(idmagasin);

                List<StockproduitMS> liststockMP = stockproduitMSFacade.findStockMP(smp.getCategorie().getMagasinSecondaire().getIdMagasin());

                JSONArray Allpages = new JSONArray();
                mag.getCategorieproduitMSList().stream().map((permission) -> {
                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdCategorie()).put(permission.getNomCategorie());
                    return perso;
                }).forEach((perso) -> {
                    Allpages.put(perso);
                });

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            }

        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

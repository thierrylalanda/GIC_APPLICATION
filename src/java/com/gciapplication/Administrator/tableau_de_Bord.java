/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.Administrator;

import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Region;
import com.gciapplication.genaralConfiguration.DataForStatistiques;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.DirectionFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.ServiceFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategoriMagasinMpFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieDirectionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieMagasinMsFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategoriePersonnelFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieRegionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieServiceFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieSiteFacadeLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "tableau_de_Bord", urlPatterns = {"/tableau_de_Bord"})
public class tableau_de_Bord extends HttpServlet {

    @EJB
    private StatistiqueCategoriMagasinMpFacadeLocal statistiqueCategoriMagasinMpFacade;

    @EJB
    private StatistiqueCategorieDirectionFacadeLocal statistiqueCategorieDirectionFacade;

    @EJB
    private StatistiqueCategorieMagasinMsFacadeLocal statistiqueCategorieMagasinMsFacade;

    @EJB
    private StatistiqueCategoriePersonnelFacadeLocal statistiqueCategoriePersonnelFacade;

    @EJB
    private StatistiqueCategorieRegionFacadeLocal statistiqueCategorieRegionFacade;

    @EJB
    private StatistiqueCategorieSiteFacadeLocal statistiqueCategorieSiteFacade;

    @EJB
    private StatistiqueCategorieServiceFacadeLocal statistiqueCategorieServiceFacade;

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private DirectionFacadeLocal directionFacade;

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private SiteFacadeLocal siteFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

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
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            request.setAttribute("vue", vue);
            Personnel p = (Personnel) session.getAttribute("personnel");

            if (action.equalsIgnoreCase("allStatConsommationService")) {

                int service = Integer.parseInt(request.getParameter("service"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {
                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelService(service, annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
                System.out.println(per);
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equalsIgnoreCase("allRapport")) {

                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("directions", directionFacade.findAll());
                request.setAttribute("services", serviceFacade.findAll());
                request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("allRapportad")) {

                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("directions", directionFacade.findAll());
                request.setAttribute("services", serviceFacade.findAll());
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("allStatConsommationSite")) {

                int site = Integer.parseInt(request.getParameter("site"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {
                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelSite(site, annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
                System.out.println(per);
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equalsIgnoreCase("allStatConsommationDirection")) {

                int direction = Integer.parseInt(request.getParameter("direction"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {
                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelDirection(direction, annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
               
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equalsIgnoreCase("allStatConsommationRegion")) {

                int region = Integer.parseInt(request.getParameter("region"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {

                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelRegion(region, annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
                System.out.println(per);
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equalsIgnoreCase("allStatpersonnel")) {
                int id_perso = Integer.parseInt(request.getParameter("personnel"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                Map myMap;
                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {
                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelPersonnel(id_perso, annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
                System.out.println(per);
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equalsIgnoreCase("allStatMS")) {
                int idMS = Integer.parseInt(request.getParameter("magasin"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {
                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelMS(idMS, annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
                System.out.println(per);
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equalsIgnoreCase("allStatMP")) {
                int idMP = Integer.parseInt(request.getParameter("magasin"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {
                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelMP(idMP, annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
                System.out.println(per);
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equalsIgnoreCase("consommationSociete")) {
                int annee = Integer.parseInt(request.getParameter("annee"));
                System.out.println(annee + " Societe");
               
                org.json.simple.JSONArray o = new org.json.simple.JSONArray();
                for (CategorieProduit cat : categorieProduitFacade.findAll()) {
                    org.json.simple.JSONArray oo = new org.json.simple.JSONArray();

                    List<Integer> quante = StatMensuelSociete(annee, cat.getTypeCategorie());

                    oo.add(cat.getTypeCategorie());
                    oo.add(quante);
                    o.add(oo);
                }

                String per = o.toJSONString();
                response.setContentType("application/json");
                System.out.println(per + " Societe 12");
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (action.equalsIgnoreCase("consommationSocietead")) {
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
              request.setAttribute("vue", request.getParameter("vue"));
                request.setAttribute("stat", "service");
                //request.setAttribute("vue", "consoService");
                request.setAttribute("statservice", "oui");
                request.setAttribute("services", serviceFacade.findAll());
                request.setAttribute("regions", regionFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
              
                request.setAttribute("categories", categorieProduitFacade.findAll());
               
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            }

        } else {
            response.sendRedirect("login.jsp");
        }
    }

    List<Integer> StatMensuelService(int service, int annee, String categorie) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategorieServiceFacade.findAllByCategorieByAnneeForService(service, categorie, annee, i);
            l.add(quantite);
        }
        return l;
    }

    List<Integer> StatMensuelSite(int service, int annee, String categorie) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategorieSiteFacade.findAllByCategorieByAnneeForSite(service, categorie, annee, i);
            l.add(quantite);
        }
        return l;
    }

    List<Integer> StatMensuelDirection(int service, int annee, String categorie) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategorieDirectionFacade.findAllByCategorieByAnneeForDirection(service, categorie, annee, i);
            l.add(quantite);
        }
        return l;
    }

    List<Integer> StatMensuelRegion(int service, int annee, String categorie) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategorieRegionFacade.findAllByCategorieByAnneeForRegion(service, categorie, annee, i);
            l.add(quantite);
        }
        return l;
    }

    List<Integer> StatMensuelPersonnel(int service, int annee, String categorie) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategoriePersonnelFacade.findAllByCategorieByAnneeForPersonnel(service, categorie, annee, i);
            l.add(quantite);
        }
        return l;
    }

    List<Integer> StatMensuelMS(int service, int annee, String categorie) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategorieMagasinMsFacade.findAllByCategorieByAnneeForMagMS(service, categorie, annee, i);
            l.add(quantite);
        }
        return l;
    }

    List<Integer> StatMensuelMP(int service, int annee, String categorie) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategoriMagasinMpFacade.findAllByCategorieByAnneeForMagMP(service, categorie, annee, i);
            l.add(quantite);
        }
        return l;
    }

    List<Integer> StatMensuelSociete(int annee, String categorie) {

        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int quantite = statistiqueCategorieRegionFacade.findAllByCategorieByAnneeForSociete(categorie,i,annee);
            l.add(quantite);
           
        }
        return l;
    }
}

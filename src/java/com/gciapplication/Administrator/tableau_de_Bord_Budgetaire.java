/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.Administrator;

import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Region;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.ServiceFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import java.io.IOException;
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
@WebServlet(name = "tableau_de_Bord_Budgetaire", urlPatterns = {"/tableau_de_Bord_Budgetaire"})
public class tableau_de_Bord_Budgetaire extends HttpServlet {

    @EJB
    private SiteFacadeLocal siteFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private LoginFacadeLocal loginFacade;

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

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
            int niveau = 0;
            String redirect;
            Personnel p = (Personnel) session.getAttribute("personnel");
            if (loginFacade.findBypersonnelID(p.getIdPersonnel()).getNiveauAcces() == 4) {
               redirect = "/WEB-INF/controleurs/main.jsp";
            } else {
                 redirect = "/WEB-INF/administrateur/main1.jsp";
            }
           
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            request.setAttribute("vue", vue);
          
            if (action.equalsIgnoreCase("allStatBudgaireRegion")) {
                int region;
                if (request.getParameter("region") != null) {
                    region = Integer.parseInt(request.getParameter("region"));
                } else {
                    try {
                        region = p.getService().getSite().getRegion().getIdRegion();
                    } catch (Exception e) {
                        List<Region> regions = regionFacade.findAll();
                        region = regions.get(0).getIdRegion();
                    }

                }

                List<MagasinSecondaire> magasinMS = magasinSecondaireFacade.findByIdregion(region);
                JSONArray AllConsommation = new JSONArray();
                JSONArray persos = new JSONArray();
                for (MagasinSecondaire mag : magasinMS) {
                    JSONArray perso = new JSONArray();
                    perso.put(mag.getNomMagasin());
                    for (Butget but : mag.getButgetList()) {

                        JSONArray cat = new JSONArray();

                        double pourcentage = ((but.getMontant() - but.getMontantRestant()) / but.getMontant()) * 100;
                        try {
                            cat.put(but.getTypeBudget()).put(arrondi(pourcentage, 2));
                        } catch (JSONException ex) {
                            System.err.println("erreur Ici Tableau Json");
                            Logger.getLogger(tableau_de_Bord_Budgetaire.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        perso.put(cat);
                    }

                    //persos.put();
                    AllConsommation.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(AllConsommation);

            } else if (action.equalsIgnoreCase("allStatUtilisateur")) {

                int region;
                boolean admin = false;
                if (request.getParameter("region") != null) {
                    region = Integer.parseInt(request.getParameter("region"));
                } else {
                    try {
                        region = p.getService().getSite().getRegion().getIdRegion();
                    } catch (Exception e) {
                        List<Region> regions = regionFacade.findAll();
                        region = regions.get(0).getIdRegion();
                    }
                }
                List<CategorieProduit> categorieProduits = categorieProduitFacade.findAll();
                List<Personnel> personnels = personnelFacade.findAllByIdRegion(region);
                // List<OperationConsommateur> consommateurs = new ArrayList<>();
                JSONArray AllConsommation = new JSONArray();

                for (Personnel personnel : personnels) {
                   
                    JSONArray perso = new JSONArray();
                  
                    JSONArray  pers = new JSONArray();
                 
                    perso.put(personnel.getNomPrenom()).put(personnel.getIdPersonnel());
                    for (CategorieProduit cat : categorieProduits) {
                        int somme = 0;
                       JSONArray catt = new JSONArray();
                        for (OperationConsommateur consommateur : operationConsommateurFacade.findAllByIdPersonnelAndDateNow(personnel.getIdPersonnel())) {
                            if (cat.getTypeCategorie().equalsIgnoreCase(consommateur.getCategorie())) {
                                somme += consommateur.getQuantite();

                            }

                        }
                        catt.put(cat.getTypeCategorie()).put(somme);
                        pers.put(catt);
                    }

                    perso.put(pers);
                    AllConsommation.put(perso);
                }

                response.setContentType("application/json");
                response.getWriter().print(AllConsommation);

            } else if (action.equalsIgnoreCase("DetailleStatUtilisateur")) {
                 int idpersonnel =Integer.parseInt(request.getParameter("nomutilisateur"));
                 String categorie = request.getParameter("categorie");
                List<OperationConsommateur> ocs = operationConsommateurFacade.findAllByIdMSAndCategorieAnNamePersonnel(categorie, idpersonnel);
                request.setAttribute("operation", ocs);
                 
                request.setAttribute("consouser", "oui");
                request.setAttribute("active", "oui");
                request.setAttribute("consouserone", "OK");
                 request.setAttribute("services", serviceFacade.findAll());
                request.setAttribute("regions", regionFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("username",personnelFacade.find(idpersonnel).getNomPrenom());
                 request.setAttribute("categories", categorieProduitFacade.findAll());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public double arrondi(double A, int B) {
        return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
    }

}

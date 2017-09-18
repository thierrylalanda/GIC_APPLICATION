/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.CommandePersonnelFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
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
@WebServlet(name = "RedirectionVue", urlPatterns = {"/RedirectionVue"})
public class RedirectionVue extends HttpServlet {

    @EJB
    private CommandePersonnelFacadeLocal commandePersonnelFacade;

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private LoginFacadeLocal loginFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    List< MagasinPrincipal> mpp = new ArrayList<>();

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
            int idMS;
            request.setAttribute("statistique", "ok");
            Personnel p = (Personnel) session.getAttribute("personnel");
            p.setLoginList(loginFacade.findBypersonnelID(p.getIdPersonnel()));
            request.setAttribute("personnell", p);
            request.setAttribute("commandesEncour", commandePersonnelFacade.findCommandeNonReceptionner(p.getIdPersonnel(), "non"));
            if (session.getAttribute("id_magasin") == null) {
                idMS = Integer.parseInt(request.getParameter("id_magasin"));

            } else {
                idMS = (int) session.getAttribute("id_magasin");
            }

            String vue = request.getParameter("vue");

            List<StockproduitMS> sms = stockproduitMSFacade.ProduitCritique(idMS);
            List<StockproduitMS> smss = stockproduitMSFacade.ProduitWarmin(idMS);
            if (!sms.isEmpty()) {
                String etat = "danger";
                request.setAttribute("etat", etat);
            }
            if (!smss.isEmpty()) {
                String etat1 = "warning";
                request.setAttribute("etat1", etat1);
            }
            if (request.getParameter("budget") != null) {
                request.setAttribute("StatistiqueBMS", "OK");
            }

            mpp = magasinPrincipalFacade.findAll();

            MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
            if (request.getParameter("rapport") != null) {
                request.setAttribute("rapport", "ok");
                request.setAttribute("categories", ms.getCategorieproduitMSList());
            }

            request.setAttribute("fournisseur", mpp);
            session.setAttribute("tail", sms.size());
            session.setAttribute("taill", smss.size());
            session.setAttribute("sms", sms);
            session.setAttribute("smss", smss);
            request.setAttribute("active", "data");
            request.setAttribute("vue", vue);

            request.setAttribute("for", "MS");

            request.setAttribute("StatistiqueMS", "OK");
            ms.setButgetList(butgetFacade.findAllBudgetByidMS(ms.getIdMagasin()));
            ms.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin()));
            request.setAttribute("magasin", ms);
            request.setAttribute("categorie", ms.getCategorieproduitMSList());

            if (!ms.getAffectationmagasinSList().isEmpty()) {
                request.setAttribute("personnel", ms.getAffectationmagasinSList().get(0).getPersonnel());
            }

            int niveau = 0;
            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            switch (niveau) {
                case 5:
                    request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                    break;
                case 4:
                    session.removeAttribute("id_magasinP");
                    if (request.getParameter("rapportms") == null) {

                        request.setAttribute("stat", "OK");
                    } else if (request.getParameter("rapportms") != null) {
                        request.setAttribute("rapportms", "ok");

                        List<CategorieproduitMS> catMS = categorieproduitMSFacade.findCatByidMS(idMS);
                        request.setAttribute("categories", catMS);
                    }
                    request.setAttribute("idMS", idMS);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                    break;
                case 3:
                    int idmagp = Integer.parseInt(request.getParameter("id_magasinP"));
                    List<CategorieproduitMP> catmp = categorieproduitMPFacade.findCatByidMP(idmagp);
                    request.setAttribute("categories", catmp);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                    break;
                case 2:
                    int idmag = Integer.parseInt(request.getParameter("id_magasin"));
                    List<CategorieproduitMS> catms = categorieproduitMSFacade.findCatByidMS(idmag);
                    request.setAttribute("categories", catms);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                    break;

                default:
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                    break;
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

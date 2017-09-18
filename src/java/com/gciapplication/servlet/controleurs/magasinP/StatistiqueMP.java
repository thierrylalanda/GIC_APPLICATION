/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.entity.Login;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.genaralConfiguration.testeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import java.io.IOException;
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
@WebServlet(name = "StatistiqueMP", urlPatterns = {"/StatistiqueMP"})
public class StatistiqueMP extends HttpServlet {

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private LoginFacadeLocal loginFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

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
            Personnel p = (Personnel) session.getAttribute("personnel");

            String vue = request.getParameter("vue");
            request.setAttribute("vue", vue);
            String action = request.getParameter("action");
            
            request.setAttribute("statistique", "ok");
            if (action.equalsIgnoreCase("editPassword")) {
                String lien = "";
                String qui = request.getParameter("qui");
                String passnew = request.getParameter("newpassword");
                String lastpass = request.getParameter("password1");
                Login l = loginFacade.findBypersonnelID(p.getIdPersonnel());
                if (qui.equalsIgnoreCase("MP")) {

                    lien = "/WEB-INF/magasignierP/main.jsp";
                } else if (qui.equalsIgnoreCase("MS")) {
                    lien = "/WEB-INF/magasignerS/main.jsp";
                } else if (qui.equalsIgnoreCase("Controleur")) {
                    lien = "/WEB-INF/controleurs/main.jsp";
                } else if (qui.equalsIgnoreCase("admin")) {
                    lien = "/WEB-INF/administrateur/main1.jsp";
                }
                if (lastpass.equalsIgnoreCase(l.getPassword())) {
                    l.setPassword(passnew);
                    loginFacade.edit(l);
                    session.removeAttribute("login");
                    Login login = loginFacade.findBypersonnelID(p.getIdPersonnel());
                    session.setAttribute("login", login);
                    request.setAttribute("active", "data");
                } else {
                    request.setAttribute("active", "login");
                    Message message = new Message("Ancien Mot de Passe Incorrect");
                    request.setAttribute("message", message);
                }
                request.getServletContext().getRequestDispatcher(lien).forward(request, response);
            } else {
                int id_magasin = (int) session.getAttribute("id_magasinP");
                
                List<StockproduitMP> sms, smss;
                sms = stockproduitMPFacade.ProduitCritique(id_magasin);
                smss = stockproduitMPFacade.ProduitWarmin(id_magasin);
                if (!sms.isEmpty()) {
                    String etat = "danger";
                    request.setAttribute("etat", etat);
                }
                if (!smss.isEmpty()) {
                    String etat1 = "warning";
                    request.setAttribute("etat1", etat1);
                }
                session.setAttribute("tail", sms.size());
                session.setAttribute("taill", smss.size());
                session.setAttribute("sms", sms);
                session.setAttribute("smss", smss);
                request.setAttribute("active", "data");
                request.setAttribute("categorie", magasinPrincipalFacade.find(id_magasin).getCategorieproduitMPList());
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);

            }

        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

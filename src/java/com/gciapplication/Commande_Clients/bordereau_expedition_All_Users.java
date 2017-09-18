/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.Commande_Clients;

import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StatistiqueArticlesPersonnel;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesPersonnelFacadeLocal;
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
@WebServlet(name = "bordereau_expedition_All_Users", urlPatterns = {"/bordereau_expedition_All_Users"})
public class bordereau_expedition_All_Users extends HttpServlet {

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private StatistiqueArticlesPersonnelFacadeLocal statistiqueArticlesPersonnelFacade;

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
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            request.setAttribute("vue", vue);
            Personnel p = (Personnel) session.getAttribute("personnel");

            String redirect;
            int niveau = 0;
            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            switch (niveau) {
                case 5:
                    redirect = "/WEB-INF/administrateur/main1.jsp";

                    break;
                case 3:

                    redirect = "/WEB-INF/magasignierP/main.jsp";
                    break;
                case 2:
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                    break;
                default:
                    redirect = "/WEB-INF/controleurs/main.jsp";
                    break;
            }
            if (action.equalsIgnoreCase("commandePeriodique")) {
                int personnel = Integer.parseInt(request.getParameter("personnel"));
                String dates = request.getParameter("interval");
                String[] d = dates.split("-");
                String datedebut = d[0];
                String datefin = d[1];
Personnel per=personnelFacade.find(personnel);
                Date d1 = date_du_jour.dateConvert(datedebut);
                Date d2 = date_du_jour.dateConvert(datefin);
                System.out.println(d1);
                System.out.println(d2);
                List<OperationConsommateur> ocs = operationConsommateurFacade.findAllByDateSortiByPersonnel(personnel, d1, d2);
                request.setAttribute("cps", ocs);
                request.setAttribute("operation", ocs);
                request.setAttribute("client", personnelFacade.find(personnel));
                if (ocs.isEmpty()) {
                    Message message = new Message(per.getNomPrenom()+" n'a effectué Aucune operation  sur cette période");
                    request.setAttribute("message", message);
                }

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

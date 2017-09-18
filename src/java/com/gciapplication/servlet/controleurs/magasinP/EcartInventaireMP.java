/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.entity.EcartinventaireMP;
import com.gciapplication.session.entity.EcartinventaireMPFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
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
@WebServlet(name = "EcartInventaireMP", urlPatterns = {"/EcartInventaireMP"})
public class EcartInventaireMP extends HttpServlet {

    @EJB
    private EcartinventaireMPFacadeLocal ecartinventaireMPFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    List<EcartinventaireMP> lists = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            int idMP = (int) session.getAttribute("id_magasinP");
            // int idMagasigner = (int) session.getAttribute("id_magasignerP");
            if (action.equalsIgnoreCase("all")) {
                lists.removeAll(lists);
                // Personnel p = personnelFacade.find(idMagasigner);
                lists = ecartinventaireMPFacade.findAllById(idMP);

            }
            if (action.equalsIgnoreCase("date")) {
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                Date d = date_du_jour.dateConvert(date1);
                Date dd = date_du_jour.dateConvert(date2);
                lists.removeAll(lists);
                lists = ecartinventaireMPFacade.findByPeriode(d, dd, idMP);
            }

            request.setAttribute("historique", lists);
            request.setAttribute("vue", vue);
            request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.genaralConfiguration.testeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Statistique", urlPatterns = {"/Statistique"})
public class Statistique extends HttpServlet {

    @EJB
    private testeLocal dataForStatistiques;

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

            if (session.getAttribute("id_magasin_Statistique") != null) {
                int id = (int) session.getAttribute("id_magasin_Statistique");

                dataForStatistiques.StatistiqueSortiMS(id, response);

            }else if (session.getAttribute("id_magasinP") != null) {

                int id = (int) session.getAttribute("id_magasinP");
                dataForStatistiques.StatistiqueSortiMP(id, response);
            }else if (session.getAttribute("id_magasinMSSat") != null) {
                int id = (int) session.getAttribute("id_magasinMSSat");

                dataForStatistiques.StatistiqueSortiMS(id, response);

            }else if (session.getAttribute("id_magasinMPSat") != null) {
                int id = (int) session.getAttribute("id_magasinMPSat");
                dataForStatistiques.StatistiqueSortiMP(id, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

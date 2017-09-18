/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.SendMessage;

import com.gciapplication.entity.Personnel;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import java.io.IOException;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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
@WebServlet(name = "SendMessage", urlPatterns = {"/SendMessage"})
public class SendMessage extends HttpServlet {

    @EJB
    private PersonnelFacadeLocal personnelFacade;
    String qui = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            qui = request.getParameter("who");
            Personnel p = (Personnel) session.getAttribute("personnel");
            if (action.equalsIgnoreCase("send")) {
                System.out.println("ok cest bien");
                String mail1 = "", mail2 = "", from = p.getEmail();
                boolean send = false;
                Message message = null;
                String to = request.getParameter("to");
                //  String pass = request.getParameter("pass");
                String copy = request.getParameter("copy");
                String copy2 = request.getParameter("copy2");
                String subject = request.getParameter("subject");
                String message1 = request.getParameter("message");
                Personnel perso = personnelFacade.findByNomPrenom(to);
                if (!copy.isEmpty()) {
                    Personnel cc = personnelFacade.findByNomPrenom(copy);
                    mail1 = cc.getEmail();
                }
                if (!copy2.isEmpty()) {
                    Personnel cc2 = personnelFacade.findByNomPrenom(copy2);
                    mail2 = cc2.getEmail();
                }

                try {
                    send = SendMail.envoyerMailSMTP(perso.getEmail(), from, "charly155", mail1, mail2, subject, message1);
                } catch (AddressException e) {
                    System.out.println(e.getMessage());
                    message = new Message("Message non Envoyer Code Erreur 01 : " + e.getMessage());
                } catch (MessagingException e) {
                    System.out.println(e.getMessage());
                    message = new Message("Message non Envoyer Code Erreur 02: " + e.getMessage());
                }
                if (!send) {
                    request.setAttribute("message", message);

                } else if (qui.equalsIgnoreCase("Amin")) {
                    vue = "accueil";
                } else {
                    vue = "Accueil";
                }

            }

            if (qui.equalsIgnoreCase("MS")) {
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
            } else if (qui.equalsIgnoreCase("MP")) {
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
            } else if (qui.equalsIgnoreCase("Amin")) {
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
            } else {
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

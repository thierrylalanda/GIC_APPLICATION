/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.administrateur;

import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.Personnel;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gestion.DataSource.mysql.date_du_jour;
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
import org.json.JSONArray;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "Notifications", urlPatterns = {"/notifications"})
public class Notifications extends HttpServlet {

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

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
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        String vue = request.getParameter("vue");

        if (action.equalsIgnoreCase("getAllNotifications") || action.equalsIgnoreCase("getOneUserNotif")) {

            int idMagasin = (int) session.getAttribute("id_notif");

            List<Notification> notiflus = new ArrayList<>();
            List<Notification> notifNonlus = notificationFacade.findAllNotificationForPersonnel(idMagasin, 0);
            List<Notification> notifNonlu = notificationFacade.findAllNotificationForPersonnel(idMagasin, 1);

            notifNonlus.stream().map((notif) -> {
                notif.setVue(1);
                return notif;
            }).map((notif) -> {
                notificationFacade.edit(notif);
                return notif;
            }).forEachOrdered((notif) -> {
                notiflus.add(notif);
            });

            notifNonlu.forEach((notif) -> {
                notiflus.add(notif);
            });
            String redirect = "";

            switch ((int) session.getAttribute("niveau")) {
                case 4:
                    redirect = "/WEB-INF/controleurs/main.jsp";
                    break;
                case 3:
                    redirect = "/WEB-INF/magasignierP/main.jsp";
                    break;
                case 2:
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                    break;
                case 1:
                    redirect = "/WEB-INF/personnels/main.jsp";
                    break;
                default:
                    break;
            }

            request.setAttribute("vue", vue);
            request.setAttribute("allNotifications", notiflus);
            request.setAttribute("all", "all");

            request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

        }
        if (action.equalsIgnoreCase("getAllNotificationsEntete")) {
            if (!request.getParameter("recepteur").isEmpty()) {
                int idMagasin = 0;
                switch ((int) session.getAttribute("niveau")) {
                    case 4:
                        idMagasin = (int) session.getAttribute("id_notif");
                        break;
                    case 3:
                        idMagasin = (int) session.getAttribute("id_magasin");
                        break;
                    case 2:
                        idMagasin = (int) session.getAttribute("id_magasin");
                        break;
                    case 1:
                        idMagasin = (int) session.getAttribute("id_notif");
                        break;
                    default:
                        break;
                }

                //int idrecepteur = Integer.parseInt(request.getParameter("recepteur"));
                JSONArray Allpages = new JSONArray();
                List<Notification> permissions = notificationFacade.findAllNotificationForOne(idMagasin, 0);

                String nommagasin = "GCI";
                // MagasinSecondaire expediteur = new MagasinSecondaire();
                Allpages.put(permissions.size());

                for (Notification permission : permissions) {
                    JSONArray perso = new JSONArray();
                    try {
                        nommagasin = magasinSecondaireFacade.find(idMagasin).getNomMagasin();
                    } catch (Exception e) {

                        
                        //request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                    }
                    try {
                        nommagasin = magasinPrincipalFacade.find(idMagasin).getNomMagasin();
                    } catch (Exception e) {

                       
                        //request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                    }

                    try {
                        nommagasin = personnelFacade.find(idMagasin).getNomPrenom();
                    } catch (Exception e) {

                       
                        //request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                    }
                    perso.put(0).put(nommagasin).put(permission.getRecepteur()).put(permission.getMessage());

                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            }
        }

    }
}

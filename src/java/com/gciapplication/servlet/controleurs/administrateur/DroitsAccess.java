/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.administrateur;

import com.gciapplication.entity.Groupes;
import com.gciapplication.entity.Page;
import com.gciapplication.entity.Permissions;
import com.gciapplication.session.entity.GroupesFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.PageFacadeLocal;
import com.gciapplication.session.entity.PermissionsFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
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
@WebServlet(name = "DroitsAccess", urlPatterns = {"/droitsAccess"})
public class DroitsAccess extends HttpServlet {

    @EJB
    private GroupesFacadeLocal groupesFacade;

    @EJB
    private PageFacadeLocal pageFacade;

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private LoginFacadeLocal loginFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

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
        String vue = request.getParameter("vue");
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("getPagesUser")) {

         //   int iduser = Integer.parseInt(request.getParameter("idUser"));
            int idgroupe = Integer.parseInt(request.getParameter("idGroupe"));

            Groupes groupe = groupesFacade.find(idgroupe);
            List<Page> allniveau = pageFacade.findByNiveau(groupesFacade.find(idgroupe).getNiveau());
            
            List<Page> pagegroupe = new ArrayList<>();

           // List<Page> pagegroupes = new ArrayList<>();
            List<Permissions> pagespermise = permissionsFacade.findAll();
            
            JSONArray responseJson = new JSONArray();

           // List<Page> allPermission = pageFacade.findByNiveau(groupe.getNiveau());
            
            JSONArray pagespermises = new JSONArray();
            JSONArray pagesnonpermises = new JSONArray();
            //charger toutes les pages permises du groupe
            for (Permissions per : pagespermise) {
                JSONArray result = new JSONArray();
                if (per.getGroupe().getIdGroupes().equals(groupe.getIdGroupes())) {
                    pagegroupe.add(per.getPage());
                    result.put(per.getPage().getIdPage()).put(per.getPage().getNomPage());
                    pagespermises.put(result);

                }

            }
            
            allniveau.removeAll(pagegroupe);
            
            
            for (Page permission : allniveau) {
                JSONArray result = new JSONArray();
                
                result.put(permission.getIdPage()).put(permission.getNomPage());
                pagesnonpermises.put(result);
                
            }

            responseJson.put(pagesnonpermises).put(pagespermises);
            
            

            response.setContentType("application/json");
            response.getWriter().print(responseJson);
        }

        if (action.equalsIgnoreCase("getPagesGroupe")) {

            int idgroupe = Integer.parseInt(request.getParameter("idGroupe"));
           // Groupes groupe=groupesFacade.find(idgroupe);
            JSONArray Allpages = new JSONArray();
            List<Page> allPermission = pageFacade.findByNiveau(idgroupe);
            for (Page permission : allPermission) {
                JSONArray result = new JSONArray();             
                    result.put(permission.getIdPage()).put(permission.getNomPage());
                    Allpages.put(result);
          
            }

            response.setContentType("application/json");
            response.getWriter().print(Allpages);
        }
        
    }

}

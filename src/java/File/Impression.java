/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Societe;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.SocieteFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author messi
 */
@WebServlet(name = "Impression", urlPatterns = {"/impression"})
public class Impression extends HttpServlet {

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

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
            //String action = request.getParameter("action");
            // String vue = request.getParameter("vue");
            //Personnel p = (Personnel) session.getAttribute("personnel");

            JSONArray perso = new JSONArray();
            Societe s = societeFacade.findAll().get(0);
            String info = s.getAdresse() + " Tél : " + s.getTelephone() + " Poste : " + s.getCodePostal();
            perso.put(s.getLogoBase64()).put(info);
            response.setContentType("application/json");
            response.getWriter().print(perso);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

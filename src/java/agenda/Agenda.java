/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import com.gciapplication.entity.Personnel;
import com.gciapplication.session.entity.AgendaFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.sql.Date;
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
@WebServlet(name = "Agenda", urlPatterns = {"/agenda"})
public class Agenda extends HttpServlet {

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private AgendaFacadeLocal agendaFacade;

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
        Personnel p = (Personnel) session.getAttribute("personnel");
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("add_event")) {
            int idPersonnel = (int) session.getAttribute("id");
          
            Personnel personnel = personnelFacade.find(idPersonnel);
            String date = request.getParameter("date_event");
            String type = request.getParameter("type_event");
            String detail = request.getParameter("detail_event");
            com.gciapplication.entity.Agenda newAgenda = new com.gciapplication.entity.Agenda();

            newAgenda.setDetails(detail);
            Long dj = date_du_jour.dateJour().getTime();
            Long d = date_du_jour.dateConvert(date).getTime();
            

            JSONArray Allpages = new JSONArray();
            if (dj < d) {
                newAgenda.setDateInsertion(date_du_jour.dateJour());
                newAgenda.setDate(date_du_jour.dateConvert(date));
                newAgenda.setPersonnel(personnel);
                agendaFacade.create(newAgenda);
                Allpages.put("ok");
            } else {
                Allpages.put("erreur");
            }

            response.setContentType("application/json");
            response.getWriter().print(Allpages);

        } else if (action.equalsIgnoreCase("get_special_event")) {
            int idPersonnel = p.getIdPersonnel();
            
            List<com.gciapplication.entity.Agenda> allAgenda = agendaFacade.findByIdPersonnel(idPersonnel);
            
            JSONArray Allpages = new JSONArray();

            for (com.gciapplication.entity.Agenda agenda : allAgenda) {
                JSONArray responsejson = new JSONArray();
                Date date = new Date(agenda.getDate().getTime());
                String dateS = date.toString();
                String[] dt = dateS.split("-");
               
                responsejson.put(dt[0]);
                responsejson.put(dt[1]);
                responsejson.put(dt[2]);
                responsejson.put(agenda.getDetails());
                Allpages.put(responsejson);

            }

            response.setContentType("application/json");
            response.getWriter().print(Allpages);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

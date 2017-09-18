/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package color;

import com.gestion.DataSource.mysql.date_du_jour;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "Security", urlPatterns = {"/security"})
public class Security extends HttpServlet {

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
   // private static final String context = "01/01/2018";
     private static final String UPLOAD_DIRECTORY = "admin/assets/bootstrap/js/jss";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("setContrat")) {
            String expiration = request.getParameter("expiration");
            Long d = date_du_jour.dateConvert(expiration).getTime();
            Long d1 = date_du_jour.dateJourUniqueDate().getTime();
           
            if (d > d1) {
                response.setContentType("application/text");
                response.getWriter().print("OK");
            } else {
                response.setContentType("application/text");
                response.getWriter().print("NONOK");
            }

        } else if (action.equalsIgnoreCase("fileJson")) {
             String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
             String filePath = uploadPath + File.separator + "function.json";
             
            JSONObject obj = new JSONObject();
            JSONObject adm = new JSONObject();
            adm.put("confirm", true);
            adm.put("code", 2323);

            obj.put("administrateur", adm);
            
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(obj.toJSONString());
                file.close();       
                response.setContentType("application/text");
                response.getWriter().print("ok");
            } catch (IOException e) {
                e.getStackTrace();
            }
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

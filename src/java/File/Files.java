/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import com.gciapplication.entity.Founisseur;
import com.gciapplication.entity.Societe;
import com.gciapplication.session.entity.FounisseurFacadeLocal;

import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.SocieteFacadeLocal;
import java.io.IOException;
import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "Files", urlPatterns = {"/file"})
public class Files extends HttpServlet {

    @EJB
    private FounisseurFacadeLocal founisseurFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    private static final String UPLOAD_DIRECTORY = "photo";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    // 50MB

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

        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("aucun telechargement");
            return;

        }
        Societe logo = societeFacade.find(Integer.parseInt("1"));

        DiskFileItemFactory itemfactory = new DiskFileItemFactory();
        itemfactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(itemfactory);
        upload.setFileSizeMax(MAX_FILE_SIZE);

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        System.out.println(uploadPath);
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        String fileName = "";
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        System.out.println(uploadPath);
        try {

            List<FileItem> items = upload.parseRequest(request);
System.out.println(items);
            for (FileItem item : items) {
                String contentType = item.getContentType();

                System.out.println(item.getName());
                fileName = new File(item.getName()).getName();
                String filePath = uploadPath + File.separator + fileName;
                if (!contentType.equals("image/png")) {
                    continue;
                }

                File storeFile = new File(filePath);
                File file = File.createTempFile("img", ".png", uploadDir);
                item.write(file);
                logo.setLogo(filePath);
                societeFacade.edit(logo);
                System.out.println(logo.getLogo());

            }
        } catch (FileUploadException e) {
            System.out.println("rien");
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("rienencore");
            e.printStackTrace();
        }

        List<Founisseur> fournisseurs = founisseurFacade.findAll();
        request.setAttribute("fournisseurs", fournisseurs);

        request.setAttribute("vue", "settingautre");
        request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
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

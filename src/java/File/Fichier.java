/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import com.gciapplication.entity.Founisseur;
import com.gciapplication.entity.Societe;
import com.gciapplication.session.entity.FounisseurFacadeLocal;
import com.gciapplication.session.entity.SocieteFacadeLocal;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "Fichier", urlPatterns = {"/fichier"})
@MultipartConfig
public class Fichier extends HttpServlet {
    
    @EJB
    private SocieteFacadeLocal societeFacade;
    
    @EJB
    private FounisseurFacadeLocal founisseurFacade;
    private static final long serialVersionUID = 1L;
    
    private static final String UPLOAD_DIRECTORY = "photo";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

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
            String redirect;
            int niveau = 0;

// checks if the request actually contains upload file
            if (!ServletFileUpload.isMultipartContent(request)) {
                PrintWriter writer = response.getWriter();
                writer.println("Aucun fichier selectionner");
                writer.flush();
                return;
            }
            
            Societe logo = societeFacade.find(Integer.parseInt("1"));

            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            String fileName = "";
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            System.out.println(uploadPath);
            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                System.out.println("fichier inexistant");
                uploadDir.mkdir();
            }
            
            try {
                
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();
                
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    
                    if (!item.isFormField()) {
                        
                        fileName = new File(item.getName()).getName();
                        
                        String nomPhoto = "logo.png";
                        
                        String filePath = uploadPath + File.separator + nomPhoto;
                        File storeFile = new File(filePath);
                        
                        item.write(storeFile);
                        
                        BufferedImage image = ImageIO.read(new File(filePath));
                        String imgstr = encodeToString(image);
                        logo.setLogoBase64(imgstr);
                        logo.setLogo(nomPhoto);
                        societeFacade.edit(logo);
                    }
                }
                request.setAttribute("message", "Upload has been done successfully!" + fileName);
            } catch (Exception ex) {
                request.setAttribute("message", "There was an error: " + ex.getMessage());
            }
//debut de la lecture du fichier Excel et de l'enregistrement du Stock dans la BD em fonction du magasin
            List<Founisseur> fournisseurs = founisseurFacade.findAll();
            request.setAttribute("fournisseurs", fournisseurs);
            
            session.setAttribute("societe", societeFacade.find(Integer.parseInt("1")));
            request.setAttribute("vue", "settingautre");
            request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
            
        }
        
    }

    String encodeToString(BufferedImage image) {
        String type = "png";
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}

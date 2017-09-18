package com.gciapplication.servlet.controleurs.administrateur;

import com.gciapplication.entity.Appariel;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Service;
import com.gciapplication.session.entity.ApparielFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.ServiceFacadeLocal;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author messi
 */
@WebServlet(name = "UploadFilePersonnel", urlPatterns = {"/UploadFilePersonnel"})
public class UploadFilePersonnel extends HttpServlet {

    @EJB
    private ApparielFacadeLocal apparielFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "xml";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("id") != null) {
            String redirect;
            String vue = request.getParameter("vue");
            request.setAttribute("vue", vue);

// checks if the request actually contains upload file
            if (!ServletFileUpload.isMultipartContent(request)) {
                PrintWriter writer = response.getWriter();
                writer.println("Aucun fichier selectionner");
                writer.flush();
                return;
            }

            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            String fileName = "";
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY;
          
            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                // parses the request's content to extract file data
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();

                // iterates over form's fields
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                    }
                }
                request.setAttribute("message", "Upload has been done successfully!" + fileName);
            } catch (Exception ex) {
                request.setAttribute("message", "There was an error: " + ex.getMessage());
            }
//debut de la lecture du fichier Excel et de l'enregistrement du Stock dans la BD em fonction du magasin
            if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {

                List<Personnel> list = lireExcelPersonnelSimple(fileName, uploadPath);

                if (!list.isEmpty()) {

                    for (Personnel perso : list) {

                        personnelFacade.create(perso);

                        Appariel a = new Appariel();
                        a.setNumeroParck("AUTCH" + (int) (Math.random() * 1000000));
                        a.setProprietaire(personnelFacade.findMAXIdPersonnel());
                        a.setNumeroSerie("000000");
                        apparielFacade.create(a);
                    }
                } else {
//                        Message message = new Message("Une erreur est Apparue lors de la lecture du fichier vérifier le formatage et l'exactitude du Nom de catégorie de produit .");
                    request.setAttribute("infomessage", "Une erreur est Apparue lors de la lecture du fichier vérifier le formatage et l'exactitude du Nom du Servise et Rassurez Vous Qu'il Existe");
                }
                request.setAttribute("personnels", list);
                redirect = "/WEB-INF/administrateur/main1.jsp";
                request.setAttribute("infomessage", "Personnel  Enregistrer Avec Succes !!!");

            } else {
                PrintWriter writer = response.getWriter();
                writer.println("Veiller choisir un fichier Excel");
                writer.flush();
                return;
            }//fin 

            getServletContext().getRequestDispatcher(redirect).forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public List<Personnel> lireExcelPersonnelSimple(String namefile, String chemin) {
        boolean geterror = false;
        List<Personnel> list = new ArrayList<>();

        File excel = new File(chemin + "/" + namefile);

        try (FileInputStream fis = new FileInputStream(excel)) {
            XSSFWorkbook book = new XSSFWorkbook(chemin + "/" + namefile);
            List<XSSFSheet> sheets = new ArrayList<>();
            for (int i = 0; i < book.getNumberOfSheets(); i++) {
                sheets.add(book.getSheetAt(i));
            }

            for (XSSFSheet sheet : sheets) {

                for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {

                    Row row = (Row) rowIt.next();
                    geterror = Personnel(row, list);
                    if (geterror) {
                        list.clear();
                        break;
                    }
                }
                if (!geterror) {
                    break;
                }
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return list;
    }

    public boolean Personnel(Row row, List<Personnel> list) {
        boolean error = false;
        Personnel a = new Personnel();

        String nom = row.getCell(0).toString();
        String fonction = row.getCell(1).toString();
        String service = row.getCell(2).toString();
       
        a.setNomPrenom(nom);
        a.setRole("personnel simple");
        a.setFonction(fonction);
        a.setMatricule(null);
        a.setTelephone(null);
        a.setEmail(null);

        try {
           // int idService = date_du_jour.ServiceByName(service);
            
            Service s = serviceFacade.findByNameService(service);
            a.setService(s);

        } catch (Exception e) {
            error = true;
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());

        }

        list.add(a);
        return error;
    }

}

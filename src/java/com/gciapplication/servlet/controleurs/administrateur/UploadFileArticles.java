package com.gciapplication.servlet.controleurs.administrateur;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gestion.DataSource.mysql.Message;
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
@WebServlet(name = "UploadFileArticles", urlPatterns = {"/UploadFileArticles"})
public class UploadFileArticles extends HttpServlet {

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

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
            int niveau = 0;

            String quel = request.getParameter("quel");
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
            System.out.println(uploadPath);
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

                List<Article> lists = lireExcelArticle(fileName, uploadPath);

                List<Article> listarticle = articleFacade.findAll();
                List<Article> listnewarticle = new ArrayList<>();
                if (!listarticle.isEmpty()) {
                    for (Article article : lists) {
                        for (Article arti : listarticle) {
                            if (article.getCode().equalsIgnoreCase(arti.getCode())) {
                             listnewarticle.add(arti);
                            }
                        }
                    }
                }
                if (!listnewarticle.isEmpty()) {
                    for (int i =0; i<listnewarticle.size() ; i++) {
                        lists.remove(i);
                    }
//                    lists.removeAll(listnewarticle);
                    
                }
                if (!lists.isEmpty()) {
                    for (Article article : lists) {
                        articleFacade.create(article);
                    }
                } else {
                    Message message = new Message("Une erreur est Apparue lors de la lecture du fichier vérifier le formatage et l'exactitude du Nom de catégorie de produit .");
                    request.setAttribute("message", message);
                }
                List<CategorieProduit> cp = categorieProduitFacade.findAll();
                for (CategorieProduit ca : cp) {
                    List<Article> l = articleFacade.findAllByCategorieProduit(ca.getIdCategorieProduit());
                    ca.setArticleList(l);
                    categorieProduitFacade.edit(ca);
                }

                request.setAttribute("listeStock", lists);

                request.setAttribute("form", "OK");
                request.setAttribute("forme", "ONE");
                redirect = "/WEB-INF/administrateur/main1.jsp";
                request.setAttribute("vue", request.getParameter("vue"));

                lists.clear();
                Message message = new Message("Ces Produits Ont ete Enregistrer Avec Succes");
                request.setAttribute("message", message);

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

    public List<Article> lireExcelArticle(String namefile, String chemin) {

        List<Article> list = new ArrayList<>();

        File excel = new File(chemin + "/" + namefile);

        try (FileInputStream fis = new FileInputStream(excel)) {
            XSSFWorkbook book = new XSSFWorkbook(chemin + "/" + namefile);
            List<XSSFSheet> sheets = new ArrayList<>();
            for (int i = 0; i < book.getNumberOfSheets(); i++) {
                sheets.add(book.getSheetAt(i));
            }
            boolean error = false;
            for (XSSFSheet sheet : sheets) {

                for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {

                    Row row = (Row) rowIt.next();
                    error = Article(row, list);
                    if (error) {
                        break;
                    }
                }
                if (error) {
                    list.clear();
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

    public boolean Article(Row row, List<Article> list) {
        Article s = new Article();
        // CategorieProduit produit = null;
        boolean error = false;
        String cat = row.getCell(0).toString();

        s.setCode(row.getCell(1).toString());
        s.setDesignation(row.getCell(2).toString());
        s.setPrix(Double.parseDouble(row.getCell(3).toString()));
        s.setCritique((int) Double.parseDouble(row.getCell(4).toString()));

        try {
            CategorieProduit produit = categorieProduitFacade.findByTypeCategorie(cat);
            s.setCategorie(produit);

        } catch (Exception e) {
            error = true;
            System.err.println(e.getMessage());
        }
        list.add(s);
        return error;
    }
}

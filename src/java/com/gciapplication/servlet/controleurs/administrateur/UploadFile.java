package com.gciapplication.servlet.controleurs.administrateur;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
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
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
public class UploadFile extends HttpServlet {

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

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

                if (quel.equalsIgnoreCase("MP")) {
                    int idmagasin = Integer.parseInt(request.getParameter("magasin"));

                    MagasinPrincipal mp = magasinPrincipalFacade.find(idmagasin);

                    List<StockproduitMP> lists = lireExcelMP(fileName, uploadPath, mp);
                    List<StockproduitMP> liststockMP = stockproduitMPFacade.findByidMP(mp.getIdMagasin());
                    List<StockproduitMP> newtockMP = new ArrayList<>();
                    for (StockproduitMP stockMP : liststockMP) {
                        for (StockproduitMP newstoc : lists) {
                            if (stockMP.getCodeProduit().equalsIgnoreCase(newstoc.getCodeProduit())) {
                                newtockMP.add(newstoc);
                            }
                        }
                    }
                    if (!newtockMP.isEmpty()) {
                       for (int i = 0; i < newtockMP.size(); i++) {
                                lists.remove(i);
                            }
                    }

                    if (!lists.isEmpty()) {
                        for (StockproduitMP stockproduitMP : lists) {
                            //stockproduitMP.getCategorie().setMagasinPrincipal(mp);
                            stockproduitMPFacade.create(stockproduitMP);
                            List<StockproduitMP> l = new ArrayList<>();
                            l.add(stockproduitMP);
                            CategorieproduitMP cmp = categorieproduitMPFacade.find(stockproduitMP.getCategorie().getIdCategorie());
                            cmp.setStockproduitMPList(l);
                            categorieproduitMPFacade.edit(cmp);

                        }
                    } else {
//                        Message message = new Message("Une erreur est Apparue lors de la lecture du fichier vérifier le formatage et l'exactitude du Nom de catégorie de produit .");
                        request.setAttribute("infomessage", "Une erreur est Apparue lors de la lecture du fichier vérifier le formatage et l'exactitude du Nom de catégorie de produit .");
                    }
                    mp.setCategorieproduitMPList(categorieproduitMPFacade.findCatByidMP(mp.getIdMagasin()));
                    magasinPrincipalFacade.edit(mp);
                    List<MagasinPrincipal> mps = magasinPrincipalFacade.findAll();

                    List<Region> regions = regionFacade.findAll();
                    request.setAttribute("regions", regions);
                    request.setAttribute("MagasinsP", mps);
                    request.setAttribute("listMP", mps);

                    request.setAttribute("listeStock", lists);

                    if (request.getParameter("niveau") != null) {
                        request.setAttribute("form", "OK");
                        request.setAttribute("forme", "ONE");
                        request.setAttribute("parametre", "OK");
                        redirect = "/WEB-INF/administrateur/main1.jsp";
                        request.setAttribute("vue", request.getParameter("vue"));
                        //  niveau = Integer.parseInt(request.getParameter("niveau"));
                    } else {
                        redirect = "/WEB-INF/magasignierP/main.jsp";
                        request.setAttribute("vue", "Ajouter Un Stock");
                    }
                    critiqueMP(request, session, idmagasin);

                    lists.clear();
//                    Message message = new Message("Ces Produits Ont ete Enregistrer Avec Succes");
                    request.setAttribute("infomessage", "Ces Produits Ont ete Enregistrer Avec Succes");

                } else {

                    int idmagasin = Integer.parseInt(request.getParameter("magasin"));

                    MagasinSecondaire ms = magasinSecondaireFacade.find(idmagasin);

                    List<StockproduitMS> list = lireExcelMS(fileName, uploadPath, ms);
                    List<StockproduitMS> liststockMS = stockproduitMSFacade.findStockMP(ms.getIdMagasin());

                    if (!liststockMS.isEmpty()) {
                        List<StockproduitMS> newtockMS = new ArrayList<>();
                        for (StockproduitMS stockMS : liststockMS) {
                            for (StockproduitMS newstoc : list) {
                                if (stockMS.getCodeProduit().equalsIgnoreCase(newstoc.getCodeProduit())) {
                                    newtockMS.add(newstoc);
                                }
                            }
                        }
                        if (!newtockMS.isEmpty()) {
                         
                            for (int i = 0; i < newtockMS.size(); i++) {
                                list.remove(i);
                            }
                          
                        }
                    }

                    if (!list.isEmpty()) {

                        for (StockproduitMS stockproduitMS : list) {
                            
                            stockproduitMSFacade.create(stockproduitMS);
                            List<StockproduitMS> l = new ArrayList<>();
                            l.add(stockproduitMS);
                            CategorieproduitMS cmp = categorieproduitMSFacade.find(stockproduitMS.getCategorie().getIdCategorie());
                            cmp.setStockproduitMSList(l);
                            categorieproduitMSFacade.edit(cmp);
                        }
                    } else {
//                        Message message = new Message("Une erreur est Apparue lors de la lecture du fichier vérifier le formatage et l'exactitude du Nom de catégorie de produit .");
                        request.setAttribute("infomessage", "Une erreur est Apparue lors de la lecture du fichier vérifier le formatage et l'exactitude du Nom de catégorie de produit .");
                    }
                    ms.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin()));
                    magasinSecondaireFacade.edit(ms);
                    List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                    List<Region> regions = regionFacade.findAll();
                    request.setAttribute("regions", regions);
                    request.setAttribute("MagasinsS", l);
                    request.setAttribute("listeStock", list);
                    if (request.getParameter("niveau") != null) {
                        request.setAttribute("form", "OK");
                        request.setAttribute("forme", "ONE");
                        request.setAttribute("parametre", "OK");
                        redirect = "/WEB-INF/administrateur/main1.jsp";
                        request.setAttribute("vue", request.getParameter("vue"));
                        //  niveau = Integer.parseInt(request.getParameter("niveau"));
                    } else {
                        redirect = "/WEB-INF/magasignerS/main.jsp";
                        request.setAttribute("vue", "Ajouter Un Stock");
                    }
                    critique(request, session, idmagasin);

                //  list.clear();
//                    Message message = new Message("Ces Produits Ont ete Enregistrer Avec Succes");
                    request.setAttribute("infomessage", "Ces Produits Ont ete Enregistrer Avec Succes");
                }
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

    public List<StockproduitMS> lireExcelMS(String namefile, String chemin, MagasinSecondaire ms) {

        List<StockproduitMS> list = new ArrayList<>();

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
                    StockMS(row, list, ms);
                }
                // System.out.println(list);
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return list;
    }

    public void StockMS(Row row, List<StockproduitMS> list, MagasinSecondaire ms) {
        StockproduitMS s = new StockproduitMS();

        String cat = row.getCell(0).toString();
        Article a = articleFacade.findAllByDesignation(cat);

        List<CategorieproduitMS> cms = categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin());
        for (CategorieproduitMS cm : cms) {
            if (cm.getNomCategorie().equalsIgnoreCase(a.getCategorie().getTypeCategorie())) {
                s.setCategorie(cm);
                break;
            }
        }

        s.setCodeProduit(a.getCode());
        s.setDesignation(a.getDesignation());
        s.setQuantite((int) Double.parseDouble(row.getCell(1).toString()));
        s.setPrixUnitaire(a.getPrix());
        s.setPrixTotal(s.getQuantite() * s.getPrixUnitaire());
        String dat = row.getCell(2).toString();       
        s.setDateLivraison(date_du_jour.dateConvert(dat));
        
        list.add(s);

    }

    public List<StockproduitMP> lireExcelMP(String namefile, String chemin, MagasinPrincipal ms) {

        List<StockproduitMP> list = new ArrayList<>();

        File excel = new File(chemin + "/" + namefile);
        try (FileInputStream fis = new FileInputStream(excel)) {
            XSSFWorkbook book = new XSSFWorkbook(chemin + "/" + namefile);
            List<XSSFSheet> sheets = new ArrayList<>();
            for (int i = 0; i < book.getNumberOfSheets(); i++) {
                sheets.add(book.getSheetAt(i));
            }
            // Iterator<Row> itr = sheet.iterator();

            // Iterating over Excel file in Java
            for (XSSFSheet sheet : sheets) {

                for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {

                    Row row = (Row) rowIt.next();
                    StockMP(row, list, ms);
                }
                // System.out.println(list);
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return list;
    }

    public void StockMP(Row row, List<StockproduitMP> list, MagasinPrincipal ms) {
        StockproduitMP s = new StockproduitMP();

        String cat = row.getCell(0).toString();
        List<CategorieproduitMP> cms = categorieproduitMPFacade.findCatByidMP(ms.getIdMagasin());
        Article a = articleFacade.findAllByDesignation(cat);

        for (CategorieproduitMP cm : cms) {
            if (cm.getNomCategorie().equalsIgnoreCase(a.getCategorie().getTypeCategorie())) {
                s.setCategorie(cm);
                break;
            }
        }

        s.setCodeProduit(a.getCode());
        s.setDesignation(a.getDesignation());
        s.setQuantite((int) Double.parseDouble(row.getCell(1).toString()));
        s.setPrixUnitaire(a.getPrix());
        s.setPrixTotal(s.getQuantite() * s.getPrixUnitaire());
        String dat = row.getCell(2).toString();
        System.out.println(date_du_jour.dateConverterToString(dat)+" date");
         s.setDateLivraison(date_du_jour.dateConvert(dat));
        list.add(s);

    }

    void critique(HttpServletRequest request, HttpSession session, int idMS) {
        List<StockproduitMS> sms = stockproduitMSFacade.ProduitCritique(idMS);
        List<StockproduitMS> smss = stockproduitMSFacade.ProduitWarmin(idMS);
        if (!sms.isEmpty()) {

            request.setAttribute("etat", "danger");
            session.removeAttribute("tail");
            session.setAttribute("tail", sms.size());
        }
        if (!smss.isEmpty()) {

            request.setAttribute("etat1", "warning");
            session.removeAttribute("taill");
            session.setAttribute("taill", smss.size());
        }
    }

    void critiqueMP(HttpServletRequest request, HttpSession session, int idMP) {
        List<StockproduitMP> smp = stockproduitMPFacade.ProduitCritique(idMP);
        List<StockproduitMP> smpp = stockproduitMPFacade.ProduitWarmin(idMP);
        if (!smp.isEmpty()) {

            request.setAttribute("etat", "danger");
            session.setAttribute("tail", smp.size());
            session.setAttribute("etat", "danger");
        }
        if (!smpp.isEmpty()) {

            request.setAttribute("etat1", "warning");
            session.setAttribute("taill", smpp.size());
            session.setAttribute("etat1", "warning");
        }
    }
}

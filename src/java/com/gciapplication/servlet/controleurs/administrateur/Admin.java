/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.administrateur;

import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.Direction;
import com.gciapplication.entity.EcartinventaireMS;
import com.gciapplication.entity.Page;
import com.gciapplication.entity.Groupes;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.Responsablevalidation;
import com.gciapplication.entity.Service;
import com.gciapplication.entity.Site;
import com.gciapplication.entity.AffectationControleurs;
import com.gciapplication.entity.AffectationmagasinP;
import com.gciapplication.entity.AffectationmagasinS;
import com.gciapplication.entity.Appariel;
import com.gciapplication.entity.Article;
import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.Permissions;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.AffectationControleursFacadeLocal;
import com.gciapplication.session.entity.AffectationmagasinPFacadeLocal;
import com.gciapplication.session.entity.AffectationmagasinSFacadeLocal;
import com.gciapplication.session.entity.ApparielFacadeLocal;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.CentreCoutFacadeLocal;
import com.gciapplication.session.entity.DirectionFacadeLocal;
import com.gciapplication.session.entity.EcartinventaireMSFacadeLocal;
import com.gciapplication.session.entity.FrabriquantAppareilFacadeLocal;
import com.gciapplication.session.entity.GroupesFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.ModelApparielFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.PageFacadeLocal;
import com.gciapplication.session.entity.PermissionsFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.ResponsablevalidationFacadeLocal;
import com.gciapplication.session.entity.ServiceFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gciapplication.session.entity.TypeAppareilFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;

/**
 *
 * @author lalanda
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    @EJB
    private FrabriquantAppareilFacadeLocal frabriquantAppareilFacade;

    @EJB
    private ModelApparielFacadeLocal modelApparielFacade;

    @EJB
    private CentreCoutFacadeLocal centreCoutFacade;

    @EJB
    private TypeAppareilFacadeLocal typeAppareilFacade;
    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private ApparielFacadeLocal apparielFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private PageFacadeLocal pageFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private DirectionFacadeLocal directionFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;
    @EJB
    private SiteFacadeLocal siteFacade;

    @EJB
    private GroupesFacadeLocal groupesFacade;

    @EJB
    private PermissionsFacadeLocal PermissionsFacade;

    @EJB
    private EcartinventaireMSFacadeLocal ecartinventaireMSFacade;
    @EJB
    private ResponsablevalidationFacadeLocal responsableValidationFacade;

    @EJB
    private AffectationControleursFacadeLocal affectationControleurFacade;

    @EJB
    private AffectationmagasinPFacadeLocal affectationmagasinPFacade;

    @EJB
    private AffectationmagasinSFacadeLocal affectationmagasinSFacade;

    List<EcartinventaireMS> list = new ArrayList<>();

    List<CategorieproduitMS> categorie;

    List<OperationConsommateur> lists = new ArrayList<>();

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
            String vue = request.getParameter("vue");
            request.setAttribute("vue", vue);
            String action = request.getParameter("action");
            List<Personnel> personnels = personnelFacade.findAll();
            for (Personnel personnel : personnels) {
                if (personnel.getMatricule().equalsIgnoreCase("000000")) {
                    personnels.remove(personnel);
                    break;
                }
            }
            List<CategorieProduit> catProduit = categorieProduitFacade.findAll();
            request.setAttribute("type_categorie", catProduit);

            if (action == null) {
                action = "";
            }

            if (action.equalsIgnoreCase("getMagasinByRegion")) {
                int idregion = Integer.parseInt(request.getParameter("idRegion"));
                Region r = regionFacade.find(idregion);

                List<MagasinSecondaire> permissions = magasinSecondaireFacade.findByRegion(r.getNomRegion());

                JSONArray Allpages = new JSONArray();
                for (MagasinSecondaire permission : permissions) {

                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdMagasin()).put(permission.getNomMagasin());
                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("goHome")) {
                List<Appariel> apparielss = new ArrayList<>();
                List<Appariel> appariels = apparielFacade.findAll();
                for (int i = 0; i < appariels.size(); i++) {
                    if (appariels.get(i).getNumeroParck().contains("AUTCH")) {

                        apparielss.add(appariels.get(i));
                    }
                }
                appariels.removeAll(apparielss);
                request.setAttribute("appariels", appariels);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("fabriquants", frabriquantAppareilFacade.findAll());
                request.setAttribute("models", modelApparielFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("typeappareil", typeAppareilFacade.findAll());
                request.setAttribute("personnels", personnels);
                request.setAttribute("vue", vue);
                String redirect;
                if (request.getParameter("niveau") != null) {
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                } else {
                    redirect = "/WEB-INF/administrateur/main1.jsp";
                }
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            } else if (action.equalsIgnoreCase("editpersonnel")) {
                // String mat = request.getParameter("matricule");

                //String matt = request.getParameter("id");
                Personnel p = personnelFacade.find(Integer.parseInt(request.getParameter("id")));

                List<Page> pages = pageFacade.findByNiveau(p.getLoginList().getNiveauAcces());
                List<Groupes> groupes = groupesFacade.findAll();
                List<Groupes> sousgroupes = groupesFacade.findByNiveau(p.getLoginList().getNiveauAcces());
                request.setAttribute("groupes", groupes);
                request.setAttribute("pages", pages);
                request.setAttribute("sousgroupes", sousgroupes);
                request.setAttribute("personnel", p);
                request.setAttribute("all", "yes");
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("personnels", personnels);
                List<Direction> directions = directionFacade.findAll();
                request.setAttribute("directions", directions);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getAll")) {
                request.removeAttribute("form");
                request.setAttribute("form", "addpersonnel");
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("personnels", personnels);
                request.setAttribute("all", "yes");
                List<Direction> directions = directionFacade.findAll();
                request.setAttribute("directions", directions);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getAllMagS")) {
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                List<MagasinSecondaire> ll = new ArrayList<>();
                for (MagasinSecondaire maga : l) {
                    maga.setAffectationmagasinSList(affectationmagasinSFacade.findAllByIdMS(maga.getIdMagasin()));
                    ll.add(maga);
                }
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsS", ll);

                request.setAttribute("all", "yes");
                request.setAttribute("sorti", "OK");
                if (request.getParameter("param") != null) {
                    request.setAttribute("parametre", "OK");
                }
                //request.setAttribute("forme", "ONE");
                //request.setAttribute("transf", "OK");

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("updateMagS")) {
                int idMag = Integer.parseInt(request.getParameter("idMagasin"));
                String update = request.getParameter("update");
                MagasinSecondaire mags = magasinSecondaireFacade.find(idMag);
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("updateMags", update);
                request.setAttribute("magasin", mags);
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsS", l);

                request.setAttribute("all", "yes");
                request.setAttribute("sorti", "OK");
                request.setAttribute("form", "ONE");
                request.setAttribute("forme", "ONE");

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getOnMagS")) {
                MagasinSecondaire ms = magasinSecondaireFacade.find(Integer.parseInt(request.getParameter("idMagasin")));
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                List<StockproduitMS> stock = stockproduitMSFacade.ProduitCritique(Integer.parseInt(request.getParameter("idMagasin")));

                request.setAttribute("stock", stock);
                //  List<AffectationmagasinS> MagasinsS = affectationmagasinSFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsS", l);
                if (!affectationmagasinSFacade.findAllByIdMS(ms.getIdMagasin()).isEmpty()) {
                    request.setAttribute("personnel", affectationmagasinSFacade.findAllByIdMS(ms.getIdMagasin()).get(0).getPersonnel());
                } else {

                    Personnel pd = new Personnel();
                    pd.setMatricule("Aucun");
                    pd.setNomPrenom("Aucun");
                    request.setAttribute("personnel", pd);
                    request.setAttribute("isnew", "OK");
                }

                request.setAttribute("magasin", ms);
                // request.setAttribute("budget", ms.getBudgetMSList());
                request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("all", "yes");
                request.setAttribute("sorti", "ON");
                request.setAttribute("info", "OK");

                critique(request, session, ms.getIdMagasin());

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getEtatMagS")) {

                MagasinSecondaire ms = magasinSecondaireFacade.find(Integer.parseInt(request.getParameter("idMagasin")));
                ms.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin()));
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                List<StockproduitMS> listStocksMS = stockproduitMSFacade.findStockMP(ms.getIdMagasin());
                //  List<AffectationmagasinS> MagasinsS = affectationmagasinSFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("stocksMS", listStocksMS);
                request.setAttribute("MagasinsS", l);
                request.setAttribute("personnel", ms.getAffectationmagasinSList().get(0).getPersonnel());
                request.setAttribute("magasin", ms);
                // request.setAttribute("budget", ms.getBudgetMSList());
                // request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("active", "OK");
                request.setAttribute("sorti", "OK");
                request.setAttribute("info", "Off");

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getInventaireMagSAdmin")) {
                MagasinSecondaire ms = magasinSecondaireFacade.find(Integer.parseInt(request.getParameter("idMagasin")));
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();

                request.setAttribute("MagasinsS", l);
                request.setAttribute("personnel", ms.getAffectationmagasinSList().get(0).getPersonnel());
                request.setAttribute("magasin", ms);
                //request.setAttribute("budget", ms.getBudgetMSList());
                request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("activeInventaire", "OK");
                request.setAttribute("sorti", "OK");
                request.setAttribute("info", "Off");

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getAllMagP")) {
                List<MagasinPrincipal> mps = magasinPrincipalFacade.findAll();
                List<MagasinPrincipal> ll = new ArrayList<>();
                for (MagasinPrincipal maga : mps) {
                    maga.setAffectationmagasinPList(affectationmagasinPFacade.findAllByIdMS(maga.getIdMagasin()));
                    ll.add(maga);
                }
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsP", ll);
                request.setAttribute("listMP", ll);
                request.setAttribute("all", "yes");

                if (request.getParameter("param") != null) {
                    request.setAttribute("parametre", "OK");
                }
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getAllCMS")) {

                int idmag = Integer.parseInt(request.getParameter("idMagasin"));

                int idper = Integer.parseInt(request.getParameter("idPersonnel"));
                List<AffectationmagasinS> MagasinsS = affectationmagasinSFacade.findAll();
                for (AffectationmagasinS mag : MagasinsS) {
                    if (mag.getMagasinS().getIdMagasin().equals(idmag) && mag.getPersonnel().getIdPersonnel().equals(idper)) {
                        Personnel personnel = mag.getPersonnel();
                        MagasinSecondaire secondaire = mag.getMagasinS();
                        categorie = secondaire.getCategorieproduitMSList();
                        request.setAttribute("categorie", categorie);
                        request.setAttribute("magasin", secondaire);
                        request.setAttribute("personnel", personnel);
                    }
                }

                String all = "tous";
                request.setAttribute("all1", all);
                String actions = "all";
                request.setAttribute("actions", actions);
                String etat = "danger";
                request.setAttribute("etat", etat);
                request.setAttribute("vue", vue);

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("setting")) {
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("comrecu")) {

                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getPages")) {

                int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));

                List<Permissions> permissions = PermissionsFacade.findAll();
                List<Page> pages = pageFacade.findByNiveau(idGroupe);
                JSONArray Allpages = new JSONArray();
                for (Permissions permission : permissions) {
                    if (permission.getGroupe().getIdGroupes().equals(idGroupe)) {
                        JSONArray perso = new JSONArray();
                        perso.put(permission.getPage().getIdPage()).put(permission.getPage().getNomPage());
                        Allpages.put(perso);
                    }
                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("getPagesBySousGroupe")) {
                int idGroupe = Integer.parseInt(request.getParameter("sousGroupe"));
                Groupes g = groupesFacade.find(idGroupe);

                JSONArray Allpages = new JSONArray();
                List<Permissions> permissions = PermissionsFacade.findAll();
                List<Page> pages = pageFacade.findByNiveau(g.getNiveau());
                List<Page> l = new ArrayList<>();
                for (Permissions permission : permissions) {
                    if (permission.getGroupe().getIdGroupes().equals(idGroupe)) {
                        JSONArray perso = new JSONArray();
                        perso.put(permission.getPage().getIdPage()).put(permission.getPage().getNomPage());
                        Allpages.put(perso);
                        l.add(permission.getPage());
                    }
                }

                pages.removeAll(l);

                JSONArray persoo = new JSONArray();
                persoo.put(99).put(99);
                Allpages.put(persoo);

                for (Page page : pages) {
                    JSONArray perso = new JSONArray();
                    perso.put(page.getIdPage()).put(page.getNomPage());
                    Allpages.put(perso);
                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("getPagesByNiveau")) {
                int niveau = Integer.parseInt(request.getParameter("niveau"));

                JSONArray Allpages = new JSONArray();

                List<Page> pages = pageFacade.findByNiveau(niveau);

                for (Page page : pages) {
                    JSONArray perso = new JSONArray();
                    perso.put(page.getIdPage()).put(page.getNomPage());
                    Allpages.put(perso);
                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);
            } else if (action.equalsIgnoreCase("getRole")) {

                int idAffectation = Integer.parseInt(request.getParameter("idAffectation"));

                int idregion = Integer.parseInt(request.getParameter("region"));
                Region NomRegion = regionFacade.find(idregion);

                JSONArray Allpages;
                if (idAffectation == 1) {
                    Allpages = new JSONArray();
                    List<Groupes> groupes = groupesFacade.findByNiveau(idAffectation);
                    for (Groupes groupe : groupes) {
                        JSONArray perso = new JSONArray();
                        perso.put(groupe.getIdGroupes()).put(groupe.getNomGroupe());

                        Allpages.put(perso);

                    }

                    response.setContentType("application/json");
                    response.getWriter().print(Allpages);
                }
                if (idAffectation == 4) {
                    Allpages = new JSONArray();
                    List<Responsablevalidation> magS = responsableValidationFacade.findAll();
                    for (Responsablevalidation permission : magS) {
                        JSONArray perso = new JSONArray();
                        perso.put(permission.getIdResponsableValidation()).put(permission.getDescription());

                        Allpages.put(perso);

                    }

                    response.setContentType("application/json");
                    response.getWriter().print(Allpages);
                }
                if (idAffectation == 2) {
                    Allpages = new JSONArray();
                    List<MagasinSecondaire> magS = magasinSecondaireFacade.findByRegion(NomRegion.getNomRegion());
                    System.out.println(magS);
                    for (MagasinSecondaire permission : magS) {
                        JSONArray perso = new JSONArray();
                        perso.put(permission.getIdMagasin()).put(permission.getNomMagasin());

                        Allpages.put(perso);

                    }

                    response.setContentType("application/json");
                    response.getWriter().print(Allpages);
                }

                if (idAffectation == 3) {
                    Allpages = new JSONArray();
                    List<MagasinPrincipal> magS = magasinPrincipalFacade.findAllByregion(NomRegion.getNomRegion());

                    for (MagasinPrincipal permission : magS) {
                        JSONArray perso = new JSONArray();
                        perso.put(permission.getIdMagasin()).put(permission.getNomMagasin());
                        Allpages.put(perso);

                    }

                    response.setContentType("application/json");
                    response.getWriter().print(Allpages);
                }

            } else if (action.equalsIgnoreCase("getSites")) {

                List<Site> sites = siteFacade.findAll();

                int region = Integer.parseInt(request.getParameter("region"));

                JSONArray siteResult = new JSONArray();
                for (Site site : sites) {

                    if (site.getRegion().getIdRegion().equals(region)) {
                        JSONArray perso = new JSONArray();

                        try {
                            perso.put(site.getIdSite()).put(site.getNomSite());
                            siteResult.put(perso);

                        } catch (Exception e) {

                        }

                    }

                }

                response.setContentType("application/json");
                response.getWriter().print(siteResult);

            } else if (action.equalsIgnoreCase("getPersonnels")) {

                int region = Integer.parseInt(request.getParameter("region"));

                JSONArray directionResult = new JSONArray();

                for (Personnel personnel : personnels) {
                    JSONArray perso = new JSONArray();
                    if (personnel.getService().getDirection().getRegion().getIdRegion().equals(region)) {
                        perso.put(personnel.getIdPersonnel()).put(personnel.getNomPrenom());
                        directionResult.put(perso);

                    }

                }

                response.setContentType("application/json");
                response.getWriter().print(directionResult);

            }else if (action.equalsIgnoreCase("getPersonnelsByService")) {

                int service = Integer.parseInt(request.getParameter("service"));
                List<Personnel> per = personnelFacade.findAllByIdService(service);
                JSONArray directionResult = new JSONArray();
                System.out.println(service);
                for (Personnel personnel : per) {
                    JSONArray perso = new JSONArray();

                    perso.put(personnel.getIdPersonnel()).put(personnel.getNomPrenom());
                    directionResult.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(directionResult);

            }else if (action.equalsIgnoreCase("getDirectionByRegion")) {

                int region = Integer.parseInt(request.getParameter("region"));
             
                List<Direction> services = directionFacade.findAll();
                JSONArray serviceResult = new JSONArray();

                for (Direction service : services) {
                    JSONArray Result = new JSONArray();
                    if (service.getRegion().getIdRegion().equals(regionFacade.find(region).getIdRegion())) {
                        Result.put(service.getIdDirection()).put(service.getNomDirection());
                        serviceResult.put(Result);
                    }

                }

                response.setContentType("application/json");
                response.getWriter().print(serviceResult);

            } else if (action.equalsIgnoreCase("getMagasigniersByService")) {

                int service = Integer.parseInt(request.getParameter("site"));

                JSONArray directionResult = new JSONArray();

                for (Personnel personnel : personnels) {
                    JSONArray perso = new JSONArray();
                    if (personnel.getService().getSite().getIdSite().equals(service)) {
                        perso.put(personnel.getIdPersonnel()).put(personnel.getNomPrenom());
                        directionResult.put(perso);
                        System.out.println(personnel.getNomPrenom());
                    }

                }

                response.setContentType("application/json");
                response.getWriter().print(directionResult);

            } else if (action.equalsIgnoreCase("getMagasinsPByRegion")) {

                String region = request.getParameter("region");

                JSONArray Allpages = new JSONArray();
                List<MagasinPrincipal> magS = magasinPrincipalFacade.findAllByregion(region);
                for (MagasinPrincipal permission : magS) {
                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdMagasin()).put(permission.getNomMagasin());
                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("getMagasinsByRegion")) {

                String service = request.getParameter("region");

                JSONArray Allpages = new JSONArray();
                List<MagasinSecondaire> magS = magasinSecondaireFacade.findByRegion(service);
                for (MagasinSecondaire permission : magS) {
                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdMagasin()).put(permission.getNomMagasin());
                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("getServices")) {

                String direction = request.getParameter("direction");

                List<Service> services = serviceFacade.findAll();
                JSONArray serviceResult = new JSONArray();
                for (Service service : services) {
                    if (service.getDirection().getNomDirection().equals(direction)) {
                        serviceResult.put(service.getNomService());
                    }

                }

                response.setContentType("application/json");
                response.getWriter().print(serviceResult);

            } else if (action.equalsIgnoreCase("getServiceBySite")) {

                int IdSite = Integer.parseInt(request.getParameter("site"));

                List<Service> services = serviceFacade.findAll();
                JSONArray serviceResult = new JSONArray();

                for (Service service : services) {
                    JSONArray Result = new JSONArray();
                    if (service.getSite().getIdSite().equals(IdSite)) {
                        Result.put(service.getIdService()).put(service.getNomService());
                        serviceResult.put(Result);
                    }

                }

                response.setContentType("application/json");
                response.getWriter().print(serviceResult);

            } else if (action.equalsIgnoreCase("getGroupesByNiveau")) {
                int id = 0;
                List<Groupes> groupes = new ArrayList<>();
                int idAffectation = Integer.parseInt(request.getParameter("idAffectation"));
                int idRegion = Integer.parseInt(request.getParameter("region"));
                JSONArray Allpages = new JSONArray();
                if (session.getAttribute("GeneralAdministrateur") == null) {

                    groupes = groupesFacade.findByNiveauByRegion(idAffectation, idRegion);
                } else {
                    groupes = groupesFacade.findByNiveau(idAffectation);
                }

                for (Groupes groupe : groupes) {
                    JSONArray perso = new JSONArray();
                    perso.put(groupe.getIdGroupes()).put(groupe.getNomGroupe());

                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("getAllHMS")) {
                int idmag = Integer.parseInt(request.getParameter("idMagasin"));
                int idper = Integer.parseInt(request.getParameter("idPersonnel"));
                List<AffectationmagasinS> MagasinsS = affectationmagasinSFacade.findAll();
                for (AffectationmagasinS mag : MagasinsS) {
                    if (mag.getMagasinS().getIdMagasin().equals(idmag) && mag.getPersonnel().getIdPersonnel().equals(idper)) {
                        Personnel personnel = mag.getPersonnel();
                        MagasinSecondaire secondaire = mag.getMagasinS();
                        categorie = secondaire.getCategorieproduitMSList();
                        request.setAttribute("categorie", categorie);
                        request.setAttribute("magasin", secondaire);
                        request.setAttribute("personnel", personnel);
                        list = ecartinventaireMSFacade.findAllByIdMS(idmag);
                    }
                }

                request.setAttribute("historique", list);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("controlleur")) {
                // List<Personnel> per;
                List<Responsablevalidation> typecontrols = responsableValidationFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                List<AffectationControleurs> controleur = affectationControleurFacade.findAll();
                request.setAttribute("vue", vue);
                request.setAttribute("responsables", controleur);
                //request.setAttribute("personnels", personnels);
                request.setAttribute("regions", regions);
                request.setAttribute("typecontrols", typecontrols);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getAllNotificationsEntete")) {

                int idrecepteur = Integer.parseInt(request.getParameter("recepteur"));

                JSONArray Allpages = new JSONArray();
                List<Notification> permissions = notificationFacade.findAllNotificationForOne(idrecepteur, 0);

                Personnel expediteur = new Personnel();
                Allpages.put(permissions.size());
                for (Notification permission : permissions) {
                    JSONArray perso = new JSONArray();
                    try {
                        expediteur = personnelFacade.find(permission.getExpediteur());
                    } catch (Exception e) {
                        request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                    }

                    perso.put(permission.getExpediteur()).put(expediteur.getNomPrenom()).put(permission.getRecepteur()).put(permission.getMessage());

                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);
            } else if (action.equalsIgnoreCase("getAllNotifications")) {

                int idrecepteur = Integer.parseInt(request.getParameter("recepteur"));
                JSONArray Allpages = new JSONArray();
                List<Notification> permissions = notificationFacade.findAllNotificationForOne(idrecepteur, 0);
                List<Personnel> connectes = personnelFacade.findAll();
                request.setAttribute("allNotifications", permissions);
                request.setAttribute("all", "all");
                request.setAttribute("connectes", connectes);
                //request.setAttribute("expediteur", vue);

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("getOneUserNotif")) {

                int idrecepteur = Integer.parseInt(request.getParameter("recepteur"));
                int idexpediteur = Integer.parseInt(request.getParameter("expediteur"));
                // JSONArray Allpages = new JSONArray();
                List<Notification> permissions = notificationFacade.findAllNotificationForOne(idrecepteur, 0);
                List<Notification> OneNotifUser = null;
                for (Notification notif : permissions) {
                    if ((notif.getExpediteur().equals(idexpediteur) && notif.getRecepteur().equals(idrecepteur)) || (notif.getExpediteur().equals(idrecepteur) && notif.getRecepteur().equals(idexpediteur))) {
                        OneNotifUser.add(notif);
                    }
                }
                List<Personnel> connectes = personnelFacade.findAll();
                request.setAttribute("newNotifications", OneNotifUser);
                request.setAttribute("one", "one");
                request.setAttribute("connectes", connectes);
                //request.setAttribute("expediteur", vue);

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("addControlleur")) {

                JSONArray Allpages = new JSONArray();
                Allpages.put("admin?vue=perso&action=getAll");

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("addMagasin")) {

                String nomMagasin = request.getParameter("nom_magasin");
                String description = request.getParameter("description");
                //int IdRegion = Integer.parseInt(request.getParameter("region"));
                String[] Categorie = request.getParameterValues("type_categorie");
                int IdSite = Integer.parseInt(request.getParameter("site"));

                // creation du magasin
                MagasinSecondaire newMagasin = new MagasinSecondaire();
                newMagasin.setIdMagasin((int) (Math.random() * (10000000)));
                newMagasin.setDescription(description);
                newMagasin.setNomMagasin(nomMagasin);
                newMagasin.setSite(new Site(IdSite));
                boolean repea = false;
                for (MagasinSecondaire ser : magasinSecondaireFacade.findAll()) {
                    if (ser.getNomMagasin().equalsIgnoreCase(nomMagasin)) {
                        repea = true;
                        break;
                    }
                }
                if (repea) {
                    Message message = new Message("Ce Magasin Secondaire Existe déjà");
                    request.setAttribute("message", message);
                } else {
                    magasinSecondaireFacade.create(newMagasin);
                }

                // recherche du nouveau personnel
                List<CategorieproduitMS> ses = new ArrayList<>();
                MagasinSecondaire lastMagasin = magasinSecondaireFacade.lastInsert();
                // cretion du budget du nouveau magasin pour les consommable informatique
                for (String string : Categorie) {
                    CategorieproduitMS cat2 = new CategorieproduitMS();
                    cat2.setMagasinSecondaire(new MagasinSecondaire(lastMagasin.getIdMagasin()));
                    cat2.setNomCategorie(string);
                    if (!repea) {
                        categorieproduitMSFacade.create(cat2);
                        ses.add(cat2);
                    }

                }
                lastMagasin.setCategorieproduitMSList(ses);
                if (!repea) {
                    magasinSecondaireFacade.edit(lastMagasin);
                }

                List<MagasinSecondaire> MagasinsS = magasinSecondaireFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsS", MagasinsS);
                request.setAttribute("all", "yes");
                request.setAttribute("form", "ONE");
                request.setAttribute("parametre", "OK");

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("addMagasinP")) {

                String nomMagasin = request.getParameter("nom_magasin");
                String description = request.getParameter("description");
                //int IdRegion = Integer.parseInt(request.getParameter("region"));
                String[] Categorie = request.getParameterValues("type_categorie");
                int IdSite = Integer.parseInt(request.getParameter("site"));

                // creation du magasin
                MagasinPrincipal newMagasin = new MagasinPrincipal();
                newMagasin.setIdMagasin((int) (Math.random() * (100000)));
                newMagasin.setDescription(description);
                newMagasin.setNomMagasin(nomMagasin);
                newMagasin.setSite(new Site(IdSite));

                boolean repea = false;
                for (MagasinPrincipal ser : magasinPrincipalFacade.findAll()) {
                    if (ser.getNomMagasin().equalsIgnoreCase(nomMagasin)) {
                        repea = true;
                        break;
                    }
                }
                if (repea) {
                    Message message = new Message("Ce Magasin Principal Existe déjà");
                    request.setAttribute("message", message);
                } else {
                    magasinPrincipalFacade.create(newMagasin);
                }

                // recherche du nouveau personnel
                MagasinPrincipal lastMagasin = magasinPrincipalFacade.lastInsert();
                List<CategorieproduitMP> ses = new ArrayList<>();
                for (String string : Categorie) {
                    CategorieproduitMP cat2 = new CategorieproduitMP();
                    cat2.setMagasinPrincipal(new MagasinPrincipal(lastMagasin.getIdMagasin()));
                    cat2.setNomCategorie(string);
                    if (!repea) {
                        categorieproduitMPFacade.create(cat2);
                        ses.add(cat2);
                    }
                }

                lastMagasin.setCategorieproduitMPList(ses);
                if (!repea) {
                    magasinPrincipalFacade.edit(lastMagasin);
                }

                List<MagasinPrincipal> MagasinsP = magasinPrincipalFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsP", MagasinsP);
                request.setAttribute("all", "yes");
                request.setAttribute("parametre", "OK");
                // request.setAttribute("form", "NON");
                //request.setAttribute("listMP", mps);
                request.setAttribute("forme", "ONE");
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("addStockMagP")) {

                int IdMagasin = Integer.parseInt(request.getParameter("nom_magasin"));
                String categoriee = request.getParameter("categorie");
                String code = request.getParameter("code");
                String designation = request.getParameter("designation");
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                Double pu = Double.parseDouble(request.getParameter("pu"));
                String date = request.getParameter("date");

                MagasinPrincipal mags = magasinPrincipalFacade.find(IdMagasin);

                CategorieproduitMP newCatMags = new CategorieproduitMP();
                for (CategorieproduitMP catMS : mags.getCategorieproduitMPList()) {
                    if (catMS.getNomCategorie().equals(categoriee)) {
                        newCatMags = catMS;
                        break;
                    }
                }

                StockproduitMP newstock = new StockproduitMP();
                newstock.setCategorie(newCatMags);
                newstock.setCodeProduit(code);
                newstock.setPrixUnitaire(pu);
                newstock.setQuantite(quantite);
                newstock.setPrixTotal(pu * quantite);
                newstock.setDesignation(designation);
                newstock.setDateLivraison(new Date(new java.util.Date(date).getTime()));

                boolean repea = false;
                for (StockproduitMP ser : stockproduitMPFacade.findByidMP(mags.getIdMagasin())) {
                    if (ser.getCodeProduit().equalsIgnoreCase(code)) {
                        repea = true;
                        break;
                    }
                }
                if (repea) {
                    Message message = new Message("Cet Article  Existe déjà Dans Ce Magasin Principal");
                    request.setAttribute("message", message);
                } else {
                    stockproduitMPFacade.create(newstock);
                }

                List<AffectationmagasinP> MagasinsP = affectationmagasinPFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsP", MagasinsP);
                request.setAttribute("all", "yes");
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("addStock")) {

                int IdMagasin = Integer.parseInt(request.getParameter("nom_magasin"));
                String categoriee = request.getParameter("categorie");
                String code = request.getParameter("code");
                String designation = request.getParameter("designation");
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                Double pu = Double.parseDouble(request.getParameter("pu"));
                String date = request.getParameter("date");
                MagasinSecondaire mags = magasinSecondaireFacade.find(IdMagasin);

                CategorieproduitMS newCatMags = new CategorieproduitMS();
                for (CategorieproduitMS catMS : mags.getCategorieproduitMSList()) {
                    if (catMS.getNomCategorie().equals(categoriee)) {
                        newCatMags = catMS;
                        break;
                    }
                }

                StockproduitMS newstock = new StockproduitMS();
                newstock.setCategorie(newCatMags);
                newstock.setCodeProduit(code);
                newstock.setPrixUnitaire(pu);
                newstock.setQuantite(quantite);
                newstock.setPrixTotal(pu * quantite);
                newstock.setDesignation(designation);
                newstock.setDateLivraison(new Date(new java.util.Date(date).getTime()));

                boolean repea = false;
                for (StockproduitMS ser : stockproduitMSFacade.findStockMP(mags.getIdMagasin())) {
                    if (ser.getCodeProduit().equalsIgnoreCase(code)) {
                        repea = true;
                        break;
                    }
                }
                if (repea) {
                    Message message = new Message("Cet Article  Existe déjà Dans Ce Magasin Secondaire");
                    request.setAttribute("message", message);
                } else {
                    stockproduitMSFacade.create(newstock);
                }

                List<AffectationmagasinS> MagasinsS = affectationmagasinSFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsS", MagasinsS);
                request.setAttribute("all", "yes");

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("profileAdmin")) {
                int id = (int) session.getAttribute("id");
                Personnel personnel = personnelFacade.find(id);
                request.setAttribute("personnel", personnel);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("deleteRegion")) {
                int id = Integer.parseInt(request.getParameter("idregion"));
                Region r = regionFacade.find(id);
                regionFacade.remove(r);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);

                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("editeRegion")) {
                int id = Integer.parseInt(request.getParameter("idregion"));
                Region r = regionFacade.find(id);
                if (request.getParameter("edite") != null) {
                    String region = request.getParameter("newval");
                    r.setNomRegion(region);
                    regionFacade.edit(r);

                    List<Region> regions = regionFacade.findAll();
                    request.setAttribute("regions", regions);
                } else {
                    Region rr = regionFacade.find(id);
                    List<Region> regions = regionFacade.findAll();
                    regions.remove(rr);
                    request.setAttribute("regions", regions);
                    request.setAttribute("region", rr);
                }
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("deleteDirection")) {
                int id = Integer.parseInt(request.getParameter("iddirection"));
                Direction r = directionFacade.find(id);
                directionFacade.remove(r);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                List<Direction> direct = directionFacade.findAll();
                request.setAttribute("directions", direct);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("editeDirection")) {
                int id = Integer.parseInt(request.getParameter("iddirection"));

                if (request.getParameter("edite") != null) {
                    String derection = request.getParameter("newval");
                    String region = request.getParameter("region");
                    Direction r = directionFacade.find(id);
                    r.setNomDirection(derection);
                    r.setRegion(new Region(Integer.parseInt(region)));
                    directionFacade.edit(r);
                    List<Direction> direct = directionFacade.findAll();
                    request.setAttribute("directions", direct);

                } else {
                    Direction r = directionFacade.find(id);
                    request.setAttribute("direction", r);
                    List<Direction> direct = directionFacade.findAll();
                    direct.remove(r);
                    request.setAttribute("directions", direct);
                }
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("deleteSite")) {
                int id = Integer.parseInt(request.getParameter("idsite"));
                Site r = siteFacade.find(id);
                siteFacade.remove(r);
                List<Site> direct = siteFacade.findAll();
                request.setAttribute("sites", direct);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("editeSite")) {
                int id = Integer.parseInt(request.getParameter("idsite"));

                if (request.getParameter("edite") != null) {
                    String site = request.getParameter("newval");
                    String region = request.getParameter("region");

                    Site r = siteFacade.find(id);
                    r.setNomSite(site);
                    r.setRegion(new Region(Integer.parseInt(region)));
                    siteFacade.edit(r);
                    List<Site> direct = siteFacade.findAll();
                    request.setAttribute("sites", direct);

                } else {
                    Site r = siteFacade.find(id);
                    request.setAttribute("site", r);
                    List<Site> direct = siteFacade.findAll();
                    direct.remove(r);
                    request.setAttribute("sites", direct);

                }
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("deleteService")) {
                int id = Integer.parseInt(request.getParameter("idservice"));
                Service r = serviceFacade.find(id);
                serviceFacade.remove(r);
                List<Service> direct = serviceFacade.findAll();
                request.setAttribute("services", direct);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("editeService")) {
                int id = Integer.parseInt(request.getParameter("idservice"));

                if (request.getParameter("edite") != null && request.getParameter("newval") != null && request.getParameter("direction") != null && request.getParameter("site") != null) {
                    String service = request.getParameter("newval");
                    int centre = Integer.parseInt(request.getParameter("centrecout"));
                    String direction = request.getParameter("direction");
                    String site = request.getParameter("site");
                    Service r = serviceFacade.find(id);
                    r.setNomService(service);
                    r.setCentreCout(centreCoutFacade.find(centre));
                    r.setDirection(directionFacade.find(Integer.parseInt(direction)));
                    r.setSite(siteFacade.find(Integer.parseInt(site)));
                    serviceFacade.edit(r);
                    List<Service> direct = serviceFacade.findAll();
                    request.setAttribute("services", direct);

                    request.setAttribute("directions", directionFacade.findAll());

                } else {
                    Service r = serviceFacade.find(id);
                    request.setAttribute("service", r);
                    List<Service> direct = serviceFacade.findAll();
                    direct.remove(r);
                    request.setAttribute("directions", directionFacade.findAll());
                    request.setAttribute("services", direct);

                }
                request.setAttribute("centreCout", centreCoutFacade.findAll());
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("getTypeMagasin")) {
                int id = Integer.parseInt(request.getParameter("id_magasin"));
                MagasinSecondaire r = magasinSecondaireFacade.find(id);
                List<CategorieproduitMS> ses = categorieproduitMSFacade.findCatByidMS(r.getIdMagasin());
                JSONArray perso = new JSONArray();

                for (CategorieproduitMS se : ses) {
                    JSONArray persoo = new JSONArray();
                    persoo.put(se.getIdCategorie());
                    persoo.put(se.getNomCategorie());
                    perso.put(persoo);
                }
                response.setContentType("application/json");
                response.getWriter().print(perso);

            } else if (action.equalsIgnoreCase("getTypeMagasinP")) {
                int id = Integer.parseInt(request.getParameter("id_magasin"));
                MagasinPrincipal r = magasinPrincipalFacade.find(id);
                List<CategorieproduitMP> ses = categorieproduitMPFacade.findCatByidMP(r.getIdMagasin());
                JSONArray perso = new JSONArray();

                for (CategorieproduitMP se : ses) {
                    JSONArray persoo = new JSONArray();
                    persoo.put(se.getIdCategorie());
                    persoo.put(se.getNomCategorie());
                    perso.put(persoo);
                }
                response.setContentType("application/json");
                response.getWriter().print(perso);

            } else if (action.equalsIgnoreCase("allCatNotMag")) {
                int id = Integer.parseInt(request.getParameter("id_magasin"));
                MagasinSecondaire r = magasinSecondaireFacade.find(id);
                r.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(r.getIdMagasin()));
                List<CategorieProduit> ses = categorieProduitFacade.findAll();
                JSONArray perso = new JSONArray();
                List<CategorieProduit> result = new ArrayList<>();
                for (CategorieProduit se : ses) {
                    for (CategorieproduitMS cat : r.getCategorieproduitMSList()) {
                        if (se.getTypeCategorie().equalsIgnoreCase(cat.getNomCategorie())) {
                            result.add(se);
                            break;

                        }
                    }

                }
                ses.removeAll(result);
                for (CategorieProduit se : ses) {
                    JSONArray persoo = new JSONArray();
                    persoo.put(se.getIdCategorieProduit());
                    persoo.put(se.getTypeCategorie());
                    perso.put(persoo);
                }
                System.out.println(result);
                response.setContentType("application/json");
                response.getWriter().print(perso);

            } else if (action.equalsIgnoreCase("allCatNotMagMP")) {
                int id = Integer.parseInt(request.getParameter("id_magasin"));
                MagasinPrincipal r = magasinPrincipalFacade.find(id);
                r.setCategorieproduitMPList(categorieproduitMPFacade.findCatByidMP(r.getIdMagasin()));
                List<CategorieProduit> ses = categorieProduitFacade.findAll();
                JSONArray perso = new JSONArray();
                List<CategorieProduit> result = new ArrayList<>();
                for (CategorieProduit se : ses) {
                    for (CategorieproduitMP cat : r.getCategorieproduitMPList()) {
                        if (se.getTypeCategorie().equalsIgnoreCase(cat.getNomCategorie())) {
                            result.add(se);
                            break;

                        }
                    }

                }
                ses.removeAll(result);
                for (CategorieProduit se : ses) {
                    JSONArray persoo = new JSONArray();
                    persoo.put(se.getIdCategorieProduit());
                    persoo.put(se.getTypeCategorie());
                    perso.put(persoo);
                }
                System.out.println(result);
                response.setContentType("application/json");
                response.getWriter().print(perso);

            } else if (action.equalsIgnoreCase("addCategorieForMS")) {

                int idMS = Integer.parseInt(request.getParameter("id_Magasin"));
                String[] categos;

                if (request.getParameterValues("allcategorie") != null) {
                    categos = request.getParameterValues("allcategorie");

                    for (String catego : categos) {
                        CategorieProduit cp = categorieProduitFacade.find(Integer.parseInt(catego));

                        CategorieproduitMS s = new CategorieproduitMS();
                        s.setMagasinSecondaire(magasinSecondaireFacade.find(idMS));
                        s.setNomCategorie(cp.getTypeCategorie());
                        categorieproduitMSFacade.create(s);
                    }
                } else {
                    CategorieProduit cp = new CategorieProduit();
                    String categories = request.getParameter("type_categorie");
                    cp.setTypeCategorie(categories);
                    categorieProduitFacade.create(cp);
                    CategorieproduitMS s = new CategorieproduitMS();
                    s.setMagasinSecondaire(magasinSecondaireFacade.find(idMS));
                    s.setNomCategorie(categories);
                    categorieproduitMSFacade.create(s);
                }

                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                ms.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(idMS));
                magasinSecondaireFacade.edit(ms);
                List<CategorieproduitMS> cp1 = categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin());

                JSONArray Allpages = new JSONArray();
                for (CategorieproduitMS cp2 : cp1) {
                    JSONArray Al = new JSONArray();
                    Al.put(cp2.getIdCategorie()).put(cp2.getNomCategorie());
                    Allpages.put(Al);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("deleteCategorieForMS")) {
                int idMS = Integer.parseInt(request.getParameter("id_Magasin"));
                int idcatego = Integer.parseInt(request.getParameter("id_categorie"));
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                CategorieproduitMS categorieMS = categorieproduitMSFacade.find(idcatego);
                CategorieProduit cp = categorieProduitFacade.findByTypeCategorie(categorieMS.getNomCategorie());
                categorieproduitMSFacade.remove(categorieMS);
                Butget b = new Butget();
                boolean iserror = false;
                try {
                    b = butgetFacade.findOnBudgetForService(ms.getIdMagasin(), cp.getIdCategorieProduit(), categorieMS.getNomCategorie());
                } catch (Exception e) {
                    iserror = true;
                }

                if (!iserror) {
                    butgetFacade.remove(b);
                }

                ms.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(idMS));
                magasinSecondaireFacade.edit(ms);
                List<CategorieproduitMS> cp1 = categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin());

                JSONArray Allpages = new JSONArray();
                for (CategorieproduitMS cp2 : cp1) {
                    JSONArray Al = new JSONArray();
                    Al.put(cp2.getIdCategorie()).put(cp2.getNomCategorie());
                    Allpages.put(Al);

                }
                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("deleteCategorieForMP")) {
                int idMS = Integer.parseInt(request.getParameter("id_Magasin"));
                int idcatego = Integer.parseInt(request.getParameter("id_categorie"));
                MagasinPrincipal ms = magasinPrincipalFacade.find(idMS);
                CategorieproduitMP categorieMS = categorieproduitMPFacade.find(idcatego);
                System.out.println(idMS);
                System.out.println(idcatego);
                System.out.println(categorieMS);
                categorieproduitMPFacade.remove(categorieMS);

                ms.setCategorieproduitMPList(categorieproduitMPFacade.findCatByidMP(idMS));
                magasinPrincipalFacade.edit(ms);
                List<CategorieproduitMP> cp1 = categorieproduitMPFacade.findCatByidMP(ms.getIdMagasin());

                JSONArray Allpages = new JSONArray();
                for (CategorieproduitMP cp2 : cp1) {
                    JSONArray Al = new JSONArray();
                    Al.put(cp2.getIdCategorie()).put(cp2.getNomCategorie());
                    Allpages.put(Al);

                }
                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("addCategorieForMP")) {

                int idMS = Integer.parseInt(request.getParameter("id_Magasin"));
                String[] categos;

                if (request.getParameterValues("allcategorie") != null) {
                    categos = request.getParameterValues("allcategorie");

                    for (String catego : categos) {
                        CategorieProduit cp = categorieProduitFacade.find(Integer.parseInt(catego));

                        CategorieproduitMP s = new CategorieproduitMP();
                        s.setMagasinPrincipal(magasinPrincipalFacade.find(idMS));
                        s.setNomCategorie(cp.getTypeCategorie());
                        categorieproduitMPFacade.create(s);
                    }
                } else {
                    CategorieProduit cp = new CategorieProduit();
                    String categories = request.getParameter("type_categorie");
                    cp.setTypeCategorie(categories);
                    categorieProduitFacade.create(cp);
                    CategorieproduitMP s = new CategorieproduitMP();
                    s.setMagasinPrincipal(magasinPrincipalFacade.find(idMS));
                    s.setNomCategorie(categories);
                    categorieproduitMPFacade.create(s);
                }

                MagasinPrincipal ms = magasinPrincipalFacade.find(idMS);
                ms.setCategorieproduitMPList(categorieproduitMPFacade.findCatByidMP(idMS));
                magasinPrincipalFacade.edit(ms);
                List<CategorieproduitMP> cp1 = categorieproduitMPFacade.findCatByidMP(ms.getIdMagasin());

                JSONArray Allpages = new JSONArray();
                for (CategorieproduitMP cp2 : cp1) {
                    JSONArray Al = new JSONArray();
                    Al.put(cp2.getIdCategorie()).put(cp2.getNomCategorie());
                    Allpages.put(Al);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("getArticlesByCategorie")) {

                int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
                CategorieproduitMS CatP = categorieproduitMSFacade.find(idCategorie);
                CategorieProduit c = categorieProduitFacade.findByTypeCategorie(CatP.getNomCategorie());

                List<Article> permissions = articleFacade.findAllByCategorieProduit(c.getIdCategorieProduit());
                //System.out.println(permissions);
                JSONArray Allpages = new JSONArray();
                for (Article permission : permissions) {

                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdArticle()).put(permission.getDesignation());
                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            } else if (action.equalsIgnoreCase("getArticlesByCategorieMP")) {

                int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
                System.out.println(idCategorie);
                CategorieproduitMP CatP = categorieproduitMPFacade.find(idCategorie);
                CategorieProduit c = categorieProduitFacade.findByTypeCategorie(CatP.getNomCategorie());

                List<Article> permissions = articleFacade.findAllByCategorieProduit(c.getIdCategorieProduit());
                //System.out.println(permissions);
                JSONArray Allpages = new JSONArray();
                for (Article permission : permissions) {

                    JSONArray perso = new JSONArray();
                    perso.put(permission.getIdArticle()).put(permission.getDesignation());
                    Allpages.put(perso);

                }

                response.setContentType("application/json");
                response.getWriter().print(Allpages);

            }

        } else {
            response.sendRedirect("login.jsp");
        }
    }

    void critique(HttpServletRequest request, HttpSession session, int idMS) {
        List<StockproduitMS> sms = stockproduitMSFacade.ProduitCritique(idMS);
        List<StockproduitMS> smss = stockproduitMSFacade.ProduitWarmin(idMS);
        if (!sms.isEmpty()) {
            System.out.println(sms.size() + "critique");

            session.removeAttribute("critique");
            session.setAttribute("critique", "yes");
            session.removeAttribute("stock");
            session.setAttribute("stock", sms);
        }
        if (!smss.isEmpty()) {
            System.out.println(smss.size() + "warmin");
            session.removeAttribute("warmin");
            session.setAttribute("warmin", "yes");
            session.removeAttribute("stockw");
            session.setAttribute("stockw", smss);
        }
    }

}

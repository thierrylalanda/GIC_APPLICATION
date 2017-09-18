/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.Administrator;

import com.gciapplication.entity.Actions;
import com.gciapplication.entity.AffectationControleurs;
import com.gciapplication.entity.AffectationmagasinP;
import com.gciapplication.entity.AffectationmagasinS;
import com.gciapplication.entity.Appariel;
import com.gciapplication.entity.CommandeMP;
import com.gciapplication.entity.Groupes;
import com.gciapplication.entity.Login;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.OrdreControleur;
import com.gciapplication.entity.Page;
import com.gciapplication.entity.Permissions;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.Responsablevalidation;
import com.gciapplication.entity.Service;
import com.gciapplication.entity.TypeAppareil;
import com.gciapplication.session.entity.AffectationControleursFacadeLocal;
import com.gciapplication.session.entity.AffectationmagasinPFacadeLocal;
import com.gciapplication.session.entity.AffectationmagasinSFacadeLocal;
import com.gciapplication.session.entity.ApparielFacadeLocal;
import com.gciapplication.session.entity.CommandeMPFacadeLocal;
import com.gciapplication.session.entity.DirectionFacadeLocal;
import com.gciapplication.session.entity.FrabriquantAppareilFacadeLocal;
import com.gciapplication.session.entity.GroupesFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.OrdreControleurFacadeLocal;
import com.gciapplication.session.entity.PageFacadeLocal;
import com.gciapplication.session.entity.PermissionsFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.ResponsablevalidationFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import com.gciapplication.session.entity.TypeAppareilFacadeLocal;
import com.gestion.DataSource.mysql.Message;
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

/**
 *
 * @author messi
 */
@WebServlet(name = "SettingPersonnal", urlPatterns = {"/SettingPersonnal"})
public class SettingPersonnal extends HttpServlet {

    @EJB
    private FrabriquantAppareilFacadeLocal frabriquantAppareilFacade;

    @EJB
    private DirectionFacadeLocal directionFacade;

    @EJB
    private SiteFacadeLocal siteFacade;
    @EJB
    private TypeAppareilFacadeLocal typeAppareilFacade;
    @EJB
    private ResponsablevalidationFacadeLocal responsablevalidationFacade;
    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private CommandeMPFacadeLocal commandeMPFacade;
    @EJB
    private OrdreControleurFacadeLocal ordreControleurFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private PageFacadeLocal pageFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private ApparielFacadeLocal apparielFacade;

    @EJB
    private AffectationControleursFacadeLocal affectationControleursFacade;

    @EJB
    private AffectationmagasinSFacadeLocal affectationmagasinSFacade;

    @EJB
    private AffectationmagasinPFacadeLocal affectationmagasinPFacade;

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private LoginFacadeLocal loginFacade;

    @EJB
    private GroupesFacadeLocal groupesFacade;

    private final String etatCommande = "Encour de Traitement";

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
            Personnel p = (Personnel) session.getAttribute("personnel");
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");

            List<Personnel> Listpersonnel = personnelFacade.findAll();
            request.setAttribute("personnels", Listpersonnel);

            if (action.equalsIgnoreCase("getUsername")) {
                String user = request.getParameter("username");
                boolean repea = false;
                for (Personnel ser : personnelFacade.findAll()) {
                    if (ser.getLoginList().getUsername().equalsIgnoreCase(user)) {
                        repea = true;
                        break;
                    }
                }
                if (repea) {
                    response.setContentType("application/text");
                    response.getWriter().print("Un Personnel Existe déjà Avec Ce Nom D'utilisateur");
                } else {
                    response.setContentType("application/text");
                    response.getWriter().print("OK");
                }
            } else if (action.equalsIgnoreCase("newpersonnel")) {

                String service = request.getParameter("service");
                System.out.println(service);
                String nomPersonnel = request.getParameter("name");
                System.out.println(nomPersonnel);
                String mail = request.getParameter("email");
                System.out.println(mail);
                String tel = request.getParameter("phone");
                System.out.println(tel);
                String fonction = request.getParameter("fonction");
                System.out.println(fonction);
                String matricule = request.getParameter("matricule");
                System.out.println(matricule);
                String user = request.getParameter("username");
                System.out.println(user);
                String password = request.getParameter("password");
                System.out.println(password);
                int groupe = Integer.parseInt(request.getParameter("groupe"));
                System.out.println(groupe);
                String contro = request.getParameter("controleur");
                System.out.println(contro);
                System.out.println(request.getParameter("affectation") + "affectation");

                if (contro.equalsIgnoreCase("yes")) {
                    request.setAttribute("vue", "controlleur");
                    request.setAttribute("controle", "yes");
                    List<AffectationControleurs> controleur = affectationControleursFacade.findAll();
                    request.setAttribute("responsables", controleur);
                    List<Region> regions = regionFacade.findAll();
                    request.setAttribute("regions", regions);

                } else {
                    request.setAttribute("vue", vue);
                }
                Personnel personnel = new Personnel();
                switch (Integer.parseInt(request.getParameter("affectation"))) {
                    case 1:
                        personnel.setRole("personnel simple");
                        break;
                    case 2:
                        personnel.setRole("magasinier secondaire");
                        break;
                    case 3:
                        personnel.setRole("magasinier principal");
                        break;
                    case 4:
                        personnel.setRole("controlleur");
                        break;
                    default:
                        break;
                }
                personnel.setNomPrenom(nomPersonnel);
                personnel.setMatricule(matricule);
                personnel.setFonction(fonction);
                personnel.setEmail(mail);
                personnel.setTelephone(tel);
                personnel.setService(new Service(Integer.parseInt(service)));

                boolean repea = false;
                for (Login ser : loginFacade.findAll()) {
                    if (ser.getUsername().equalsIgnoreCase(user)) {
                        repea = true;
                        break;
                    }
                }
                if (!repea) {

                    try {
                        personnelFacade.create(personnel);
                    } catch (Exception e) {
                        System.out.println("erreur creation Personnel " + e.getMessage());
                        e.getCause();
                        response.setContentType("application/text");
                        response.getWriter().print("Une Erreur est survenue L'enregistrement Ne C'est Pas Effectuer");

                    }

                    Personnel personnels = personnelFacade.findMAXIdPersonnel();

                    Login login = new Login();
                    login.setUsername(user);
                    login.setPassword(password);
                    login.setNiveauAcces(Integer.parseInt(request.getParameter("affectation")));
                    login.setPersonnel(personnels);
                    login.setGroupes(groupesFacade.find(groupe));
                    login.getGroupes().setPermissionsList(groupesFacade.find(groupe).getPermissionsList());
                    try {
                        loginFacade.create(login);
                    } catch (Exception e) {
                        System.out.println("erreur creation du login " + e.getMessage());
                        e.getCause();
                    }

                    personnels.setLoginList(loginFacade.findBypersonnelID(personnels.getIdPersonnel()));
                    Appariel a = new Appariel();
                    a.setNumeroParck("AUTCH" + (int) (Math.random() * 1000000));
                    a.setProprietaire(personnels);
                    a.setNumeroSerie("000000");
                    try {
                        apparielFacade.create(a);
                    } catch (Exception e) {
                        System.out.println("erreur creation de L'appareil Par Defaut " + e.getMessage());
                        e.getCause();
                    }
                    personnels.setApparielList(apparielFacade.findAllApparielByPersonnel(personnels.getIdPersonnel()));

                    switch (Integer.parseInt(request.getParameter("affectation"))) {

                        case 2:
                            AffectationmagasinS s = new AffectationmagasinS();
                            int magasinMS = Integer.parseInt(request.getParameter("magasin"));
                            s.setMagasinS(new MagasinSecondaire(magasinMS));
                            s.setPersonnel(personnels);
                            try {
                                affectationmagasinSFacade.create(s);
                            } catch (Exception e) {
                                System.out.println("erreur creation de L'affectation" + e.getMessage());
                                e.getCause();
                            }

                            personnels.setAffectationmagasinSList(affectationmagasinSFacade.findByIDPersonnel(personnels.getIdPersonnel()));
                            break;
                        case 3:
                            AffectationmagasinP ap = new AffectationmagasinP();
                            int magasinMP = Integer.parseInt(request.getParameter("magasin"));
                            ap.setMagasinP(new MagasinPrincipal(magasinMP));
                            ap.setPersonnel(personnels);
                            try {
                                affectationmagasinPFacade.create(ap);
                            } catch (Exception e) {
                                System.out.println("erreur creation de L'affectation" + e.getMessage());
                                e.getCause();
                            }

                            personnels.setAffectationmagasinPList(affectationmagasinPFacade.findByIDPersonnel(personnels.getIdPersonnel()));
                            break;
                        case 4:
                            AffectationControleurs AffectaC = new AffectationControleurs();
                            int control = Integer.parseInt(request.getParameter("type_controle"));
                            AffectaC.setResponsableValidation(new Responsablevalidation(control));
                            AffectaC.setPersonnel(personnels);
                            try {
                                affectationControleursFacade.create(AffectaC);
                            } catch (Exception e) {
                                System.out.println("erreur creation de L'affectation" + e.getMessage());
                                e.getCause();
                            }

                            personnels.setAffectationControleursList(affectationControleursFacade.findByIDPersonnel(personnels.getIdPersonnel()));
                            break;
                        default:
                            break;
                    }
                    personnelFacade.edit(personnels);

                    List<Personnel> personnelss = personnelFacade.findAll();

                    request.setAttribute("personnels", personnelss);
                    List<Region> regions = regionFacade.findAll();
                    request.setAttribute("regions", regions);

                    response.setContentType("application/text");
                    response.getWriter().print("OK");
                } else {
                    response.setContentType("application/text");
                    response.getWriter().print("Un Personnel Existe déjà Avec Ce Nom D'utilisateur");

                }
                // request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("editpersonnel")) {
                String controle = request.getParameter("controleur");
                int id = Integer.parseInt(request.getParameter("id"));
                Personnel personnel = personnelFacade.find(id);
                Appariel a = new Appariel();

                try {
                    a = apparielFacade.findDefautAppareil(id);
                } catch (Exception e) {

                }

                request.setAttribute("defautAppareil", a.getNumeroParck());

                if (personnel.getMatricule() != null) {

                    List<Page> pages = pageFacade.findByNiveau(loginFacade.findBypersonnelID(id).getNiveauAcces());

                    List<Groupes> groupes = groupesFacade.findAll();
                    List<Groupes> sousgroupes = groupesFacade.findByNiveau(loginFacade.findBypersonnelID(id).getNiveauAcces());
                    request.setAttribute("groupes", groupes);
                    request.setAttribute("pages", pages);
                    request.setAttribute("sousgroupes", sousgroupes);

                    Login l = loginFacade.findBypersonnelID(personnel.getIdPersonnel());
                    List< Page> ps = new ArrayList<>();
                    List<Page> pagegroupe = pageFacade.findByNiveau(l.getGroupes().getNiveau());
                    List<Permissions> pagespermise = permissionsFacade.findAllByIdGrpoupe(loginFacade.findBypersonnelID(id).getGroupes().getIdGroupes());
                    pagespermise.stream().forEach((permission) -> {
                        ps.add(permission.getPage());
                    });
                    pagegroupe.removeAll(ps);
                    request.setAttribute("pagegroupe", pagegroupe);
                    personnel.setLoginList(loginFacade.findBypersonnelID(id));
                    personnel.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findAllByIdGrpoupe(l.getGroupes().getIdGroupes()));

                    Groupes g = groupesFacade.find(personnel.getLoginList().getGroupes().getIdGroupes());
                    g.setLoginList(loginFacade.findAllByIdGroupe(g.getIdGroupes()));
                    personnel.getLoginList().setGroupes(g);

                    request.setAttribute("personnel", personnel);
                    request.setAttribute("groupe", groupesFacade.find(personnel.getLoginList().getGroupes().getIdGroupes()));
                    request.setAttribute("all", "yes");
                    List<Region> regions = regionFacade.findAll();
                    request.setAttribute("regions", regions);
                    List<Personnel> personnels = personnelFacade.findAll();
                    request.setAttribute("personnels", personnels);
                    //si on edite plus tot un controleur on envoie toutes les commandes quil es sence traiter
                    if (controle.equalsIgnoreCase("yes")) {
                        Responsablevalidation responsablevalidation = personnel.getAffectationControleursList().get(0).getResponsableValidation();
                        boolean lister = false;
                        List<OrdreControleur> ordreControleursC = ordreControleurFacade.findIDControleurs(personnel.getIdPersonnel());
                        if (!ordreControleursC.isEmpty()) {

                            switch (responsablevalidation.getNiveau()) {
                                case 1:
                                    if (responsablevalidation.getNiveau() == 1 && ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == personnel.getIdPersonnel()) {
                                        lister = true;
                                    }
                                    break;
                                case 2:
                                    lister = responsablevalidation.getNiveau() == 2 && ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == personnel.getIdPersonnel();
                                    break;
                                case 3:
                                    lister = responsablevalidation.getNiveau() == 3 && ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == personnel.getIdPersonnel();
                                    break;
                                default:
                                    break;
                            }

                        }
                        if (lister) {

                            List<OrdreControleur> ordreControleur = ordreControleurFacade.findIDControleurs(personnel.getIdPersonnel());
                            List<CommandeMP> cmps = commandeMPFacade.findByIdMPAndEtat(ordreControleur.get(0).getIdMP().getIdMagasin(), etatCommande);
                            // List<BudgetMP> bmp = budgetMPFacade.findBudgetByidMP(cmps.get(0).getIdMP().getIdMagasin());

                            request.setAttribute("produitMP", ordreControleursC.get(0).getIdMP().getCategorieproduitMPList());
                            request.setAttribute("listeCMP", cmps);

                            request.setAttribute("nom_Magasin", cmps.get(0).getIdMP().getNomMagasin());
                            // request.setAttribute("budget", bmp);

                        } else {
                            Message message = new Message("Aucune Commande pour l'instant !!!");
                            request.setAttribute("message", message);

                        }
                        request.setAttribute("vue", "controlleur");
                        request.setAttribute("controle", "yes");
                    }
                } else {
                    request.setAttribute("personnel", personnel);
                }
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("updatedatapersonnel")) {

                int id = Integer.parseInt(request.getParameter("idPersonne"));

                Personnel p1 = personnelFacade.find(id);
                if (!request.getParameter("email").isEmpty()) {
                    String mail = request.getParameter("email");
                    p1.setEmail(mail);
                }
                if (!request.getParameter("phone").isEmpty()) {
                    String tel = request.getParameter("phone");
                    p1.setTelephone(tel);
                }
                if (!request.getParameter("fonction").isEmpty()) {
                    String fonction = request.getParameter("fonction");
                    p1.setFonction(fonction);
                }

                if (!request.getParameter("matricule").isEmpty()) {
                    String matricule = request.getParameter("matricule");
                    p1.setMatricule(matricule);
                }
                if (!request.getParameter("nom").isEmpty()) {
                    String nom = request.getParameter("nom");
                    p1.setNomPrenom(nom);
                }
                if (!request.getParameter("service").isEmpty()) {
                    String service = request.getParameter("service");
                    if (p1.getService().getIdService() != Integer.parseInt(service)) {
                        p1.setService(new Service(Integer.parseInt(service)));
                    }
                }
                personnelFacade.edit(p1);
                try {
                    List<Page> pages = pageFacade.findByNiveau(p1.getLoginList().getNiveauAcces());
                    request.setAttribute("pages", pages);
                    request.setAttribute("groupe", groupesFacade.find(p1.getLoginList().getGroupes().getIdGroupes()));
                } catch (Exception e) {
                }

                List<Personnel> personnelss = personnelFacade.findAll();
                request.setAttribute("personnels", personnelss);
                request.setAttribute("personnel", personnelFacade.find(p1.getIdPersonnel()));

                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("etat", "OK");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("AddAppareilDefaut")) {
                int id = Integer.parseInt(request.getParameter("idPersonne"));
                Personnel p1 = personnelFacade.find(id);
                Appariel a = new Appariel();
                a.setNumeroParck("AUTCH" + (int) (Math.random() * 1000000));
                a.setProprietaire(p1);
                a.setNumeroSerie("000000");
                try {
                    apparielFacade.create(a);
                } catch (Exception e) {
                    System.out.println("erreur creation de L'appareil Par Defaut " + e.getMessage());
                    e.getCause();
                }
                p1.setApparielList(apparielFacade.findAllApparielByPersonnel(p1.getIdPersonnel()));

                personnelFacade.edit(p1);

                List<Page> pages = pageFacade.findByNiveau(p1.getLoginList().getGroupes().getNiveau());
                request.setAttribute("pages", pages);
                List<Personnel> personnelss = personnelFacade.findAll();
                request.setAttribute("personnels", personnelss);
                request.setAttribute("personnel", personnelFacade.find(p1.getIdPersonnel()));
                request.setAttribute("personnell", personnelFacade.find(p1.getIdPersonnel()));
                request.setAttribute("groupe", groupesFacade.find(p1.getLoginList().getGroupes().getIdGroupes()));
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("etat", "OK");
                request.setAttribute("vue", vue);
                int niveau = 0;
                String redirect = "";
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                switch (niveau) {
                    case 1:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 2:
                        redirect = "/WEB-INF/magasignerS/main.jsp";
                        break;
                    case 3:
                        redirect = "/WEB-INF/magasignierP/main.jsp";
                        break;
                    case 4:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 5:
                        redirect = "/WEB-INF/administrateur/main1.jsp";
                        break;
                }
                request.setAttribute("defautAppareil", a.getNumeroParck());
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("updateLoginpersonnel")) {

                int id = Integer.parseInt(request.getParameter("idPersonne"));

                Personnel p1 = personnelFacade.find(id);

                if (!request.getParameter("password").isEmpty()) {
                    String password = request.getParameter("password");
                    p1.getLoginList().setPassword(password);
                }

                //loginFacade.edit(p1.getLoginList());
                personnelFacade.edit(p1);
                try {
                    List<Page> pages = pageFacade.findByNiveau(p1.getLoginList().getGroupes().getNiveau());
                    request.setAttribute("pages", pages);
                    request.setAttribute("groupe", groupesFacade.find(p1.getLoginList().getGroupes().getIdGroupes()));
                } catch (Exception e) {
                }

                List<Personnel> personnelss = personnelFacade.findAll();
                request.setAttribute("personnels", personnelss);
                request.setAttribute("personnel", personnelFacade.find(p1.getIdPersonnel()));
                request.setAttribute("personnell", personnelFacade.find(p1.getIdPersonnel()));

                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("etat", "OK");
                request.setAttribute("vue", vue);
                int niveau = 0;
                String redirect = "";
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                switch (niveau) {
                    case 1:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 2:
                        redirect = "/WEB-INF/magasignerS/main.jsp";
                        break;
                    case 3:
                        redirect = "/WEB-INF/magasignierP/main.jsp";
                        break;
                    case 4:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 5:
                        redirect = "/WEB-INF/administrateur/main1.jsp";
                        break;
                }

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("newLoginforpersonnel")) {

                int id = Integer.parseInt(request.getParameter("idPersonne"));
                String password = request.getParameter("password");
                String user = request.getParameter("user_name");
                int id_groupe = Integer.parseInt(request.getParameter("groupe"));
                Groupes g = groupesFacade.find(id_groupe);
                Personnel p1 = personnelFacade.find(id);

                Login login = new Login();
                login.setUsername(user);
                login.setPassword(password);
                login.setNiveauAcces(g.getNiveau());
                login.setPersonnel(p1);
                login.setGroupes(groupesFacade.find(g.getIdGroupes()));
                login.getGroupes().setPermissionsList(groupesFacade.find(g.getIdGroupes()).getPermissionsList());
                loginFacade.create(login);
                p1.setLoginList(loginFacade.findBypersonnelID(p1.getIdPersonnel()));

                try {
                    List<Page> pages = pageFacade.findByNiveau(g.getNiveau());
                    request.setAttribute("pages", pages);
                    request.setAttribute("groupe", groupesFacade.find(p1.getLoginList().getGroupes().getIdGroupes()));
                } catch (Exception e) {
                }
                personnelFacade.edit(p1);
                List<Personnel> personnelss = personnelFacade.findAll();
                request.setAttribute("personnels", personnelss);
                request.setAttribute("personnel", personnelFacade.find(p1.getIdPersonnel()));
                request.setAttribute("personnell", personnelFacade.find(p1.getIdPersonnel()));

                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("etat", "OK");
                request.setAttribute("vue", vue);
                int niveau = 0;
                String redirect = "";
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                switch (niveau) {
                    case 1:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 2:
                        redirect = "/WEB-INF/magasignerS/main.jsp";
                        break;
                    case 3:
                        redirect = "/WEB-INF/magasignierP/main.jsp";
                        break;
                    case 4:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 5:
                        redirect = "/WEB-INF/administrateur/main1.jsp";
                        break;
                }

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("updateUserNamepersonnel")) {
                String user = request.getParameter("user");
                int id = Integer.parseInt(request.getParameter("idPersonne"));

                Personnel p1 = personnelFacade.find(id);
                p1.getLoginList().setUsername(user);

                personnelFacade.edit(p1);

                List<Page> pages = pageFacade.findByNiveau(p1.getLoginList().getGroupes().getNiveau());
                request.setAttribute("pages", pages);
                List<Personnel> personnelss = personnelFacade.findAll();
                request.setAttribute("personnels", personnelss);
                request.setAttribute("personnel", personnelFacade.find(p1.getIdPersonnel()));
                request.setAttribute("personnell", personnelFacade.find(p1.getIdPersonnel()));
                request.setAttribute("groupe", groupesFacade.find(p1.getLoginList().getGroupes().getIdGroupes()));
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("etat", "OK");
                request.setAttribute("vue", vue);
                int niveau = 0;
                String redirect = "";
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                switch (niveau) {
                    case 1:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 2:
                        redirect = "/WEB-INF/magasignerS/main.jsp";
                        break;
                    case 3:
                        redirect = "/WEB-INF/magasignierP/main.jsp";
                        break;
                    case 4:
                        redirect = "/WEB-INF/controleurs/main.jsp";
                        break;
                    case 5:
                        redirect = "/WEB-INF/administrateur/main1.jsp";
                        break;
                }

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("updatepersonnel")) {

                int id = Integer.parseInt(request.getParameter("idPersonne"));

                if (request.getParameter("groupe") != null) {

                    int groupe = Integer.parseInt(request.getParameter("groupe"));

                    Personnel p1 = personnelFacade.find(id);

                    AffectationmagasinS as = new AffectationmagasinS();
                    AffectationmagasinP ap = new AffectationmagasinP();
                    AffectationControleurs ac = new AffectationControleurs();
                    boolean seems = false;
                    boolean seemp = false;
                    boolean seec = false;
                    try {
                        as = affectationmagasinSFacade.findOnByIdPersonnel(p1.getIdPersonnel());
                    } catch (Exception e) {
                        seems = true;
                    }
                    try {
                        ap = affectationmagasinPFacade.findOnByIdPersonnel(p1.getIdPersonnel());

                    } catch (Exception e) {
                        seemp = true;
                    }

                    try {
                        ac = affectationControleursFacade.findOnByIdPersonnel(p1.getIdPersonnel());
                    } catch (Exception e) {
                        seec = true;
                    }

                    int magasinMS, magasinMP, control;
                    switch (Integer.parseInt(request.getParameter("affectation"))) {

                        case 2:
//                        boolean isnew = false;
                            magasinMS = Integer.parseInt(request.getParameter("magasin"));

                            if (!seems) {
                                affectationmagasinSFacade.remove(as);

                            }
                            if (!seemp) {
                                affectationmagasinPFacade.remove(ap);

                            }
                            if (!seec) {
                                affectationControleursFacade.remove(ac);

                            }

                            p1.setRole("magasinier secondaire");
                            AffectationmagasinS s = new AffectationmagasinS();
                            s.setPersonnel(personnelFacade.find(p1.getIdPersonnel()));
                            s.setMagasinS(magasinSecondaireFacade.find(magasinMS));
                            affectationmagasinSFacade.create(s);

                            p1.getLoginList().setNiveauAcces(2);
                            p1.setAffectationmagasinSList(affectationmagasinSFacade.findByIDPersonnel(p1.getIdPersonnel()));
                            MagasinSecondaire ms = magasinSecondaireFacade.find(magasinMS);
                            ms.setAffectationmagasinSList(affectationmagasinSFacade.findAllByIdMS(ms.getIdMagasin()));
                            magasinSecondaireFacade.edit(ms);

                            if (request.getParameterValues("pages") != null) {
                                List<Page> l = new ArrayList<>();
                                // List<Page> ls = new ArrayList<>();

                                String[] pageMS = request.getParameterValues("pages");
                                if (pageMS.length >= 1) {

                                    for (String pag : pageMS) {
                                        Page p2 = pageFacade.find(Integer.parseInt(pag));
                                        l.add(p2);

                                    }

                                    permissionsFacade.removeByIdGroupe(groupe);
//                                date_du_jour.DeletePermissiond(groupe);
                                    List<Permissions> pss = new ArrayList<>();
                                    if (!l.isEmpty()) {

                                        l.stream().map((page) -> {
                                            Permissions permises = new Permissions();
                                            permises.setPage(page);
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setGroupe(groupesFacade.find(groupe));
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setAction(new Actions(1));
                                            return permises;
                                        }).map((permises) -> {
                                            permissionsFacade.create(permises);
                                            return permises;
                                        }).forEach((permises) -> {
                                            pss.add(permises);
                                        });
                                    }
                                    Groupes g = groupesFacade.find(groupe);
                                    g.setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    groupesFacade.edit(g);
                                    p1.getLoginList().setGroupes(g);
                                    p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    personnelFacade.edit(p1);
                                    l.clear();

                                } else {
                                    request.setAttribute("info", "Veuiller Sélectionner Au Moins 2 Pages D'Accès");
                                }

                            }
                            break;

                        case 3:

//                        boolean isnewp = false;
                            magasinMP = Integer.parseInt(request.getParameter("magasin"));
//                            isnewp = true;

                            if (!seems) {
                                affectationmagasinSFacade.remove(as);

                            }
                            if (!seemp) {
                                affectationmagasinPFacade.remove(ap);

                            }
                            if (!seec) {
                                affectationControleursFacade.remove(ac);

                            }
                            p1.setRole("magasinier principal");
                            AffectationmagasinP aff = new AffectationmagasinP();
                            aff.setPersonnel(personnelFacade.find(p1.getIdPersonnel()));
                            aff.setMagasinP(magasinPrincipalFacade.find(magasinMP));
                            affectationmagasinPFacade.create(aff);
                            //Groupes gg = groupesFacade.find(groupe);
                            p1.getLoginList().setNiveauAcces(3);
                            p1.setAffectationmagasinPList(affectationmagasinPFacade.findByIDPersonnel(p1.getIdPersonnel()));
                            MagasinPrincipal mp = magasinPrincipalFacade.find(magasinMP);
                            mp.setAffectationmagasinPList(affectationmagasinPFacade.findAllByIdMS(magasinMP));
                            magasinPrincipalFacade.edit(mp);
                            // p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findByIdGroupe(groupe));
                            p1.setAffectationmagasinPList(affectationmagasinPFacade.findAllByIdMS(mp.getIdMagasin()));

                            if (request.getParameterValues("pages") != null) {
                                List<Page> l = new ArrayList<>();

                                String[] pageMS = request.getParameterValues("pages");
                                if (pageMS.length > 1) {

                                    for (String pag : pageMS) {
                                        Page p2 = pageFacade.find(Integer.parseInt(pag));
                                        l.add(p2);

                                    }

                                    List<Permissions> pss = new ArrayList<>();
                                    permissionsFacade.removeByIdGroupe(groupe);
                                    //date_du_jour.DeletePermissiond(groupe);
                                    if (!l.isEmpty()) {
                                        l.stream().map((page) -> {
                                            Permissions permises = new Permissions();
                                            permises.setPage(page);
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setGroupe(new Groupes(groupe));
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setAction(new Actions(1));
                                            return permises;
                                        }).map((permises) -> {
                                            permissionsFacade.create(permises);
                                            return permises;
                                        }).forEach((permises) -> {
                                            pss.add(permises);
                                        });
                                    }
                                    Groupes g = groupesFacade.find(groupe);
                                    g.setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    groupesFacade.edit(g);
                                    p1.getLoginList().setGroupes(g);
                                    p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    personnelFacade.edit(p1);
                                    l.clear();

                                } else {
                                    request.setAttribute("info", "Veuillez Selectionner Au Moins 2 Pages D'Accès");
                                }

                            }
                            break;
                        case 4:

//                        boolean isnewc = false;
                            control = Integer.parseInt(request.getParameter("type_controle"));
//                            isnewc = true;

                            if (!seems) {
                                affectationmagasinSFacade.remove(as);

                            }
                            if (!seemp) {
                                affectationmagasinPFacade.remove(ap);

                            }
                            if (!seec) {
                                affectationControleursFacade.remove(ac);

                            }

                            p1.setRole("controleur");
                            AffectationControleurs acc = new AffectationControleurs();
                            acc.setPersonnel(p1);
                            acc.setResponsableValidation(responsablevalidationFacade.find(control));
                            affectationControleursFacade.create(acc);
                            p1.getLoginList().setNiveauAcces(4);
                            p1.setAffectationControleursList(affectationControleursFacade.findByIDPersonnel(p1.getIdPersonnel()));
                            Responsablevalidation r = responsablevalidationFacade.find(control);
                            r.setAffectationControleursList(affectationControleursFacade.findByidResponsable(control));
                            responsablevalidationFacade.edit(r);

                            if (request.getParameterValues("pages") != null) {
                                List<Page> l = new ArrayList<>();

                                String[] pageMS = request.getParameterValues("pages");
                                if (pageMS.length > 1) {

                                    for (String pag : pageMS) {
                                        Page p2 = pageFacade.find(Integer.parseInt(pag));
                                        l.add(p2);

                                    }

                                    permissionsFacade.removeByIdGroupe(groupe);
                                    List<Permissions> pss = new ArrayList<>();
                                    //date_du_jour.DeletePermissiond(groupe);
                                    if (!l.isEmpty()) {
                                        l.stream().map((page) -> {
                                            Permissions permises = new Permissions();
                                            permises.setPage(page);
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setGroupe(new Groupes(groupe));
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setAction(new Actions(1));
                                            return permises;
                                        }).map((permises) -> {
                                            permissionsFacade.create(permises);
                                            return permises;
                                        }).forEach((permises) -> {
                                            pss.add(permises);
                                        });
                                    }
                                    Groupes g = groupesFacade.find(groupe);
                                    g.setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    groupesFacade.edit(g);
                                    p1.getLoginList().setGroupes(g);
                                    p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    personnelFacade.edit(p1);
                                    l.clear();

                                } else {
                                    request.setAttribute("info", "Veuillez Sélectionner Au Moins 2 Pages D'Accès");
                                }

                            }
                            break;

                        case 5:
                            if (Integer.parseInt(request.getParameter("affectation")) != p1.getLoginList().getNiveauAcces()) {
                                p1.setRole("Administrateur");
                                p1.getLoginList().setNiveauAcces(5);
                            }

                            if (!seems) {
                                affectationmagasinSFacade.remove(as);

                            }
                            if (!seemp) {
                                affectationmagasinPFacade.remove(ap);

                            }
                            if (!seec) {
                                affectationControleursFacade.remove(ac);

                            }
                            // p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findByIdGroupe(groupe));
                            if (request.getParameterValues("pages") != null) {
                                List<Page> l = new ArrayList<>();

                                String[] pageMS = request.getParameterValues("pages");
                                if (pageMS.length > 1) {

                                    for (String pag : pageMS) {
                                        Page p2 = pageFacade.find(Integer.parseInt(pag));
                                        l.add(p2);

                                    }

                                    List<Permissions> pss = new ArrayList<>();
                                    permissionsFacade.removeByIdGroupe(groupe);
                                    if (!l.isEmpty()) {
                                        l.stream().map((page) -> {
                                            Permissions permises = new Permissions();
                                            permises.setPage(page);
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setGroupe(new Groupes(groupe));
                                            return permises;
                                        }).map((permises) -> {
                                            permises.setAction(new Actions(1));
                                            return permises;
                                        }).map((permises) -> {
                                            permissionsFacade.create(permises);
                                            return permises;
                                        }).forEach((permises) -> {
                                            pss.add(permises);
                                        });
                                    }
                                    Groupes g = groupesFacade.find(groupe);
                                    g.setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    groupesFacade.edit(g);
                                    if (g.getRegion() != null) {
                                        p1.getLoginList().setAdminRegion(2);
                                    }
                                    p1.getLoginList().setGroupes(g);
                                    p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                    personnelFacade.edit(p1);
                                    l.clear();

                                } else {
                                    request.setAttribute("info", "Veuillez Sélectionner Au Moins 2 Pages D'Accès");
                                }

                            }
                            break;

                        case 1:

                            p1.setRole("Personnel Simple");
                            boolean isnewper = false;
                            try {
                                p1.getLoginList().setNiveauAcces(1);
                            } catch (Exception e) {
                                isnewper = true;
                            }
                            if (!isnewper) {

                                if (!seems) {
                                    affectationmagasinSFacade.remove(as);

                                }
                                if (!seemp) {
                                    affectationmagasinPFacade.remove(ap);

                                }
                                if (!seec) {
                                    affectationControleursFacade.remove(ac);

                                }
                                // p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findByIdGroupe(groupe));
                                if (request.getParameterValues("pages") != null) {
                                    List<Page> l = new ArrayList<>();

                                    String[] pageMS = request.getParameterValues("pages");
                                    if (pageMS.length > 1) {

                                        for (String pag : pageMS) {
                                            Page p2 = pageFacade.find(Integer.parseInt(pag));
                                            l.add(p2);

                                        }

                                        permissionsFacade.removeByIdGroupe(groupe);

                                        List<Permissions> pss = new ArrayList<>();
                                        if (!l.isEmpty()) {
                                            l.stream().map((page) -> {
                                                Permissions permises = new Permissions();
                                                permises.setPage(page);
                                                return permises;
                                            }).map((permises) -> {
                                                permises.setGroupe(new Groupes(groupe));
                                                return permises;
                                            }).map((permises) -> {
                                                permises.setAction(new Actions(1));
                                                return permises;
                                            }).map((permises) -> {
                                                permissionsFacade.create(permises);
                                                return permises;
                                            }).forEach((permises) -> {
                                                pss.add(permises);
                                            });
                                        }
                                        Groupes g = groupesFacade.find(groupe);
                                        g.setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                        groupesFacade.edit(g);
                                        p1.getLoginList().setGroupes(g);
                                        p1.getLoginList().getGroupes().setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe));
                                        personnelFacade.edit(p1);
                                        l.clear();

                                    } else {
                                        request.setAttribute("info", "Veuillez Sélectionner Au Moins 2 Pages D'Accès");
                                    }

                                }
                            } else {
                                request.setAttribute("info", "Veillez Donner Un Login A cet Utilisateur");
                            }
                            break;

                        default:
                            break;

                    }
                    try {
                        Personnel pers = personnelFacade.find(p1.getIdPersonnel());
                        Groupes g = groupesFacade.find(pers.getLoginList().getGroupes().getIdGroupes());
                        g.setLoginList(loginFacade.findAllByIdGroupe(g.getIdGroupes()));
                        pers.getLoginList().setGroupes(g);
                        List<Groupes> groupes = groupesFacade.findAll();
                        request.setAttribute("groupes", groupes);
                        request.setAttribute("groupe", groupesFacade.find(pers.getLoginList().getGroupes().getIdGroupes()));
                        List<Page> pages = pageFacade.findByNiveau(pers.getLoginList().getGroupes().getNiveau());
                        request.setAttribute("pages", pages);
                        request.setAttribute("personnel", pers);
                    } catch (Exception e) {
                    }

                    List<Personnel> personnelss = personnelFacade.findAll();
                    request.setAttribute("personnels", personnelss);

                    request.setAttribute("size", request.getParameterValues("pages").length);
                }
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("etat", "OK");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("AddAppariel")) {
                boolean emptyuser = false;
                String numerosparck = request.getParameter("numeroParck");

                String lieu = request.getParameter("lieu");
                Personnel personnels = new Personnel();
                Appariel a = new Appariel();

                if (!request.getParameter("utilisateur").isEmpty()) {
                    int id = Integer.parseInt(request.getParameter("utilisateur"));
                    personnels = personnelFacade.find(id);
                    a.setProprietaire(personnels);
                } else {
                    emptyuser = true;
                }

                if (request.getParameter("numeroSerie") != null) {
                    String serie = request.getParameter("numeroSerie");
                    a.setNumeroSerie(serie);
                }
                if (request.getParameter("model") != null) {
                    String model = request.getParameter("model");
                    a.setModel(model);
                }
                if (request.getParameter("fabricant") != null) {
                    String fabricant = request.getParameter("fabricant");
                    a.setFabricant(fabricant);
                }
                if (request.getParameter("type") != null) {
                    int type = Integer.parseInt(request.getParameter("type"));
                    TypeAppareil app = typeAppareilFacade.find(type);
                    a.setTypeAppareil(app);
                }

                a.setLieu(lieu);

                a.setNumeroParck(numerosparck);

                apparielFacade.create(a);
                if (!emptyuser) {
                    personnels.setApparielList(apparielFacade.findAllApparielByPersonnel(personnels.getIdPersonnel()));
                    personnelFacade.edit(personnels);
                }

                List<Appariel> appariels = apparielFacade.findAll();
                request.setAttribute("appariels", appariels);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("vue", vue);
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("typeappareil", typeAppareilFacade.findAll());
                String redirect;
                if (request.getParameter("niveau") != null) {
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                } else {
                    redirect = "/WEB-INF/administrateur/main1.jsp";
                }
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("EditAppariel")) {

                //  int id = Integer.parseInt(request.getParameter("utilisateur"));
                String numerosparck = request.getParameter("numeroParck");
                String model = request.getParameter("model");
                int type = Integer.parseInt(request.getParameter("type"));
                String lieu = request.getParameter("lieu");
                String serie = request.getParameter("numeroSerie");
                String fabricant = request.getParameter("fabricant");
                boolean emptyuser = false;
                Personnel p1 = new Personnel();
                Appariel a = apparielFacade.find(numerosparck);
                if (!request.getParameter("utilisateur").isEmpty()) {
                    int id = Integer.parseInt(request.getParameter("utilisateur"));
                    p1 = personnelFacade.find(id);
                    a.setProprietaire(p1);
                } else {
                    a.setProprietaire(null);
                    emptyuser = true;
                }
                TypeAppareil typeapp = typeAppareilFacade.find(type);

                a.setTypeAppareil(typeapp);
                a.setFabricant(fabricant);
                a.setLieu(lieu);
                a.setModel(model);
                a.setNumeroParck(numerosparck);
                a.setNumeroSerie(serie);
                apparielFacade.edit(a);
                List<Appariel> appariels = apparielFacade.findAll();

                if (!emptyuser) {
                    p1.setApparielList(apparielFacade.findAllApparielByPersonnel(p1.getIdPersonnel()));
                    personnelFacade.edit(p1);
                }

                request.setAttribute("appariels", appariels);
                List<Personnel> personnelss = personnelFacade.findAll();
                request.setAttribute("personnels", personnelss);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("personnel", a.getProprietaire());
                request.setAttribute("regions", regions);
                request.setAttribute("vue", vue);
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("typeappareil", typeAppareilFacade.findAll());
                String redirect;
                if (request.getParameter("niveau") != null) {
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                } else {
                    redirect = "/WEB-INF/administrateur/main1.jsp";
                }
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            } else if (action.equalsIgnoreCase("selectApparielToEdite")) {
                String numparck = request.getParameter("numeroParck");
                Appariel appariel = apparielFacade.find(numparck);

                request.setAttribute("selectToEdite", "edite");
                request.setAttribute("apparielSelect", appariel);
                request.setAttribute("fabriquants", frabriquantAppareilFacade.findAll());
                List<Personnel> personnelss = personnelFacade.findAll();
                // appariel.getProprietaire().getApparielList().remove(0);
                request.setAttribute("personnel", appariel.getProprietaire());
                request.setAttribute("personnels", personnelss);
                request.setAttribute("typeappareil", typeAppareilFacade.findAll());
                request.setAttribute("sites", siteFacade.findAll());
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("vue", vue);
                String redirect;
                if (request.getParameter("niveau") != null) {
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                } else {
                    redirect = "/WEB-INF/administrateur/main1.jsp";
                }
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deletteAppariel")) {
                String numparck = request.getParameter("numeroParck");
                Appariel appariel = apparielFacade.find(numparck);
                apparielFacade.remove(appariel);
                boolean error = false;
                try {
                    appariel.getProprietaire().getIdPersonnel();
                } catch (Exception e) {
                    error = true;
                }
                if (!error) {
                    int idPersonel = appariel.getProprietaire().getIdPersonnel();
                    Personnel p1 = personnelFacade.find(idPersonel);
                    p1.setApparielList(apparielFacade.findAllApparielByPersonnel(idPersonel));
                    request.setAttribute("personnel", p1);
                }

                List< Appariel> appariels = apparielFacade.findAll();
                request.setAttribute("appariels", appariels);
                List<Personnel> personnelss = personnelFacade.findAll();
                request.setAttribute("personnels", personnelss);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("vue", vue);
                request.setAttribute("sites", siteFacade.findAll());
                request.setAttribute("typeappareil", typeAppareilFacade.findAll());
                String redirect;
                if (request.getParameter("niveau") != null) {
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                } else {
                    redirect = "/WEB-INF/administrateur/main1.jsp";
                }
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            } else if (action.equalsIgnoreCase("addControlleur")) {

                int IdPersonnel = Integer.parseInt(request.getParameter("personnel"));
                int IdControl = Integer.parseInt(request.getParameter("typeControl"));

                Personnel personnel = personnelFacade.find(IdPersonnel);
                AffectationControleurs controlleur = new AffectationControleurs();

                controlleur.setPersonnel(new Personnel(IdPersonnel));
                controlleur.setResponsableValidation(new Responsablevalidation(IdControl));
                affectationControleursFacade.create(controlleur);
                personnel.setAffectationControleursList(affectationControleursFacade.findLastInsert());
                personnelFacade.edit(personnel);
                List<Personnel> personnels = personnelFacade.findAll();
                List<Responsablevalidation> typecontrols = responsablevalidationFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                List<AffectationControleurs> controleur = affectationControleursFacade.findAll();
                request.setAttribute("vue", vue);
                request.setAttribute("responsables", controleur);
                request.setAttribute("personnels", personnels);
                request.setAttribute("regions", regions);
                request.setAttribute("typecontrols", typecontrols);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("DelettePersonnel")) {
                int IdPersonnel = Integer.parseInt(request.getParameter("personnel"));
                Personnel personnel = personnelFacade.find(IdPersonnel);
                personnelFacade.remove(personnel);
                List<Region> regions = regionFacade.findAll();
                List<Personnel> personnels = personnelFacade.findAll();
                request.setAttribute("personnels", personnels);
                request.setAttribute("regions", regions);
                request.setAttribute("directions", directionFacade.findAll());
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("DeletteMS")) {
                int idMS = Integer.parseInt(request.getParameter("idMS"));
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                magasinSecondaireFacade.remove(ms);
                List<MagasinSecondaire> l = magasinSecondaireFacade.findAll();
                request.setAttribute("MagasinsS", l);
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("vue", vue);
                request.setAttribute("all", "yes");
                request.setAttribute("sorti", "OK");
                // request.setAttribute("form", "ONE");
                // request.setAttribute("forme", "ONE");
                request.setAttribute("parametre", "OK");
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("DeletteMP")) {
                int idMP = Integer.parseInt(request.getParameter("idMP"));
                MagasinPrincipal mp = magasinPrincipalFacade.find(idMP);
                magasinPrincipalFacade.remove(mp);
                List<MagasinPrincipal> mps = magasinPrincipalFacade.findAll();
                List<Region> regions = regionFacade.findAll();
                request.setAttribute("regions", regions);
                request.setAttribute("MagasinsP", mps);
                request.setAttribute("listMP", mps);
                //request.setAttribute("form", "NON");
                //request.setAttribute("forme", "ONE");
                request.setAttribute("parametre", "OK");
                request.setAttribute("all", "yes");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("confirmedelete")) {

                String password = request.getParameter("password");
                String user = request.getParameter("user");
                List<Login> ls = loginFacade.findAlUser(user, password);
                if (!ls.isEmpty()) {
                    response.setContentType("application/text");
                    response.getWriter().print(ls.get(0).getPersonnel().getIdPersonnel().toString());
                } else {
                    response.setContentType("application/text");
                    response.getWriter().print(0);
                }
            }

        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

package Authentification;

import com.gciapplication.entity.Agenda;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.Founisseur;
import com.gciapplication.entity.Groupes;
import com.gciapplication.entity.Login;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.Page;
import com.gciapplication.entity.Permissions;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Site;
import com.gciapplication.entity.Societe;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.AffectationControleursFacadeLocal;
import com.gciapplication.session.entity.AffectationmagasinPFacadeLocal;
import com.gciapplication.session.entity.AffectationmagasinSFacadeLocal;
import com.gciapplication.session.entity.AgendaFacadeLocal;
import com.gciapplication.session.entity.ColorFacadeLocal;
import com.gciapplication.session.entity.FounisseurFacadeLocal;
import com.gciapplication.session.entity.GroupesFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.PageFacadeLocal;
import com.gciapplication.session.entity.PermissionsFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import com.gciapplication.session.entity.SocieteFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name = "Authentification", urlPatterns = "/authentification")
public class Authentification extends HttpServlet {

    @EJB
    private PageFacadeLocal pageFacade;

    @EJB
    private ColorFacadeLocal colorFacade;

    @EJB
    private AgendaFacadeLocal agendaFacade;

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private SiteFacadeLocal siteFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private GroupesFacadeLocal groupesFacade;

    @EJB
    private AffectationmagasinSFacadeLocal affectationmagasinSFacade;

    @EJB
    private AffectationmagasinPFacadeLocal affectationmagasinPFacade;

    @EJB
    private AffectationControleursFacadeLocal affectationControleursFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private FounisseurFacadeLocal founisseurFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private LoginFacadeLocal loginFacade;

    List<Login> ls;

    List<Societe> Societe;

    String deconnexion = "vraie";
    String region;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        deconnexion = request.getParameter("action");
        if (deconnexion.equalsIgnoreCase("faut")) {

            HttpSession session = request.getSession();

            session.invalidate();

            request.setAttribute("deconnexion", "yes");
            request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = null;
        // Personnel personnel;
        String vue = "Accueil";
        String password = request.getParameter("password");
        String user = request.getParameter("user");
        if (!contener()) {
            ls = new ArrayList<>();
            ls = loginFacade.findAlUser(user, password);

            if (!ls.isEmpty()) {
                // Personnel personnel = new Personnel();
                List<Societe> societe = societeFacade.findAll();

                /*
            try {
                envoiEmail.EnvoiEmail("messi.charly155@gmail.com", "teste", "tous ces bien Passer");
            } catch (MessagingException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            }
                 */
                switch (ls.get(0).getNiveauAcces()) {
                    case 5:
                        session = request.getSession();
                        if (!societe.isEmpty()) {
                            session.setAttribute("societe", societe.get(0));
                            session.setAttribute("All_Groupe", groupesFacade.findByAllDefaultGroupe());
                        }

                        session.setAttribute("login", ls.get(0));
                        session.setAttribute("id", ls.get(0).getPersonnel().getIdPersonnel());
                        Personnel personne = personnelFacade.find(ls.get(0).getPersonnel().getIdPersonnel());
                        com.gciapplication.entity.Color c = colorFacade.findAll().get(0);
                        session.setAttribute("couleur", c.getColor());

                        if (ls.get(0).getAdminRegion() == 2) {
                            Login log = new Login();
                            // List<Permissions> permissionis = new ArrayList<>();
                            Groupes group = new Groupes();
                            group.setIdGroupes(ls.get(0).getGroupes().getIdGroupes());
                            group.setNomGroupe(ls.get(0).getGroupes().getNomGroupe());
                            group.setNiveau(ls.get(0).getNiveauAcces());
                            List<Permissions> permissionis = permissionsFacade.findAllByIdGrpoupe(group.getIdGroupes());
                            group.setPermissionsList(permissionis);
                            log.setGroupes(group);
                            personne.setLoginList(log);
                            session.setAttribute("adminRegion", personne.getService().getSite().getRegion().getNomRegion());
                            List<MagasinSecondaire> ListMS = magasinSecondaireFacade.findByRegion(personne.getService().getSite().getRegion().getNomRegion());
                            session.setAttribute("magasinS", ListMS);
                            for (Permissions pageAcce : personne.getLoginList().getGroupes().getPermissionsList()) {
                                switch (pageAcce.getPage().getNomPage()) {
                                    case "Liste Du Personnels":
                                        session.setAttribute("lien1", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un Personnel":
                                        session.setAttribute("lien2", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Les Donnees Du Personnel":
                                        session.setAttribute("lien3", pageAcce.getPage().getLien());
                                        break;
                                    case "Mis A Jour Donnees Du Personnel":
                                        session.setAttribute("lien4", pageAcce.getPage().getLien());
                                        break;
                                    case "Change Password Personnel":
                                        session.setAttribute("lien5", pageAcce.getPage().getLien());
                                        break;
                                    case "changer Groupe Personnel":
                                        session.setAttribute("lien6", pageAcce.getPage().getLien());
                                        break;
                                    case "Update Appariels Personnel":
                                        session.setAttribute("lien7", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande Encour Personnel":
                                        session.setAttribute("lien8", pageAcce.getPage().getLien());
                                        break;
                                    case "Operations Personnel":
                                        session.setAttribute("lien9", pageAcce.getPage().getLien());
                                        break;
                                    case "Liste Des controleurs":
                                        session.setAttribute("lien10", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande En Attente De Validation":
                                        session.setAttribute("lien11", pageAcce.getPage().getLien());
                                        break;
                                    case "Listes Des Appariels Du Personnels":
                                        session.setAttribute("lien12", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un Appariel":
                                        session.setAttribute("lien13", pageAcce.getPage().getLien());
                                        break;
                                    case "Mettre A Jour Les Appariels":
                                        session.setAttribute("lien14", pageAcce.getPage().getLien());
                                        break;
                                    case "Liste Des Magasins Secondaires":
                                        session.setAttribute("lien15", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un magasin Secondaire":
                                        session.setAttribute("lien16", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Stock Pour Le M.S":
                                        session.setAttribute("lien17", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Le Stock Des M.S":
                                        session.setAttribute("lien18", pageAcce.getPage().getLien());
                                        break;
                                    case "Etablir L'Etat Consommation Du Personnel":
                                        session.setAttribute("lien19", pageAcce.getPage().getLien());
                                        break;
                                    case "Etat Consommation Général M.S":
                                        session.setAttribute("lien20", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Les Commandes En Cours Du M.S":
                                        session.setAttribute("lien21", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvement Des Sortis Des Produits Du M.S":
                                        session.setAttribute("lien22", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvement Des Entrer Des Produits Du M.S":
                                        session.setAttribute("lien23", pageAcce.getPage().getLien());
                                        break;
                                    case "Historiques Des Inventaire M.S":
                                        session.setAttribute("lien24", pageAcce.getPage().getLien());
                                        break;
                                    case "Commandes Du Personnels Recus Par Le M.S":
                                        session.setAttribute("lien25", pageAcce.getPage().getLien());
                                        break;
                                    case "Transfert Reçus Par M.S":
                                        session.setAttribute("lien26", pageAcce.getPage().getLien());
                                        break;
                                    case "Passer Une Commande Pour Le M.S":
                                        session.setAttribute("lien27", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Les Statistiques D’entrer Du M.S":
                                        session.setAttribute("lien28", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Les Statistiques De Sortis Du M.S":
                                        session.setAttribute("lien29", pageAcce.getPage().getLien());
                                        break;
                                    case "Liste Des Magasins Principaux":
                                        session.setAttribute("lien30", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Une Magasin Principal":
                                        session.setAttribute("lien31", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Des Stocks Pour Les M.P":
                                        session.setAttribute("lien32", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Les Stocks Des M.P":
                                        session.setAttribute("lien33", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir L'Etat Général De Consommation Des M.P":
                                        session.setAttribute("lien34", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande Non Réceptionner M.P":
                                        session.setAttribute("lien35", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande déjà Réceptionner M.P":
                                        session.setAttribute("lien36", pageAcce.getPage().getLien());
                                        break;
                                    case "Commandes Déjà Traités":
                                        session.setAttribute("lien37", pageAcce.getPage().getLien());
                                        break;
                                    case "Suivis Des Transferts Des M.P":
                                        session.setAttribute("lien38", pageAcce.getPage().getLien());
                                        break;
                                    case "Commandes Du M.S Reçus Par M.P":
                                        session.setAttribute("lien39", pageAcce.getPage().getLien());
                                        break;
                                    case "Historiques Des Bordereaux M.P":
                                        session.setAttribute("lien40", pageAcce.getPage().getLien());
                                        break;
                                    case "Passer Des Commandes Pour M.P":
                                        session.setAttribute("lien41", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Les Historiques D'Inventaires Des M.P":
                                        session.setAttribute("lien42", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvements D’entrer Des Produits M.P":
                                        session.setAttribute("lien43", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvements Sortis Des Produits M.P":
                                        session.setAttribute("lien44", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Entrer Produits M.P":
                                        session.setAttribute("lien45", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Sortis Produits M.P":
                                        session.setAttribute("lien46", pageAcce.getPage().getLien());
                                        break;
                                    case "Passer Une Commande Interne":
                                        session.setAttribute("lien47", pageAcce.getPage().getLien());
                                        break;
                                    case "Verrouille L’écran":
                                        session.setAttribute("lien48", pageAcce.getPage().getLien());
                                        break;
                                    case "Profil Administrateur":
                                        session.setAttribute("lien49", pageAcce.getPage().getLien());
                                        break;
                                    case "Deconnexion":
                                        session.setAttribute("lien50", pageAcce.getPage().getLien());
                                        break;
                                    case "Change Therme":
                                        session.setAttribute("lien51", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter une Region":
                                        session.setAttribute("lien52", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Une Direction":
                                        session.setAttribute("lien53", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Une Site":
                                        session.setAttribute("lien54", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un Centre De Cout":
                                        session.setAttribute("lien66", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un Service":
                                        session.setAttribute("lien55", pageAcce.getPage().getLien());
                                        break;
                                    case "Créer Un Groupe":
                                        session.setAttribute("lien56", pageAcce.getPage().getLien());
                                        break;
                                    case "Mis A Jour Groupes":
                                        session.setAttribute("lien57", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un Bureau De Control":
                                        session.setAttribute("lien58", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un Fournisseur":
                                        session.setAttribute("lien59", pageAcce.getPage().getLien());
                                        break;
                                    case "Créer Une Société":
                                        session.setAttribute("lien60", pageAcce.getPage().getLien());
                                        break;
                                    case "Envoyer Un E-Mail":
                                        session.setAttribute("lien61", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Region":
                                        session.setAttribute("lien62", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Site":
                                        session.setAttribute("lien63", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Service":
                                        session.setAttribute("lien64", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Societe":
                                        session.setAttribute("lien65", pageAcce.getPage().getLien());
                                        break;
                                    case "tableau de bord":
                                        session.setAttribute("lien66", pageAcce.getPage().getLien());
                                        break;
                                    case "reporting":
                                        session.setAttribute("lien67", pageAcce.getPage().getLien());
                                        break;
                                    default:
                                        session.setAttribute("AdminRegional", "OK");
                                        break;
                                }

                            }
                        } else {
                            session.setAttribute("GeneralAdministrateur", "OK");
                        }

                        session.setAttribute("personnel", personne);

                        ls.clear();
                        List<Agenda> monagenda = agendaFacade.findByIdPersonnelAndDate(personne.getIdPersonnel(), date_du_jour.dateJour());
                        session.setAttribute("agenda", monagenda);
                        session.setAttribute("societe", societeFacade.find(Integer.parseInt("1")));
                        request.setAttribute("vue", "accueil");
                        request.setAttribute("deconnexion", "yes");
                        session.setAttribute("niveau", 5);
                        request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                        break;
                    case 4:
                        session = request.getSession();
                        if (!societe.isEmpty()) {
                            session.setAttribute("societe", societe.get(0));
                        }
                        session.setAttribute("niveau", ls.get(0).getNiveauAcces());
                        session.setAttribute("regions", regionFacade.findAll());
                        session.setAttribute("login", ls.get(0));
                        com.gciapplication.entity.Color c3 = colorFacade.findAll().get(0);
                        session.setAttribute("couleur", c3.getColor());
                        Personnel personnel = personnelFacade.find(ls.get(0).getPersonnel().getIdPersonnel());
                        session.setAttribute("id_notif", ls.get(0).getPersonnel().getIdPersonnel());

                        if (personnel != null) {
                            Login ll = new Login();
                            //  List<Permissions> permissions = new ArrayList<>();
                            Groupes groupe = new Groupes();
                            groupe.setIdGroupes(ls.get(0).getGroupes().getIdGroupes());
                            groupe.setNomGroupe(ls.get(0).getGroupes().getNomGroupe());
                            groupe.setNiveau(ls.get(0).getNiveauAcces());
                            List<Permissions> permissions = permissionsFacade.findAllByIdGrpoupe(groupe.getIdGroupes());
                            groupe.setPermissionsList(permissions);
                            ll.setGroupes(groupe);
                            personnel.setLoginList(ll);

                            session.setAttribute("id_valideur", ls.get(0).getPersonnel().getIdPersonnel());
                            region = personnel.getService().getSite().getRegion().getNomRegion();

                            Site s = siteFacade.find(personnel.getService().getSite().getIdSite());
                            List<MagasinSecondaire> ListMS = magasinSecondaireFacade.findByRegion(s.getRegion().getNomRegion());
                            session.setAttribute("magasinS", ListMS);
                            session.setAttribute("id", ls.get(0).getPersonnel().getIdPersonnel());

                            personnel.setAffectationControleursList(affectationControleursFacade.findByIDPersonnel(personnel.getIdPersonnel()));
                            for (Permissions pageAcce : personnel.getLoginList().getGroupes().getPermissionsList()) {
                                switch (pageAcce.getPage().getNomPage()) {
                                    case "Commande Personnel":
                                        session.setAttribute("lien1", pageAcce.getPage().getLien());
                                        break;
                                    case "Commandes a Traiter":
                                        session.setAttribute("lien2", pageAcce.getPage().getLien());
                                        break;
                                    case "Contact":
                                        session.setAttribute("lien3", pageAcce.getPage().getLien());
                                        break;
                                    case "Deconnexion":
                                        session.setAttribute("DeconnexionControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Changer Son Mot De Passe":
                                        session.setAttribute("ChangePasseControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Ces Appariels":
                                        session.setAttribute("ApparielControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Consulte Tous Ces Operations":
                                        session.setAttribute("OperationControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Ces Informations":
                                        session.setAttribute("InfoControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Verrouille L'ecran":
                                        session.setAttribute("Ecran", pageAcce.getPage().getLien());
                                        break;
                                    case "Commandes Effectuer Au MS":
                                        session.setAttribute("CommandeCourControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Mouvements Des Produits En Sortie M.P":
                                        session.setAttribute("MouvSMPControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Mouvements Des Produits En Entrer M.P":
                                        session.setAttribute("MouvEMPControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Mouvements Des Produits En Sortie M.S":
                                        session.setAttribute("MouvSMSControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Mouvements Des Produits En Entrer M.S":
                                        session.setAttribute("MouvEMSControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Consommations Produits M.S":
                                        session.setAttribute("StatistiqueMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistiques Consommations Produits M.P":
                                        session.setAttribute("StatistiqueMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Etat Consommation General M.S":
                                        session.setAttribute("ConsGeneralMSControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Etat Consommation General M.P":
                                        session.setAttribute("ConsGeneralMPControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Stocks Produits M.S":
                                        session.setAttribute("StockMSControl", pageAcce.getPage().getLien());
                                        break;
                                    case "Etat Budgetaire Region":
                                        session.setAttribute("budget", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Ces Commandes":
                                        session.setAttribute("commandePerso", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Stocks Produits M.P":
                                        session.setAttribute("StockMPControl", pageAcce.getPage().getLien());
                                        break;
                                    case "tableau de bord":
                                        session.setAttribute("tableauBord", pageAcce.getPage().getLien());
                                        break;
                                    case "reporting":
                                        session.setAttribute("reporting", pageAcce.getPage().getLien());
                                        break;
                                    default:
                                        break;
                                }

                            }
                            List<MagasinSecondaire> secondaires = magasinSecondaireFacade.findByRegion(personnel.getService().getSite().getRegion().getNomRegion());
                            session.setAttribute("secondaires", secondaires);
                            List<MagasinPrincipal> principals = magasinPrincipalFacade.findAllByregion(personnel.getService().getSite().getRegion().getNomRegion());
                            session.setAttribute("principals", principals);
                            session.setAttribute("lienAccueil", "ValiderCommande?vue=Accueil&action=autre");
                            List<Notification> notification = notificationFacade.findAllNotificationForOne(personnel.getIdPersonnel(), 0);
                            session.setAttribute("notifications", notification);
                            session.setAttribute("size_notifications", notification.size());
                            request.setAttribute("deconnexion", "yes");
                            session.setAttribute("personnel", personnel);
                            session.setAttribute("controle", affectationControleursFacade.findByIDPersonnel(personnel.getIdPersonnel()).get(0).getResponsableValidation());
                            request.setAttribute("vue", vue);
                            ls.removeAll(ls);
                            List<Agenda> monagendaC = agendaFacade.findByIdPersonnelAndDate(personnel.getIdPersonnel(), date_du_jour.dateJour());
                            session.setAttribute("agenda", monagendaC);
                            session.setAttribute("societe", societeFacade.findAll().get(0));
                            request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                        }
                        break;

                    case 3:

                        session = request.getSession();
                        if (!societe.isEmpty()) {
                            session.setAttribute("societe", societe.get(0));
                        }

                        session.setAttribute("regions", regionFacade.findAll());
                        session.setAttribute("login", ls.get(0));
                        com.gciapplication.entity.Color c1 = colorFacade.findAll().get(0);
                        session.setAttribute("couleur", c1.getColor());
                        Personnel personnels = personnelFacade.find(ls.get(0).getPersonnel().getIdPersonnel());

                        if (personnels != null) {
                            List<MagasinSecondaire> ListMS = magasinSecondaireFacade.findByRegion(personnels.getService().getSite().getRegion().getNomRegion());
                            session.setAttribute("magasinS", ListMS);
                            Login ll = new Login();

                            Groupes groupe = new Groupes();
                            groupe.setIdGroupes(ls.get(0).getGroupes().getIdGroupes());
                            groupe.setNomGroupe(ls.get(0).getGroupes().getNomGroupe());
                            groupe.setNiveau(ls.get(0).getNiveauAcces());
                            List<Permissions> permissions = permissionsFacade.findAllByIdGrpoupe(groupe.getIdGroupes());
                            groupe.setPermissionsList(permissions);
                            ll.setGroupes(groupe);
                            personnels.setLoginList(ll);
                            int idMP = affectationmagasinPFacade.findByIDPersonnel(personnels.getIdPersonnel()).get(0).getMagasinP().getIdMagasin();
                            MagasinPrincipal principal = magasinPrincipalFacade.find(idMP);
                            session.setAttribute("principal", principal);

                            List<Founisseur> founisseur = founisseurFacade.findAll();

                            personnels.setAffectationmagasinPList(affectationmagasinPFacade.findByIDPersonnel(personnels.getIdPersonnel()));

                            critiqueMP(request, session, idMP);

                            Login l = loginFacade.findBypersonnelID(personnels.getIdPersonnel());
                            session.setAttribute("personnel", l.getPersonnel());
                            for (Permissions pageAcce : personnels.getLoginList().getGroupes().getPermissionsList()) {
                                switch (pageAcce.getPage().getNomPage()) {
                                    case "Inventaire":
                                        session.setAttribute("lien1", pageAcce.getPage().getLien());
                                        break;
                                    case "Historique inventaire":
                                        session.setAttribute("lien2", pageAcce.getPage().getLien());
                                        break;

                                    case "Traitement Commande":
                                        session.setAttribute("lien5", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande Interne":
                                        session.setAttribute("lien6", pageAcce.getPage().getLien());
                                        break;
                                    case "Réception Commande Fournisseur":
                                        session.setAttribute("lien7", pageAcce.getPage().getLien());
                                        break;
                                    case "Etat de Stock":
                                        session.setAttribute("lien8", pageAcce.getPage().getLien());
                                        break;
                                    case "Suivi commandes":
                                        session.setAttribute("lien9", pageAcce.getPage().getLien());
                                        break;
                                    case "Etat de consommation":
                                        session.setAttribute("lien10", pageAcce.getPage().getLien());
                                        break;
                                    case "Suivi Transfert":
                                        session.setAttribute("lien11", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande Traiter":
                                        session.setAttribute("lien14", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvement Produits":
                                        session.setAttribute("lien12", pageAcce.getPage().getLien());
                                        break;
                                    case "Contact":
                                        session.setAttribute("lien13", pageAcce.getPage().getLien());
                                        break;
                                    case "Deconnexion":
                                        session.setAttribute("DeconnexionMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Changer Son Mot De Passe":
                                        session.setAttribute("ChangePasseMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Ces Appariels":
                                        session.setAttribute("ApparielMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Consulte Tous Ces Operations":
                                        session.setAttribute("OperationMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Ces Informations":
                                        session.setAttribute("InfoMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Verrouillé L'Écran":
                                        session.setAttribute("EcranMP", pageAcce.getPage().getLien());

                                        break;
                                    case "Commandes Effectuer En cour":
                                        session.setAttribute("CommandeCourMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvement Sorti Produits":
                                        session.setAttribute("MouvSortiMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvement Entrer Produits":
                                        session.setAttribute("MouvEntrerMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistique Sorti Produits":
                                        session.setAttribute("StatistiqueSortiMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistique Entrer Produits":
                                        session.setAttribute("StatistiqueEntrerMP", pageAcce.getPage().getLien());
                                        break;

                                    case "Commande Deja Traiter":
                                        session.setAttribute("CommandeTraiterMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande Receptionner":
                                        session.setAttribute("CommandeRecepMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Commandes Non Receptionner":
                                        session.setAttribute("CommandeNonRecepMP", pageAcce.getPage().getLien());
                                        break;
                                    case "Passer Des Commandes":
                                        session.setAttribute("PasserCommandeMP", pageAcce.getPage().getLien());
                                        break;
                                    default:

                                        break;
                                }

                            }
                            List<StockproduitMP> stock = stockproduitMPFacade.ProduitCritique(idMP);
                            request.setAttribute("stock", stock);
                            session.setAttribute("lienAccueil", "StatistiqueMP?vue=Accueil&action=autre");
                            List<Notification> notifications = notificationFacade.findAllNotificationForOne(idMP, 0);
                            session.setAttribute("id", ls.get(0).getPersonnel().getIdPersonnel());
                            session.setAttribute("id_magasinP", idMP);
                            session.setAttribute("id_magasin", idMP);
                            session.setAttribute("id_notif", idMP);
                            session.setAttribute("niveau", ls.get(0).getNiveauAcces());
                            session.setAttribute("notification", notifications);
                            session.setAttribute("size_notification", notifications.size());
                            session.setAttribute("personnel", personnels);
                            session.setAttribute("magasinP", affectationmagasinPFacade.findByIDPersonnel(personnels.getIdPersonnel()).get(0).getMagasinP().getNomMagasin());
                            session.setAttribute("categorieMP", affectationmagasinPFacade.findByIDPersonnel(personnels.getIdPersonnel()).get(0).getMagasinP().getCategorieproduitMPList());
                            session.setAttribute("founisseurs", founisseur);
                            request.setAttribute("vue", vue);
                            request.setAttribute("deconnexion", "yes");
                            ls.removeAll(ls);
                            List<Agenda> monagendams = agendaFacade.findByIdPersonnelAndDate(personnels.getIdPersonnel(), date_du_jour.dateJour());
                            session.setAttribute("agenda", monagendams);
                            session.setAttribute("societe", societeFacade.find(Integer.parseInt("1")));
                            request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                        }
                        break;

                    case 2:

                        session = request.getSession();

                        if (!societe.isEmpty()) {
                            session.setAttribute("societe", societe.get(0));
                        }

                        session.setAttribute("regions", regionFacade.findAll());
                        session.setAttribute("login", ls.get(0));
                        com.gciapplication.entity.Color c2 = colorFacade.findAll().get(0);
                        session.setAttribute("couleur", c2.getColor());
                        Personnel personnell = personnelFacade.find(ls.get(0).getPersonnel().getIdPersonnel());
                        if (personnell != null) {
                            List<MagasinSecondaire> ListMS = magasinSecondaireFacade.findByRegion(personnell.getService().getSite().getRegion().getNomRegion());
                            session.setAttribute("magasinS", ListMS);
                            Login ll = new Login();
                            //List<Permissions> permissions = new ArrayList<>();
                            Groupes groupe = new Groupes();
                            groupe.setIdGroupes(ls.get(0).getGroupes().getIdGroupes());
                            groupe.setNomGroupe(ls.get(0).getGroupes().getNomGroupe());
                            groupe.setNiveau(ls.get(0).getNiveauAcces());
                            List<Permissions> permissions = permissionsFacade.findAllByIdGrpoupe(personnell.getLoginList().getGroupes().getIdGroupes());
                            groupe.setPermissionsList(permissions);
                            ll.setGroupes(groupe);
                            personnell.setLoginList(ll);
                            session.setAttribute("id", ls.get(0).getPersonnel().getIdPersonnel());
                            session.setAttribute("id_magasignerS", ls.get(0).getPersonnel().getIdPersonnel());

                            personnell.setAffectationmagasinSList(affectationmagasinSFacade.findByIDPersonnel(personnell.getIdPersonnel()));
                            //personnell.setLoginList(loginFacade.find(personnell.getIdPersonnel()));

                            Societe = societeFacade.findAll();
                            List<CategorieproduitMS> categoriee = affectationmagasinSFacade.findByIDPersonnel(personnell.getIdPersonnel()).get(0).getMagasinS().getCategorieproduitMSList();
                            session.setAttribute("personnel", personnell);
                            session.setAttribute("categoriee", categoriee);
                            int id_magasin = affectationmagasinSFacade.findByIDPersonnel(personnell.getIdPersonnel()).get(0).getMagasinS().getIdMagasin();
                            session.setAttribute("id_magasin", id_magasin);
                            session.setAttribute("id_magasin_Statistique", id_magasin);
                            session.setAttribute("id_notif", id_magasin);
                            session.setAttribute("niveau", ls.get(0).getNiveauAcces());
                            session.setAttribute("magasinMS", magasinSecondaireFacade.find(id_magasin));

                            critique(request, session, id_magasin);

                            for (Permissions pageAcce : personnell.getLoginList().getGroupes().getPermissionsList()) {
                                switch (pageAcce.getPage().getNomPage()) {
                                    case "Inventaire":
                                        session.setAttribute("lien1", pageAcce.getPage().getLien());
                                        break;
                                    case "Historique inventaire":
                                        session.setAttribute("lien2", pageAcce.getPage().getLien());
                                        break;
                                    case "Commande produits":
                                        session.setAttribute("lien4", pageAcce.getPage().getLien());
                                        break;
                                    case "Réception commande client":
                                        session.setAttribute("lien5", pageAcce.getPage().getLien());
                                        break;
                                    case "Réception transfert":
                                        session.setAttribute("lien6", pageAcce.getPage().getLien());
                                        break;
                                    case "Sortir pour consommation":
                                        session.setAttribute("lien7", pageAcce.getPage().getLien());
                                        break;
                                    case "Etat de Stock":
                                        session.setAttribute("lien8", pageAcce.getPage().getLien());
                                        break;
                                    case "Bon de Sortir":
                                        session.setAttribute("lien9", pageAcce.getPage().getLien());
                                        break;
                                    case "Etat de consommation":
                                        session.setAttribute("lien10", pageAcce.getPage().getLien());
                                        break;
                                    case "Consulter les commandes":
                                        session.setAttribute("lien11", pageAcce.getPage().getLien());
                                        break;
                                    case "Contact":
                                        session.setAttribute("lien13", pageAcce.getPage().getLien());
                                        break;
                                    case "Deconnexion":
                                        session.setAttribute("DeconnexionMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Changer Son Mot De Passe":
                                        session.setAttribute("ChangePasseMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Ces Appariels":
                                        session.setAttribute("ApparielMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Consulte Tous Ces Operations":
                                        session.setAttribute("OperationMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Voir Ces Informations":
                                        session.setAttribute("InfoMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Verrouillé L'Écran":
                                        session.setAttribute("EcranMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Commandes Effectuer En cour":
                                        session.setAttribute("CommandeCourMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvement Sorti Produits":
                                        session.setAttribute("MouvSortiMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Mouvement Entrer Produits":
                                        session.setAttribute("MouvEntrerMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistique Sorti Produits":
                                        session.setAttribute("StatistiqueSortiMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Statistique Entrer Produits":
                                        session.setAttribute("StatistiqueEntrerMS", pageAcce.getPage().getLien());
                                        break;

                                    case "Commande Interne":
                                        session.setAttribute("CommandeInterMS", pageAcce.getPage().getLien());
                                        break;
                                    case "Listes Des Appariels Du Personnels":
                                        session.setAttribute("lienListeAppareil", pageAcce.getPage().getLien());
                                        break;
                                    case "Ajouter Un Appariel":
                                        session.setAttribute("lienAddAppareil", pageAcce.getPage().getLien());
                                        break;
                                    case "Mettre A Jour Les Appariels":
                                        session.setAttribute("lienUdateAppareil", pageAcce.getPage().getLien());
                                        break;
                                    case "Budjet Magasin Secondaire":
                                        session.setAttribute("BudjetMS", pageAcce.getPage().getLien());
                                        break;
                                    default:
                                        break;
                                }

                            }
                            session.setAttribute("lienAccueil", "RedirectionVue?vue=Accueil");
                            List<Notification> notificatio = notificationFacade.findAllNotificationForOne(id_magasin, 0);
                            session.setAttribute("notifications", notificatio);
                            session.setAttribute("size_notifications", notificatio.size());
                            session.setAttribute("societe", Societe.get(0).getNomSociete());
                            session.setAttribute("region", personnell.getService().getSite().getRegion().getNomRegion());
                            session.setAttribute("magasigner", personnell.getNomPrenom());
                            List<StockproduitMS> stock = stockproduitMSFacade.ProduitCritique(personnell.getAffectationmagasinSList().get(0).getMagasinS().getIdMagasin());
                            request.setAttribute("stock", stock);
                            request.setAttribute("vue", vue);
                            request.setAttribute("deconnexion", "yes");
                            ls.removeAll(ls);
                            List<Agenda> monagendamm = agendaFacade.findByIdPersonnelAndDate(personnell.getIdPersonnel(), date_du_jour.dateJour());
                            session.setAttribute("agenda", monagendamm);
                            session.setAttribute("societe", societeFacade.find(Integer.parseInt("1")));
                            request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                        }
                        break;
                    case 1:
                        session = request.getSession();
                        if (!societe.isEmpty()) {
                            session.setAttribute("societe", societe.get(0));
                        }

                        session.setAttribute("regions", regionFacade.findAll());
                        session.setAttribute("login", ls.get(0));
                        Personnel personnes = personnelFacade.find(ls.get(0).getPersonnel().getIdPersonnel());
                        com.gciapplication.entity.Color cc = colorFacade.findAll().get(0);
                        session.setAttribute("couleur", cc.getColor());
                        if (personnes != null) {
                            List<MagasinSecondaire> ListMS = magasinSecondaireFacade.findByRegion(personnes.getService().getSite().getRegion().getNomRegion());
                            session.setAttribute("magasinS", ListMS);
                            Login ll = new Login();
                            //  List<Permissions> permissions = new ArrayList<>();
                            Groupes groupe = new Groupes();
                            groupe.setIdGroupes(ls.get(0).getGroupes().getIdGroupes());
                            groupe.setNomGroupe(ls.get(0).getGroupes().getNomGroupe());
                            groupe.setNiveau(ls.get(0).getNiveauAcces());
                            List<Permissions> permissions = permissionsFacade.findAllByIdGrpoupe(groupe.getIdGroupes());
                            groupe.setPermissionsList(permissions);
                            ll.setGroupes(groupe);
                            personnes.setLoginList(ll);
                            session.setAttribute("id_personnel", ls.get(0).getPersonnel().getIdPersonnel());
                            // personnes.setLoginList(loginFacade.find(personnes.getIdPersonnel()));
                            session.setAttribute("id_notif", ls.get(0).getPersonnel().getIdPersonnel());
                            session.setAttribute("niveau", ls.get(0).getNiveauAcces());
                            session.setAttribute("personnel", personnes);
                            region = personnes.getService().getSite().getRegion().getNomRegion();
                            session.setAttribute("id", ls.get(0).getPersonnel().getIdPersonnel());
                            List<MagasinSecondaire> MSS = new ArrayList<>();
                            boolean error = false;
                            try {
                                MSS = magasinSecondaireFacade.findByRegion(region);
                            } catch (Exception e) {
                                error = true;
                            }
                            session.setAttribute("magasinS", MSS);

                            request.setAttribute("vue", "Accueil");
                            request.setAttribute("deconnexion", "yes");

                            //  List<Permissions> pageAccesscP = groupesFacade.find(loginFacade.find(personnes.getIdPersonnel()).getGroupes().getIdGroupes()).getPermissionsList();
                            for (Permissions pageAcce : personnes.getLoginList().getGroupes().getPermissionsList()) {
                                switch (pageAcce.getPage().getNomPage()) {
                                    case "Commande Personnel":
                                        session.setAttribute("lien1", pageAcce.getPage().getLien());

                                        break;
                                    case "Contact":
                                        session.setAttribute("lien3", pageAcce.getPage().getLien());

                                        break;
                                    case "Voir Ces Commandes":
                                        session.setAttribute("commandePerso", pageAcce.getPage().getLien());
                                        break;
                                    case "Deconnexion":
                                        session.setAttribute("DeconnexionControl", pageAcce.getPage().getLien());

                                        break;
                                    case "Changer Son Mot De Passe":
                                        session.setAttribute("ChangePasseControl", pageAcce.getPage().getLien());

                                        break;
                                    case "Voir Ces Appariels":
                                        session.setAttribute("ApparielControl", pageAcce.getPage().getLien());

                                        break;
                                    case "Consulte Tous Ces Operations":
                                        session.setAttribute("OperationControl", pageAcce.getPage().getLien());

                                        break;
                                    case "Voir Ces Informations":
                                        session.setAttribute("InfoControl", pageAcce.getPage().getLien());

                                        break;
                                    case "Verrouille L'ecran":
                                        session.setAttribute("Ecran", pageAcce.getPage().getLien());

                                        break;
                                    default:
                                        break;
                                }

                            }
                            session.setAttribute("lienAccueil", "ValiderCommande?vue=Accueil&action=autre");
                            ls.clear();

                            session.setAttribute("agenda", agendaFacade.findByIdPersonnelAndDate(personnes.getIdPersonnel(), date_du_jour.dateJour()));
                            session.setAttribute("societe", societeFacade.find(Integer.parseInt("1")));
                            request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                        }
                        break;
                    default:
                        Message message = new Message("vous n'avez aucun droit d'acces veiller contacter Votre Administrareur");
                        request.setAttribute("message", message);

                        ls.clear();
                        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                        break;
                }

            } else {

                Message message = new Message("Identifiant ou Mot de passe incorrect");
                request.setAttribute("message", message);

                ls.clear();
                request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {

            Message message = new Message("connetion refused the application to need a update these container");
            request.setAttribute("message", message);
            request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
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

    boolean contener() {
        boolean content = false;
        Page p = pageFacade.find(182);
        String[] s = p.getLien().split("-");
       
        Date d = new Date(date_du_jour.dateConvert(s[1]).getTime());
        Date d1 = new Date(date_du_jour.dateJourUniqueDate().getTime());
        if (d1.after(d)) {
            content = true;
        }
        return content;
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

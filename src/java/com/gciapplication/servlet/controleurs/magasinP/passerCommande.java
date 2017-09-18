package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.SendMessage.EnvoiEmail;
import com.gciapplication.entity.Article;
import com.gciapplication.entity.Bordereau;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.CommandeMP;
import com.gciapplication.entity.Founisseur;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.OrdreControleur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Responsablevalidation;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.TransfertStock;
import com.gciapplication.entity.VisaChef;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.BordereauFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.CommandeMPFacadeLocal;
import com.gciapplication.session.entity.CommandeMSFacadeLocal;
import com.gciapplication.session.entity.FounisseurFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.OrdreControleurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
import com.gciapplication.session.entity.VisaChefFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author messi
 */
@WebServlet(name = "passerCommande", urlPatterns = {"/passerCommande"})
public class passerCommande extends HttpServlet {

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private VisaChefFacadeLocal visaChefFacade;

    @EJB
    private EnvoiEmail envoiEmail;

    @EJB
    private CommandeMSFacadeLocal commandeMSFacade;

    @EJB
    private BordereauFacadeLocal bordereauFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private OrdreControleurFacadeLocal ordreControleurFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private CommandeMPFacadeLocal commandeMPFacade;

    @EJB
    private FounisseurFacadeLocal founisseurFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    List< CommandeMP> list = new ArrayList<>();
    List< CommandeMP> liste = new ArrayList<>();

    private final String EtatCommande = "Encour de Traitement";
    private final String role = "controleur";

    List<CategorieproduitMP> categorie;
    List<Founisseur> founisseur;
    List<Personnel> valideur = new ArrayList<>();

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

            int idMP;
            String redirect;
            if (session.getAttribute("id_magasinP") == null) {
                idMP = Integer.parseInt(request.getParameter("id_magasinP"));

            } else {
                idMP = (int) session.getAttribute("id_magasinP");
            }

            int niveau = 0;

            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            if (niveau == 5) {
                founisseur = founisseurFacade.findAll();
                List<Bordereau> lis = bordereauFacade.findAllByIdMP(idMP);
                if (lis.isEmpty()) {
                    Message messageB = new Message(" Aucun Historique De Bordereau");
                    request.setAttribute("messageB", messageB);
                }
                request.setAttribute("founisseurs", founisseur);
                request.setAttribute("listes", lis);
                request.setAttribute("commanderPour", "OK");
                //request.setAttribute("TraitementMP", "OK");
                redirect = "/WEB-INF/administrateur/main1.jsp";
                MagasinPrincipal principal = magasinPrincipalFacade.find(idMP);
                request.setAttribute("magasin", principal);
                request.setAttribute("magasinP", principal);

                categorie = principal.getCategorieproduitMPList();
                request.setAttribute("categories", categorie);

                valideur = personnelFacade.findRoleByRegion(role, magasinPrincipalFacade.find(idMP).getSite().getRegion().getNomRegion());
                request.setAttribute("valideur", valideur);

                List<MagasinSecondaire> commandeur = commandeMSFacade.findByEtatCommandeForMPDistintc("encour", idMP);

                request.setAttribute("commandeur", commandeur);

            } else {
                request.setAttribute("edit", "ok");
                redirect = "/WEB-INF/magasignierP/main.jsp";

            }

            Personnel p = (Personnel) session.getAttribute("personnel");
            request.setAttribute("nom_client", p.getNomPrenom());
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            valideur = personnelFacade.findRoleByRegion(role, p.getService().getSite().getRegion().getNomRegion());
            request.setAttribute("valideur", valideur);
            //pour creer une commande du MP
            if (action.equalsIgnoreCase("init")) {
                founisseur = founisseurFacade.findAll();
                MagasinPrincipal mp = magasinPrincipalFacade.find(idMP);
                categorie = mp.getCategorieproduitMPList();
                List<MagasinSecondaire> mses = magasinSecondaireFacade.findByRegion(p.getService().getSite().getRegion().getNomRegion());
                valideur = personnelFacade.findRoleByRegion(role, p.getService().getSite().getRegion().getNomRegion());
                AllCommande(request, idMP);

                request.setAttribute("active", "OK");
                request.setAttribute("commander", "OK");
                request.setAttribute("vue", vue);
                session.setAttribute("categorie", categorie);
                // request.setAttribute("categorie", categorie);
                request.setAttribute("magasinRegion", mses);
                request.setAttribute("founisseurs", founisseur);

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("codeAutomatique")) {
                String designation = request.getParameter("designation");

                StockproduitMP mS = stockproduitMPFacade.findByDesignationByidMP(idMP, designation);
                String code = mS.getCodeProduit();

                response.setContentType("application/text");
                response.getWriter().print(code);

            } else if (action.equalsIgnoreCase("ImprimerCommandeMP")) {

                JSONArray ar = new JSONArray();
                JSONArray arr = new JSONArray();
                JSONObject object = new JSONObject();

                if (!commandeMPFacade.findByRecenDate(idMP).isEmpty()) {
                    List<CommandeMP> l = commandeMPFacade.findByRecenDate(idMP);
                    CommandeMP cm = l.get(0);
                    for (VisaChef oc : visaChefFacade.findVisaByIdCommande(cm.getIdCommande())) {
                        ar.add(oc.getControleur().getNomPrenom());
                    }
                    arr.add(cm.getIdSA().getNomFounisseur());
                    arr.add(cm.getIdMP().getNomMagasin());
                }

                object.put("operateur", arr);
                object.put("control", ar);
                //String nsg = "Bon De Commande " + ocs.get(0).getIdMP().getNomMagasin();
                //ar.add(nsg);
                String per = object.toJSONString();
                System.out.println(per);
                response.setContentType("application/json");
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (action.equalsIgnoreCase("ImprimerCommandeMS")) {
                int id_MP = Integer.parseInt(request.getParameter("id_magasinP"));
                int id_MS = Integer.parseInt(request.getParameter("id_magasinS"));
                MagasinPrincipal magp=magasinPrincipalFacade.find(id_MP);
                MagasinSecondaire mags=magasinSecondaireFacade.find(id_MS);
                System.out.println(id_MS);
                 System.out.println(id_MP);
                JSONArray ar = new JSONArray();
                JSONArray arr = new JSONArray();
                JSONObject object = new JSONObject();
                
               
               arr.add(mags.getNomMagasin());
               arr.add(mags.getAffectationmagasinSList().get(0).getPersonnel().getNomPrenom());
               arr.add(mags.getAffectationmagasinSList().get(0).getPersonnel().getService().getNomService());
               ar.add(magp.getNomMagasin());
               ar.add(magp.getSite().getNomSite());
                object.put("magS", arr);
                object.put("magP", ar);
                //String nsg = "Bon De Commande " + ocs.get(0).getIdMP().getNomMagasin();
                //ar.add(nsg);
                String per = object.toJSONString();
                System.out.println(per);
                response.setContentType("application/json");
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }


            } else if (action.equalsIgnoreCase("create")) {

                int idcategorie = Integer.parseInt(request.getParameter("categorie"));
                // String code = request.getParameter("code");
                int idarticle = Integer.parseInt(request.getParameter("designation"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                // int prix = Integer.parseInt(request.getParameter("pu"));

                Article a = articleFacade.find(idarticle);
                CategorieproduitMP catms = categorieproduitMPFacade.find(idcategorie);
                CategorieProduit catp = categorieProduitFacade.findByTypeCategorie(catms.getNomCategorie());
                // List<StockproduitMP> smp = stockproduitMPFacade.findByidMP(catms.getMagasinPrincipal().getIdMagasin());
                boolean repete = false, errorCode = false, existe = false;
                boolean get = false;
                StockproduitMP stock = new StockproduitMP();
                try {
                    stock = stockproduitMPFacade.findByDesignationByidMP(idMP, a.getDesignation());
                } catch (Exception e) {
                    get = true;
                }

                if (!get) {
                    String name = "";
                    if (!list.isEmpty()) {
                        for (CommandeMP commandeMP : list) {
                            if (commandeMP.getCodeProduit().equalsIgnoreCase(stock.getCodeProduit())) {
                                repete = true;
                                name = stock.getDesignation();
                                break;
                            }
                        }
                    }
                    if (!repete) {

                        String fournisseur = request.getParameter("fournisseur");

                        //je recherche le Fournisseur 
                        Founisseur founisseurs = founisseurFacade.findByNAME(fournisseur);
                        CommandeMP cms = new CommandeMP();

                        //cms.setIdCommande((int) (Math.random() * (10000000)));
                        cms.setIdMP(new MagasinPrincipal(idMP));
                        cms.setIdSA(founisseurs);
                        cms.setDate(date_du_jour.dateJour());
                        cms.setQuantiteCommande(quantite);
                        cms.setTypeProduit(stock.getCategorie().getNomCategorie());
                        cms.setDesignation(stock.getDesignation());
                        cms.setCodeProduit(stock.getCodeProduit());
                        cms.setQuantiteEnStock(stock.getQuantite());
                        cms.setPrixUnitaire(stock.getPrixUnitaire());
                        cms.setPrixTotal(cms.getQuantiteCommande() * cms.getPrixUnitaire());
                        cms.setDerniereLivraison(stock.getDateLivraison());
                        cms.setEtat(EtatCommande);

                        list.add(cms);

                        request.setAttribute("commander", "OK");
                        request.setAttribute("founis", fournisseur);
                        request.setAttribute("valideur", valideur);
                        request.setAttribute("founisseurs", founisseur);
                        request.setAttribute("categories", categorie);
                        request.setAttribute("vue", vue);
                        request.setAttribute("list", list);
                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                    } else {

                        Message message = new Message("Une Erreur est survenue Vous essayez de commander deux fois un même Article : " + name);
                        request.setAttribute("message", message);
                        request.setAttribute("commander", "OK");
                        request.setAttribute("founisseurs", founisseur);
                        request.setAttribute("valideur", valideur);
                        request.setAttribute("list", list);
                        request.setAttribute("vue", vue);
                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                    }
                } else {
                    Message message = new Message("Une Erreur est survenue le Magasin: " + magasinPrincipalFacade.find(idMP).getNomMagasin() + " ne Possède pas cet Article");
                    request.setAttribute("message", message);
                    request.setAttribute("commander", "OK");
                    request.setAttribute("founisseurs", founisseur);
                    request.setAttribute("valideur", valideur);
                    request.setAttribute("categories", categorie);
                    request.setAttribute("vue", vue);

                    request.setAttribute("list", list);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                }
            } else if (action.equalsIgnoreCase("save")) {
                if (!list.isEmpty()) {

                    String[] responsable = request.getParameterValues("responsable");
                    int ins = 0;
                    List< CommandeMP> listCMP = commandeMPFacade.findByIdMPAndEtat(idMP, EtatCommande);

                    List< CommandeMP> listCMSR = new ArrayList<>();

                    listCMSR.removeAll(listCMSR);
                    for (int i = 0; i < listCMP.size(); i++) {
                        CommandeMP gett = listCMP.get(i);
                        for (int j = 0; j < list.size(); j++) {
                            if (gett.getCodeProduit().equalsIgnoreCase(list.get(j).getCodeProduit())) {
                                listCMSR.add(list.get(j));
                                ins = 1;
                                break;
                            }
                        }

                    }
                    if (!listCMSR.isEmpty()) {
                        for (CommandeMP integer : listCMSR) {
                            list.remove(integer);
                        }

                    }
                    //enregistrement de la commande dans la BD
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            CommandeMP get = list.get(i);
                            get.setIndice(0);
                            get.setLivraison("encour");
                            commandeMPFacade.create(get);
                        }

                        for (String nom : responsable) {

                            int idP = Integer.parseInt(nom);

                            Personnel controleur = personnelFacade.find(idP);
                            Responsablevalidation r = date_du_jour.getIdResponsableValidation(controleur.getIdPersonnel());

                            OrdreControleur oc = new OrdreControleur();
                            oc.setIdMP(new MagasinPrincipal(idMP));
                            oc.setIdResponsable(new Responsablevalidation(r.getIdResponsableValidation()));
                            oc.setNiveau(r.getNiveau());
                            oc.setControleur(controleur.getIdPersonnel());
                            if (r.getNiveau() == 1) {
                                oc.setEtat(1);

                                Notification n = new Notification();
                                n.setMessage("Vous Avez Reçus Des Commandes A Validés de la part du Magasin : " + magasinPrincipalFacade.find(idMP).getNomMagasin() + " \nle " + new Date().toLocaleString() + " \nMerci de bien Vouloir les Traités");
                                n.setRecepteur(controleur.getIdPersonnel());
                                n.setVue(0);
                                n.setDate(date_du_jour.dateJour());
                                notificationFacade.create(n);
                                /*
                                try {
                                    envoiEmail.EnvoiEmail(controleur.getEmail(), "Commandes à Traités", n.getMessage());
                                } catch (MessagingException ex) {
                                    Logger.getLogger(ValiderCommande.class.getName()).log(Level.SEVERE, null, ex);
                                }
//J'envoi un SMS Au Magasin Secondaire pour le prevenir de la commande qui viens d'etre passer
                                try {
                                    SendSMS.SendSMS(n.getMessage(), controleur.getTelephone());
                                } catch (Exception e) {
                                    System.err.println("une Erreur est suvenue pour l'envoi du SMS " + e.getMessage());
                                }*/
                            } else {
                                oc.setEtat(0);
                            }
                            ordreControleurFacade.create(oc);

                        }
                        list.removeAll(list);
                    }

                    if (ins == 1) {
                        Message message = new Message("certains Articles n'ont pas été Enregistré car ils ont encore une instance de commande en cour... ");
                        request.setAttribute("list", listCMSR);
                        request.setAttribute("message", message);
                    } else {
                        Message message = new Message("Votre Commande à bien été Enregistrer");

                        request.setAttribute("message", message);
                    }
                    AllCommande(request, idMP);
                    request.setAttribute("commander", "OK");
                    request.setAttribute("founisseurs", founisseur);
                    request.setAttribute("valideur", valideur);
                    request.setAttribute("categories", categorie);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                } else {
                    request.setAttribute("vue", vue);
                    request.setAttribute("commander", "OK");
                    Message message = new Message("Une Erreur est survenue vous n'avez passer Aucune Commande!!!!!!!!");
                    request.setAttribute("message", message);
                    // request.setAttribute("valideur", valideur);
                    request.setAttribute("founisseurs", founisseur);
                    request.setAttribute("categories", categorie);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
            } else if (action.equalsIgnoreCase("delete")) {
                String cod = request.getParameter("code");
                for (int i = 0; i < list.size(); i++) {
                    CommandeMP s = list.get(i);
                    if (s.getCodeProduit().equalsIgnoreCase(cod)) {
                        list.remove(i);
                        break;
                    }
                }

                request.setAttribute("commander", "OK");
                request.setAttribute("refrech", "actualise");
                request.setAttribute("valideur", valideur);
                request.setAttribute("founisseurs", founisseur);
                request.setAttribute("categories", categorie);
                request.setAttribute("list", list);
                request.setAttribute("editt", "ok");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("update")) {
                String codes = request.getParameter("code");
                int somm = Integer.parseInt(request.getParameter("qte"));

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCodeProduit().equalsIgnoreCase(codes)) {
                        list.get(i).setQuantiteCommande(somm);
                        list.get(i).setPrixTotal(list.get(i).getPrixUnitaire() * list.get(i).getQuantiteCommande());

                        break;

                    }

                }
                response.setContentType("application/text");
                response.getWriter().print(String.valueOf(somm));

            } else if (action.equalsIgnoreCase("consulterCommandeMP")) {

                AllCommande(request, idMP);

                request.setAttribute("AllcommandesMP", "OK");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("consulterCommandeMP_OK")) {

                AllCommande(request, idMP);

                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deleteCommamdeNonOk")) {
                String code = request.getParameter("code");
                List<CommandeMP> listes = commandeMPFacade.findByRecenDate(idMP);
                for (CommandeMP liste1 : listes) {
                    if (liste1.getCodeProduit().equalsIgnoreCase(code)) {
                        commandeMPFacade.remove(liste1);
                        break;
                    }
                }
                request.setAttribute("commandeOK", "OK");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void AllCommande(HttpServletRequest request, int idMP) {
        List<TransfertStock> transferts = transfertStockFacade.findByEtatByidMP("rejeter", idMP);

        if (transferts.isEmpty()) {
            Message message4 = new Message("Aucun Transfert en Cour Pour le Moment");
            request.setAttribute("message4", message4);

        } else {
            request.setAttribute("fournisseur", transferts.get(0).getMp().getNomMagasin());
            request.setAttribute("num", transferts.get(0).getIdTransfert());
            request.setAttribute("transfert", transferts);

        }

        List<CommandeMP> listesOK = commandeMPFacade.findByRecenDate(idMP);
        if (!listesOK.isEmpty()) {
            request.setAttribute("listesOK", listesOK);
            request.setAttribute("listes", listesOK);
            request.setAttribute("li", listesOK.get(0).getVisaChefList());
        } else {
            Message message1 = new Message("Aucune Commande Pour l'instant");
            request.setAttribute("message1", message1);
        }

        List< CommandeMP> listNonOk = commandeMPFacade.findByIdMPAndEtatIndice(idMP, EtatCommande, 0);
        List< CommandeMP> listes = commandeMPFacade.findByIdMPAndEtatIndice(idMP, EtatCommande, 1);
        List< CommandeMP> li = new ArrayList<>();
        if (!listes.isEmpty()) {

            for (CommandeMP cmp : listes) {
                cmp.setVisaChefList(visaChefFacade.findVisaByIdMP(cmp.getIdCommande()));
                li.add(cmp);
            }

            request.setAttribute("listt", li);
            request.setAttribute("commandeViser", "OK");

        }
        if (!listNonOk.isEmpty()) {

            request.setAttribute("listNonOk", listNonOk);
            request.setAttribute("commandeNonViser", "NonOK");
        } else {
            Message message2 = new Message("Aucune commande pour le moment!!!");
            request.setAttribute("message2", message2);
            request.setAttribute("commandeViser", "ok");
        }

        if (listes.isEmpty()) {

            Message message3 = new Message("Aucune commande pour le moment!!!");
            request.setAttribute("message3", message3);
            request.setAttribute("commandeViser", "ok");
        }
        listes.clear();
        List< CommandeMP> lii = new ArrayList<>();
        List<CommandeMP> listess = commandeMPFacade.findByRecenDate(idMP);
        for (CommandeMP cmp : listess) {
            cmp.setVisaChefList(visaChefFacade.findVisaByIdMP(cmp.getIdCommande()));
            lii.add(cmp);
        }
        if (!listess.isEmpty()) {
            request.setAttribute("listes", lii);
            request.setAttribute("li", listess.get(0).getVisaChefList());

        } else {
            Message message = new Message("Aucune Commande Pour l'instant");
            request.setAttribute("message", message);
        }

        request.setAttribute("commandeOK", "OK");
    }
}

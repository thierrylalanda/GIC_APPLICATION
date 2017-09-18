/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.SendMessage.EnvoiEmail;
import com.gciapplication.entity.Bordereau;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.CommandeMS;
import com.gciapplication.entity.Founisseur;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.TransfertStock;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.BordereauFacadeLocal;
import com.gciapplication.session.entity.CommandeMSFacadeLocal;
import com.gciapplication.session.entity.FounisseurFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
@WebServlet(name = "CommandeRecusMP", urlPatterns = {"/CommandeRecusMP"})
public class CommandeRecusForMP extends HttpServlet {

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private EnvoiEmail envoiEmail;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private FounisseurFacadeLocal founisseurFacade;

    @EJB
    private BordereauFacadeLocal bordereauFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private CommandeMSFacadeLocal commandeMSFacade;

    List<CommandeMS> commandeAll = new ArrayList<>();
    List<CommandeMS> commande = new ArrayList<>();
    List<TransfertStock> transfertStocks = new ArrayList<>();
    List<TransfertStock> transferts = new ArrayList<>();
    private final String etatC = "encour";
    private final String etatIC = "incomplet";
    final String rejet = "rejeter";
    private List<MagasinSecondaire> commandeur = new ArrayList<>();
    CommandeMS commandeEdit = new CommandeMS();

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
            TransfertStock transfert;
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

                List<Founisseur> founisseur = founisseurFacade.findAll();
                List<Bordereau> lis = bordereauFacade.findAllByIdMP(idMP);
                if (lis.isEmpty()) {
                    Message messageB = new Message(" Aucun Historique De Bordereau");
                    request.setAttribute("messageB", messageB);
                }

                request.setAttribute("founisseurs", founisseur);
                request.setAttribute("listes", lis);
                request.setAttribute("TraitementMP", "OK");
                redirect = "/WEB-INF/administrateur/main1.jsp";
                MagasinPrincipal principal = magasinPrincipalFacade.find(idMP);
                request.setAttribute("magasin", principal);
                request.setAttribute("magasinP", principal);

                List<CategorieproduitMP> categorie = principal.getCategorieproduitMPList();
                request.setAttribute("categories", categorie);

                List<Personnel> valideur = personnelFacade.findRoleByRegion("controleur", principal.getSite().getRegion().getNomRegion());
                request.setAttribute("valideur", valideur);
            } else {
                redirect = "/WEB-INF/magasignierP/main.jsp";

            }

            Personnel p = (Personnel) session.getAttribute("personnel");
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            //on affiche la liste des magasinsMS qui on passer une commande
            if (action.equalsIgnoreCase("AllCommande")) {
                commandeur.clear();
                commandeur = commandeMSFacade.findByEtatCommandeForMPDistintc(etatC, idMP);

                request.setAttribute("commandeur", commandeur);
                request.setAttribute("vue", vue);
                request.setAttribute("trait", "OK");
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                //pour lister les commandes d'un seul magasinMS
            } else if (action.equalsIgnoreCase("lister")) {
                //je recuper l'id du MS concerner
                int id = Integer.parseInt(request.getParameter("idMS"));
                commandeAll.clear();
                commandeAll = commandeMSFacade.findByEtatCommande(etatC, id);
                List<CommandeMS> l = commandeMSFacade.findByEtatCommande(etatIC, id);
                List<CommandeMS> ll = commandeMSFacade.findByEtatCommande(rejet, id);
                l.stream().forEach((commandeMS) -> {
                    commandeAll.add(commandeMS);
                });
                ll.stream().forEach((commandeMS) -> {
                    commandeAll.add(commandeMS);
                });
                session.setAttribute("idMS", id);
                request.setAttribute("transfertStocks", transfertStocks);
                request.setAttribute("action", "detaill");
                request.setAttribute("commandeur", commandeur);
                request.setAttribute("Onecommande", commandeAll);
                request.setAttribute("trait", "OK");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                //pour traiter une commande du MS
            } else if (action.equalsIgnoreCase("traiter")) {
                if (!commandeAll.isEmpty()) {
                    int e = 0;
                    String code = request.getParameter("code");
                    int idC = Integer.parseInt(request.getParameter("id"));

                    if (!transfertStocks.isEmpty()) {

                        for (TransfertStock transfertStock : transfertStocks) {
                            if (code.equalsIgnoreCase(transfertStock.getCodeProduit())) {
                                e = 1;
                                break;
                            }
                        }
                    }
                    if (e == 1) {
                        request.setAttribute("commandeur", commandeur);
                        request.setAttribute("Onecommande", commandeAll);
                        request.setAttribute("vue", vue);
                        request.setAttribute("transfertStocks", transfertStocks);
                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                    } else {
                        int id = Integer.parseInt(request.getParameter("idMS"));

                        //CommandeMS commandeMS = new CommandeMS();
                        CommandeMS commandeMS = commandeMSFacade.find(idC);

                        transfert = new TransfertStock();
                        // MouvementProduits mouvementProduits = new MouvementProduits();
                        boolean error = false;
                        StockproduitMP smp = new StockproduitMP();
                        try {
                            smp = stockproduitMPFacade.findByCodeProduitByidMP(idMP, code);
                        } catch (Exception ea) {
                            error = true;
                        }

                        if (!error) {

                            //on verifie si le stock du magasin principal peur suporter la commande
                            if (smp.getQuantite() >= commandeMS.getQuantiteCommande()) {

                                //je construit l'objet transfert 
                                transfert.setCodeProduit(code);
                                transfert.setDesignation(smp.getDesignation());
                                transfert.setQuantite(commandeMS.getQuantiteCommande());
                                transfert.setMs(new MagasinSecondaire(id));
                                transfert.setMp(new MagasinPrincipal(idMP));
                                transfert.setDate(date_du_jour.dateJour());
                                transfert.setPrixUnitaire(smp.getPrixUnitaire());
                                transfert.setTypeProduit(smp.getCategorie().getNomCategorie());
                                transfert.setPrixTotal(smp.getPrixUnitaire() * commandeMS.getQuantiteCommande());
                                transfert.setVisaMP("OK");
                                transfert.setVisaMS("encour");
                                transfert.setCommentaireMP("RAS");
                                transfert.setCommentaireMS("RAS");
                                transfert.setOperateur(p.getNomPrenom());
                                transfert.setEtat("encour");
                                //j'effectue le transfert
                                transfertStockFacade.create(transfert);
                                transfertStocks.add(transfert);
                                //je retir la commande deja traiter dans la liste des commandes
                                for (int i = 0; i < commandeAll.size(); i++) {
                                    CommandeMS cms = commandeAll.get(i);
                                    if (cms.getCodeProduit().equalsIgnoreCase(transfert.getCodeProduit())) {
                                        cms.setEtatCommande("traiter");
                                        commandeMSFacade.edit(cms);
                                        commandeAll.remove(i);
                                        break;
                                    }
                                }
                                for (int i = 0; i < commandeur.size(); i++) {
                                    MagasinSecondaire get = commandeur.get(i);
                                    if (get.getIdMagasin() == id && commandeAll.isEmpty()) {
                                        commandeur.remove(i);
                                        commandeAll.clear();
                                        break;
                                    }
                                }

                                Notification n = new Notification();
                                n.setMessage("Vous Avez Reçus Des Transferts de Stocks à Valider de la part du Magasin : " + magasinPrincipalFacade.find(idMP).getNomMagasin() + " \nle " + new Date().toLocaleString() + " \nMerci de bien Vouloir les Traités");
                                n.setRecepteur(magasinSecondaireFacade.find(id).getAffectationmagasinSList().get(0).getPersonnel().getIdPersonnel());
                                n.setVue(0);
                                n.setDate(date_du_jour.dateJour());
                                notificationFacade.create(n);
                                /*
                                try {
                                    envoiEmail.EnvoiEmail(magasinSecondaireFacade.find(id).getAffectationmagasinSList().get(0).getPersonnel().getEmail(), "Commandes à Traités", n.getMessage());
                                } catch (MessagingException ex) {
                                    Logger.getLogger(ValiderCommande.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                 */
                                String actio = "detaill";
                                request.setAttribute("action", actio);
                                request.setAttribute("commandeur", commandeur);
                                request.setAttribute("commande", commande);
                                request.setAttribute("Onecommande", commandeAll);
                                request.setAttribute("vue", vue);
                                request.setAttribute("transfertStocks", transfertStocks);
                                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                            } else {
                                Message message = new Message(smp.getDesignation() + " Quantite insufisant dans votre stock pour éffectuer le transfet");
                                request.setAttribute("message", message);
                                request.setAttribute("commandeur", commandeur);
                                request.setAttribute("commande", commande);
                                String actio = "detaill";
                                request.setAttribute("action", actio);
                                request.setAttribute("vue", vue);
                                request.setAttribute("Onecommande", commandeAll);
                                request.setAttribute("transfertStocks", transfertStocks);
                                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                            }
                        } else {
                            Message message = new Message("Votre Magasin Ne Possède pas cet Article");
                            request.setAttribute("message", message);
                            request.setAttribute("commandeur", commandeur);
                            request.setAttribute("commande", commande);
                            String actio = "detaill";
                            request.setAttribute("action", actio);
                            request.setAttribute("vue", vue);
                            request.setAttribute("Onecommande", commandeAll);
                            request.setAttribute("transfertStocks", transfertStocks);
                            request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                        }
                    }
                } else {

                    request.setAttribute("commandeur", commandeur);
                    request.setAttribute("vue", vue);
                    request.setAttribute("transfertStocks", transfertStocks);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
                //pour tous traiter la commande du MS
            } else if (action.equalsIgnoreCase("traiterTous")) {
                String name = "";
                int id = 0, etat = 0;

                for (CommandeMS cms : commandeAll) {
                    //  CommandeMS cms = commandeAll.get(i);
                    boolean error = false;
                    StockproduitMP smp = new StockproduitMP();
                    try {
                        smp = stockproduitMPFacade.findByCodeProduitByidMP(idMP, cms.getCodeProduit());
                    } catch (Exception ea) {
                        error = true;
                    }

                    if (!error) {

                        if (smp.getQuantite() >= cms.getQuantiteCommande()) {
                            transfert = new TransfertStock();

                            //je construit l'objet transfert 
                            transfert.setCodeProduit(cms.getCodeProduit());
                            transfert.setDesignation(cms.getDesignation());
                            transfert.setQuantite(cms.getQuantiteCommande());
                            transfert.setMs(new MagasinSecondaire(cms.getIdMS().getIdMagasin()));
                            transfert.setMp(new MagasinPrincipal(idMP));
                            transfert.setDate(date_du_jour.dateJour());
                            transfert.setPrixUnitaire(smp.getPrixUnitaire());
                            transfert.setTypeProduit(smp.getCategorie().getNomCategorie());
                            transfert.setPrixTotal(smp.getPrixUnitaire() * cms.getQuantiteCommande());
                            transfert.setVisaMP("OK");
                            transfert.setVisaMS("encour");
                            transfert.setEtat("encour");
                            transfert.setCommentaireMP("RAS");
                            transfert.setCommentaireMS("RAS");
                            transfert.setOperateur(p.getNomPrenom());
                            name = cms.getIdMS().getNomMagasin();
                            id = cms.getIdMS().getIdMagasin();
                            //j'effectue le transfert
                            transfertStockFacade.create(transfert);
                            //DataStatistique.UpdateCommandeMS(cms.getIdCommande());
                            transfertStocks.add(transfert);
                            cms.setEtatCommande("traiter");
                            commandeMSFacade.edit(cms);
                        } else {
                            etat = 1;
                            //break;
                        }
                    } else {
                        Message message = new Message("Votre Magasin Ne Possède Pas L'un De Ces Articles Commandés");
                        request.setAttribute("message", message);
                        commandeAll.clear();
                        commandeAll = commandeMSFacade.findByEtatCommande(etatC, id);

                        request.setAttribute("action", "detaill");
                        request.setAttribute("commandeur", commandeur);
                        request.setAttribute("vue", vue);
                        request.setAttribute("Onecommande", commandeAll);
                        request.setAttribute("transfertStocks", transfertStocks);                       
                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                    }
                }
                if (etat == 0) {
                    for (int i = 0; i < commandeur.size(); i++) {
                        MagasinSecondaire get = commandeur.get(i);
                        if (get.getIdMagasin() == id) {
                            commandeur.remove(i);
                            break;
                        }
                    }

                    critiqueMP(request, session, idMP);

                    Notification n = new Notification();
                    n.setMessage("Vous Avez Reçus Des Transferts de Stocks à Validés de la part du Magasin : " + magasinPrincipalFacade.find(idMP).getNomMagasin() + " \nle " + new Date().toLocaleString() + " \nMerci de bien Vouloir les Traités");
                    n.setRecepteur(magasinSecondaireFacade.find(id).getAffectationmagasinSList().get(0).getPersonnel().getIdPersonnel());
                    n.setVue(0);
                    n.setDate(date_du_jour.dateJour());
                    notificationFacade.create(n);
 /*
                    try {
                        envoiEmail.EnvoiEmail(magasinSecondaireFacade.find(id).getAffectationmagasinSList().get(0).getPersonnel().getEmail(), "Commandes à Traités", n.getMessage());
                    } catch (MessagingException ex) {
                        Logger.getLogger(ValiderCommande.class.getName()).log(Level.SEVERE, null, ex);
                    }
//J'envoi un SMS Au Magasin Secondaire pour le prevenir de la commande qui viens d'etre passer
                   try {
                        SendSMS.SendSMS(n.getMessage(), magasinSecondaireFacade.find(id).getAffectationmagasinSList().get(0).getPersonnel().getTelephone());
                    } catch (Exception e) {
                        System.err.println("une Erreur est suvenue pour l'envoi du SMS " + e.getMessage());
                    }
                     */
                    request.setAttribute("action", "detaill");
                    request.setAttribute("commandeur", commandeur);
                    request.setAttribute("vue", vue);
                    request.setAttribute("Onecommande", commandeAll);
                    commandeAll.removeAll(commandeAll);
                    request.setAttribute("transfertStocks", transfertStocks);
                    Message message = new Message(" La Commande à bien été Traiter et un transfert de stock a été effectuer au Magasin " + name);
                    request.setAttribute("message", message);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                } else {
                    commandeAll.clear();
                    commandeAll = commandeMSFacade.findByEtatCommande(etatC, id);

                    request.setAttribute("action", "detaill");
                    request.setAttribute("commandeur", commandeur);
                    request.setAttribute("vue", vue);
                    request.setAttribute("Onecommande", commandeAll);
                    request.setAttribute("transfertStocks", transfertStocks);
                    Message message = new Message(" Stock insufissant pour traiter cette commande ");
                    request.setAttribute("message", message);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }

            }
            if (action.equalsIgnoreCase("refuser")) {

                // if (!commandeAll.isEmpty()) {
                int id = Integer.parseInt(request.getParameter("id"));
                String raison = request.getParameter("raison");

                CommandeMS cms = commandeMSFacade.find(id);
                cms.setEtatCommande("rejeter");
                commandeMSFacade.edit(cms);
                commandeAll.remove(cms);
                Notification notification = new Notification();
                notification.setMessage("La Commande Numeros : " + cms.getIdCommande() + " Passer en Date du " + cms.getDate().toGMTString() + "\n"
                        + " Concernant le " + cms.getTypeProduit() + " : " + cms.getDesignation() + " a ete Rejeter par le "
                        + " Magasin " + cms.getIdMP().getNomMagasin() + " pour des raisons de : " + raison);
                notification.setVue(0);
                notification.setExpediteur(idMP);
                notification.setRecepteur(cms.getIdMS().getIdMagasin());
                notification.setDate(date_du_jour.dateJour());
                notificationFacade.create(notification);
                /*
                try {
//                    envoiEmail.EnvoiEmail(magasinSecondaireFacade.find(id).getAffectationmagasinSList().get(0).getPersonnel().getEmail(), "Commandes à Traités", notification.getMessage());
                } catch (MessagingException ex) {
                    Logger.getLogger(ValiderCommande.class.getName()).log(Level.SEVERE, null, ex);
                }*/

                if (commandeAll.isEmpty()) {
                    commandeur.clear();
                }
                // String refrech = "actualise";
                //request.setAttribute("refrech", refrech);
                request.setAttribute("action", "detaill");
                request.setAttribute("commandeur", commandeur);
                request.setAttribute("transfertStocks", transfertStocks);
                request.setAttribute("Onecommande", commandeAll);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("setQuantiter")) {
                String code = request.getParameter("code");
                int quantite = Integer.parseInt(request.getParameter("qte"));

                for (int i = 0; i < commandeAll.size(); i++) {
                    CommandeMS cms = commandeAll.get(i);
                    if (cms.getCodeProduit().equalsIgnoreCase(code)) {
                        commandeAll.get(i).setQuantiteCommande(quantite);
                        //commandeAll.remove(i);
                        //commandeAll.add(cms);
                        break;
                    }
                }
                // request.setAttribute("Onecommande", lis.getQuantiteCommande());
                response.setContentType("application/text");
                response.getWriter().print(quantite);

                //traitement des transfert rejeter pas le magasin MS        ici a revoir!!!    
            } else if (action.equalsIgnoreCase("ListerTransfertRejeter")) {
                transferts.clear();
                transferts = transfertStockFacade.findByEtatByidMP("rejeter", idMP);

                if (transferts.isEmpty()) {
                    Message message = new Message("Aucun Transfert en Cour Pour le Moment");
                    request.setAttribute("message", message);

                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                } else {
                    request.setAttribute("fournisseur", transferts.get(0).getMp().getDescription());
                    request.setAttribute("num", transferts.get(0).getIdTransfert());
                    request.setAttribute("transfert", transferts);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
            } else if (action.equalsIgnoreCase("edittransfert")) {
                int id = Integer.parseInt(request.getParameter("idtransfert"));

                TransfertStock ts = transfertStockFacade.find(id);
                request.setAttribute("transf", ts);
                transferts.remove(ts);
                request.setAttribute("transfert", transferts);
                String mo = "refuser";
                request.setAttribute("modifier", mo);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("saveedit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int quantites = Integer.parseInt(request.getParameter("quantite"));
                String observation = request.getParameter("raison");

                TransfertStock ts = transfertStockFacade.find(id);
                ts.setQuantite(quantites);
                ts.setCommentaireMP(observation);
                ts.setPrixTotal(ts.getPrixUnitaire() * ts.getQuantite());
                ts.setDate(date_du_jour.dateJour());
                ts.setVisaMS("encour");
                ts.setEtat("encour");
                transfertStockFacade.edit(ts);
                transferts.remove(ts);

                commandeAll.clear();
                commandeAll = commandeMSFacade.findByEtatCommande("refuser", ts.getMs().getIdMagasin());
                for (CommandeMS commandeMS : commandeAll) {
                    if (commandeMS.getCodeProduit().equalsIgnoreCase(ts.getCodeProduit())) {
                        commandeMS.setEtatCommande("traiter");
                        commandeMSFacade.edit(commandeMS);
                        break;
                    }
                }

                request.setAttribute("transfert", transferts);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            } else if (action.equalsIgnoreCase("deletetransfert")) {
                int id = Integer.parseInt(request.getParameter("idtransfert"));

                TransfertStock ts = transfertStockFacade.find(id);
                transfertStockFacade.remove(ts);
                transferts.remove(ts);
                commandeAll.clear();
                commandeAll = commandeMSFacade.findByEtatCommande("refuser", ts.getMs().getIdMagasin());
                for (CommandeMS commandeMS : commandeAll) {
                    if (commandeMS.getCodeProduit().equalsIgnoreCase(ts.getCodeProduit())) {
                        commandeMSFacade.remove(commandeMS);
                        break;
                    }
                }
                request.setAttribute("transfert", transferts);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("refus")) {//a rectifier               
                int idd = Integer.parseInt(request.getParameter("id"));

                CommandeMS cms = commandeMSFacade.find(idd);

                commandeAll.remove(cms);
                request.setAttribute("action", "detaill");
                request.setAttribute("modifier", "refuser");
                request.setAttribute("Onecommande", commandeAll);
                request.setAttribute("commandeur", commandeur);
                request.setAttribute("cms", cms);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("edit")) {//a rectifier
                int idd = Integer.parseInt(request.getParameter("id"));

                commandeEdit = null;
                commandeEdit = commandeMSFacade.find(idd);

                commandeAll.remove(commandeEdit);

                request.setAttribute("action", "detaill");
                request.setAttribute("modifiers", "refuser");
                request.setAttribute("Onecommande", commandeAll);
                request.setAttribute("cms", commandeEdit);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            } else if (action.equalsIgnoreCase("savetransf")) {

                int idd = Integer.parseInt(request.getParameter("id"));
                int idMs = Integer.parseInt(request.getParameter("idMS"));
                String codes = request.getParameter("code");
                int quantites = Integer.parseInt(request.getParameter("quantite"));
                String observation = request.getParameter("raison");

                CommandeMS commandeMS = commandeMSFacade.find(idd);
                String designation = commandeMS.getDesignation();

                transfert = new TransfertStock();
                // MouvementProduits mouvementProduits = new MouvementProduits();
                StockproduitMP smp = stockproduitMPFacade.findByCodeProduitByidMP(idMP, codes);
                //on verifie si le stock du magasin principal peur suporter la commande
                if (smp.getQuantite() >= quantites) {

                    //je construit l'objet transfert 
                    transfert.setCodeProduit(codes);
                    transfert.setDesignation(smp.getDesignation());
                    transfert.setQuantite(quantites);
                    transfert.setMs(new MagasinSecondaire(idMs));
                    transfert.setMp(new MagasinPrincipal(idMP));
                    transfert.setDate(date_du_jour.dateJour());
                    transfert.setPrixUnitaire(smp.getPrixUnitaire());
                    transfert.setTypeProduit(smp.getCategorie().getNomCategorie());
                    transfert.setPrixTotal(smp.getPrixUnitaire() * commandeMS.getQuantiteCommande());
                    transfert.setVisaMP("OK");
                    transfert.setVisaMS("encour");
                    transfert.setCommentaireMP(observation);
                    transfert.setCommentaireMS("RAS");
                    transfert.setOperateur(p.getNomPrenom());
                    transfert.setEtat("encour");
                    //j'effectue le transfert
                    transfertStockFacade.create(transfert);
                    transfertStocks.add(transfert);

                    commandeMS.setEtatCommande("traiter");
                    commandeMSFacade.edit(commandeMS);
                    commandeAll.remove(commandeMS);

                    for (int i = 0; i < commandeur.size(); i++) {
                        MagasinSecondaire get = commandeur.get(i);
                        if (Objects.equals(get.getIdMagasin(), commandeMS.getIdMS().getIdMagasin()) && commandeAll.isEmpty()) {
                            commandeur.remove(i);
                            commandeAll.clear();
                            break;
                        }
                    }
                } else {
                    Message message = new Message("le Stock des " + designation + " est insufissant pour effectuer le transfert");
                    request.setAttribute("message", message);
                }
                String actio = "detaill";
                request.setAttribute("action", actio);
                request.setAttribute("commandeur", commandeur);
                request.setAttribute("commande", commande);
                request.setAttribute("Onecommande", commandeAll);
                request.setAttribute("vue", vue);
                request.setAttribute("transfertStocks", transfertStocks);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
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

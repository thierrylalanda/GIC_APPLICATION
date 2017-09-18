/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.controleur;

import com.gciapplication.SendMessage.EnvoiEmail;
import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CommandeMP;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.OrdreControleur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Responsablevalidation;
import com.gciapplication.entity.VisaChef;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CommandeMPFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.OrdreControleurFacadeLocal;
import com.gciapplication.session.entity.VisaChefFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
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
import org.json.simple.JSONArray;

@WebServlet(name = "ValiderCommande", urlPatterns = {"/ValiderCommande"})
public class ValiderCommande extends HttpServlet {

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private EnvoiEmail envoiEmail;

    @EJB
    private LoginFacadeLocal loginFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private CommandeMPFacadeLocal commandeMPFacade;

    @EJB
    private VisaChefFacadeLocal visaChefFacade;

    @EJB
    private OrdreControleurFacadeLocal ordreControleurFacade;

    List<CommandeMP> cmps = new ArrayList<>();
    List<OrdreControleur> ordreControleursC = new ArrayList<>();
    List<OrdreControleur> ordreControleu = new ArrayList<>();
    //private final String fourniture = "Fourniture Bureau", consommable = "Consommables Informatique";
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
            Responsablevalidation responsablevalidation = (Responsablevalidation) session.getAttribute("controle");
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            Personnel p = (Personnel) session.getAttribute("personnel");

            p.setLoginList(loginFacade.findBypersonnelID(p.getIdPersonnel()));
            request.setAttribute("personnel", p);
            // int[] ordreControleurs = ordreControleurFacade.findAllByNiveaux();
            if (action.equalsIgnoreCase("lister")) {

                ordreControleursC = ordreControleurFacade.findIDControleurs(p.getIdPersonnel());

                if (!ordreControleursC.isEmpty()) {
                    boolean lister = false;

                    switch (responsablevalidation.getNiveau()) {
                        case 1:
                            if (ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == p.getIdPersonnel()) {
                                lister = true;
                            }
                            break;
                        case 2:
                            lister = ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == p.getIdPersonnel();
                            break;
                        case 3:
                            lister = ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == p.getIdPersonnel();
                            break;
                        case 4:
                            lister = ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == p.getIdPersonnel();
                            break;
                        case 5:
                            lister = ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == p.getIdPersonnel();
                            break;
                        case 6:
                            lister = ordreControleursC.get(0).getEtat() == 1 && ordreControleursC.get(0).getControleur() == p.getIdPersonnel();
                            break;
                        default:
                            break;
                    }

                    if (lister) {

                        //ordreControleursC = ordreControleurFacade.findIDControleurs( p.getIdPersonnel());
                        cmps = commandeMPFacade.findByIdMPAndEtat(ordreControleursC.get(0).getIdMP().getIdMagasin(), etatCommande);
                        //   List<BudgetMP> bmp = budgetMPFacade.findBudgetByidMP(cmps.get(0).getIdMP().getIdMagasin());

                        request.setAttribute("produitMP", ordreControleursC.get(0).getIdMP().getCategorieproduitMPList());
                        request.setAttribute("listeCMP", cmps);
                        request.setAttribute("vue", vue);
                        request.setAttribute("nom_Magasin", cmps.get(0).getIdMP().getNomMagasin());
                        //  request.setAttribute("budget", bmp);
                        request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                    } else {
                        Message message = new Message("Aucune Commande pour l'instant !!!");
                        request.setAttribute("message", message);
                        request.setAttribute("vue", vue);
                        request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                    }

                } else {
                    Message message = new Message("Aucune Commande pour l'instant !!!");
                    request.setAttribute("message", message);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                }

            } else if (action.equalsIgnoreCase("validerAll")) {
                System.out.println("ouiiii taille");
                String[] commande = request.getParameter("idcommande").split(",");
                List<Integer> l = new ArrayList<>();
                for (String string : commande) {
                    l.add(Integer.parseInt(string));
                }

                if (!commandeMPFacade.findByIdMPAndEtat(ordreControleursC.get(0).getIdMP().getIdMagasin(), etatCommande).isEmpty()) {
                    System.out.println("ouiiii ");
                    boolean fini = false;
                    for (int i = 0; i < l.size(); i++) {
                        CommandeMP cmp = commandeMPFacade.find(l.get(i));
                        VisaChef visa = new VisaChef();

                        visa.setDecision("OK");
                        visa.setControleur(p);
                        visa.setCommande(new CommandeMP(cmp.getIdCommande()));
                        visa.setObservation("RAS");
                        visa.setValidation(new Responsablevalidation(responsablevalidation.getIdResponsableValidation()));
                        visaChefFacade.create(visa);
                        System.out.println("ouiiii dedant");
                        //cmp.setEtat("traiter");
                        //si ces le dernier controleur alors sa commande a ete completement valider 
                        List<OrdreControleur> oc = ordreControleurFacade.findAllByIdMP(cmp.getIdMP().getIdMagasin());
                        System.out.println(oc.size() + " taille");
                        if (oc.size() == 1) {
                            cmp.setEtat("OK");
                            cmp.setIndice(ordreControleurFacade.findAllByIdMP(cmp.getIdMP().getIdMagasin()).get(0).getNiveau());
                            System.out.println(oc.size() + " tailleeeee");
                           fini = true;
                        }
                        cmp.setIndice(1);
                        commandeMPFacade.edit(cmp);

                    }

                    ordreControleu = ordreControleurFacade.findAllByIdMP(cmps.get(0).getIdMP().getIdMagasin());

                    if (commandeMPFacade.findByIdMPAndEtatIndice(ordreControleursC.get(0).getIdMP().getIdMagasin(), etatCommande, 0).isEmpty() && !fini) {
                        for (int i = 0; i < ordreControleu.size(); i++) {
                            OrdreControleur get = ordreControleu.get(i);
                            if (get.getNiveau() > responsablevalidation.getNiveau()) {
                                get.setEtat(1);
                                ordreControleurFacade.edit(get);
                                break;
                            }
                        }
                        for (int i = 0; i < l.size(); i++) {
                            CommandeMP cmp = commandeMPFacade.find(l.get(i));
                            cmp.setEtat(etatCommande);
                            commandeMPFacade.edit(cmp);
                        }
                        date_du_jour.DeleteOrdre(p.getIdPersonnel());
                    }
                    if (fini) {
                        date_du_jour.DeleteOrdre(p.getIdPersonnel());
                    }
                    Notification notification = new Notification();
                    notification.setMessage("Les Commandes Passées en Date du " + cmps.get(0).getDate().toGMTString() + "\n ont étés aprouvers par " + p.getNomPrenom() + " veillez imprimer le Bon De Commande");
                    notification.setVue(0);
                    notification.setDate(date_du_jour.dateJour());
                    notification.setExpediteur(p.getIdPersonnel());
                    notification.setRecepteur(cmps.get(0).getIdMP().getIdMagasin());
                    notificationFacade.create(notification);

                    /*
                        try {
                            envoiEmail.EnvoiEmail(cmps.get(0).getIdMP().getAffectationmagasinPList().get(0).getPersonnel().getEmail(), "Commandes Validés", notification.getMessage());
                        } catch (MessagingException ex) {
                            Logger.getLogger(ValiderCommande.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     */
                    Message message = new Message("Vous Avez Approuvé toutes les commandes du Magasin " + cmps.get(0).getIdMP().getNomMagasin());
                    request.setAttribute("message", message);
                    request.setAttribute("vue", vue);
                    request.setAttribute("listeCMP", commandeMPFacade.findByIdMPAndEtatIndice(ordreControleursC.get(0).getIdMP().getIdMagasin(), etatCommande, 0));

                    request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                } else {
                    Message message = new Message("une erreur est survenue");
                    request.setAttribute("message", message);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                }

            } else if (action.equalsIgnoreCase("refusValidation")) {
                String raison = request.getParameter("raison");
                String[] commande = request.getParameterValues("idcommande");
                List<Integer> l = new ArrayList<>();
                for (String string : commande) {
                    l.add(Integer.parseInt(string));
                }
                for (int i = 0; i < l.size(); i++) {
                    CommandeMP cmp = commandeMPFacade.find(l.get(i));
                    /*
                    VisaChef visa = new VisaChef();

                    visa.setDecision("NON OK");
                    visa.setCommande(new CommandeMP(cmp.getIdCommande()));
                    visa.setObservation(raison);
                    visa.setControleur(p);
                    visa.setValidation(new Responsablevalidation(responsablevalidation.getIdResponsableValidation()));
                    visaChefFacade.create(visa);
                    cmp.setEtat("rejeter");*/
                    commandeMPFacade.remove(cmp);
                }
                Notification notification = new Notification();
                notification.setMessage("Les Commandes Passés en Date du " + cmps.get(0).getDate().toGMTString() + "\n à été Réjéter par " + p.getNomPrenom() + " pour des raisons : " + raison);
                notification.setVue(0);
                notification.setDate(date_du_jour.dateJour());
                notification.setExpediteur(p.getIdPersonnel());
                notification.setRecepteur(cmps.get(0).getIdMP().getIdMagasin());
                notificationFacade.create(notification);

                if (commandeMPFacade.findByIdMPAndEtat(ordreControleursC.get(0).getIdMP().getIdMagasin(), etatCommande).isEmpty()) {
                    for (int i = 0; i < ordreControleu.size(); i++) {
                        OrdreControleur get = ordreControleu.get(i);
                        if (get.getNiveau() > responsablevalidation.getNiveau()) {
                            get.setEtat(1);
                            ordreControleurFacade.edit(get);
                            break;
                        }
                    }
                    date_du_jour.DeleteOrdre(p.getIdPersonnel());
                }
                Message message = new Message("les commandes ont étés Rejeter et une notification à été envoyer au Magasin " + cmps.get(0).getIdMP().getNomMagasin() + " pour l'informer");
                request.setAttribute("message", message);
                request.setAttribute("vue", vue);
                request.setAttribute("listeCMP", commandeMPFacade.findByIdMPAndEtat(ordreControleursC.get(0).getIdMP().getIdMagasin(), etatCommande));

                cmps.clear();
                request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("budgetRegional")) {

                List<MagasinSecondaire> listMS = magasinSecondaireFacade.findByRegion(p.getService().getSite().getRegion().getNomRegion());
                List<Butget> BudgetRegion = new ArrayList<>();
                //List<Butget> BudgetMS = new ArrayList<>();
                for (MagasinSecondaire ms : listMS) {
                    List<Butget> BudgetMS = butgetFacade.findAllBudgetByidMS(ms.getIdMagasin());
                    for (Butget butget : BudgetMS) {
                        BudgetRegion.add(butget);
                    }

                }
                request.setAttribute("vue", vue);
                request.setAttribute("magasinsMS", listMS);
                request.setAttribute("statmagasin", "oui");
                request.setAttribute("budgetRegional", BudgetRegion);
                request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("budgetRegionalCategorie")) {

                Personnel per = (Personnel) session.getAttribute("personnel");
                List<Butget> Budget = butgetFacade.findByRegion(per.getService().getSite().getRegion().getNomRegion());
                List<String> Budgett = butgetFacade.findByRegionAndTypeBudget(per.getService().getSite().getRegion().getNomRegion());

                List<Butget> catms = butgetFacade.findAll();
                List<Butget> bud = new ArrayList<>();

                for (String catm : Budgett) {
                    Butget part = new Butget();
                    part.setMontant(Double.parseDouble("0"));
                    part.setMontantRestant(Double.parseDouble("0"));
                    part.setTypeBudget(catm);
                    for (Butget catmb : catms) {

                        if (catmb.getTypeBudget().equalsIgnoreCase(catm)) {
                            part.setMontant(part.getMontant() + catmb.getMontant());
                            part.setMontantRestant(part.getMontantRestant() + catmb.getMontantRestant());

                        }

                        System.out.println(catmb.getMontant());
                    }
                    bud.add(part);

                }

                request.setAttribute("statBudgetMS", "data");

                request.setAttribute("vue", vue);
                request.setAttribute("statmagasin", "non");
                request.setAttribute("budgets", bud);
                request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("etatButgetMS")) {
                int idMS = Integer.parseInt(request.getParameter("idMagasin"));
                org.json.JSONArray butgetMS = new org.json.JSONArray();
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                List<Butget> BudgetMS = butgetFacade.findAllBudgetByidMS(ms.getIdMagasin());
                butgetMS.put(BudgetMS);
                System.out.println(BudgetMS);
                response.setContentType("application/json");
                response.getWriter().print(butgetMS);

            } else if (action.equalsIgnoreCase("Statistiquebudget")) {
                int idMS = (int) session.getAttribute("id_magasinMSS");
                session.removeAttribute("id_magasinMSS");
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                double total = 0, montant = 0;

                for (Butget but : ms.getButgetList()) {
                    total = but.getMontantRestant();
                    montant = but.getMontant();
                }
                JSONArray ar = new JSONArray();
                JSONArray arr = new JSONArray();

                ar.add(montant);
                ar.add(total);
                arr.add(ar);
                String per = arr.toJSONString();
                response.setContentType("application/json");
                System.out.println(per);
                try {
                    response.getWriter().print(per);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            } else if (action.equalsIgnoreCase("redirect")) {
                int idMS = Integer.parseInt(request.getParameter("id_magasin"));
                session.setAttribute("id_magasinMSS", idMS);
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                List<Butget> BudgetMS = butgetFacade.findAllBudgetByidMS(idMS);
                request.setAttribute("statBudgetMS", "data");
                request.setAttribute("magasin", ms);
                request.setAttribute("idMS", ms.getIdMagasin());
                request.setAttribute("vue", vue);
                request.setAttribute("budgets", BudgetMS);
                request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
            } else {
                request.setAttribute("active", "data");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

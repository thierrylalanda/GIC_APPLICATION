package com.gciapplication.Commande_Clients;

import com.gciapplication.entity.Appariel;
import com.gciapplication.entity.Article;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.CommandePersonnel;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.Site;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.CommandePersonnelFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "Commande_All_Client", urlPatterns = "/Commande_All_Client")
public class Commande_All_Client extends HttpServlet {

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private SiteFacadeLocal siteFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private CommandePersonnelFacadeLocal commandePersonnelFacade;

    ArrayList<CommandePersonnel> listeCommandes = new ArrayList<>();
    ArrayList<OperationConsommateur> listeOperations = new ArrayList<>();
    List<CategorieproduitMS> categorieproduitMS = new ArrayList<>();
    String save = "", edit = "ok", nom_client = "";
    int idMS;

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
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            Personnel p = (Personnel) session.getAttribute("personnel");
            nom_client = p.getNomPrenom();
            String redirect;
            int niveau = 0;
            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            switch (niveau) {
                case 5:
                    redirect = "/WEB-INF/administrateur/main1.jsp";

                    break;
                case 3:
                    redirect = "/WEB-INF/magasignierP/main.jsp";
                    break;
                case 2:
                    redirect = "/WEB-INF/magasignerS/main.jsp";
                    break;
                default:
                    redirect = "/WEB-INF/controleurs/main.jsp";
                    break;
            }
            if (action.equalsIgnoreCase("ajouter")) {
                int id;
                if (request.getParameter("personnel") != null) {
                    id = Integer.parseInt(request.getParameter("personnel"));
                } else {
                    id = p.getIdPersonnel();
                }
                List<CommandePersonnel> l = commandePersonnelFacade.findCommandeNonReceptionner(id, "non");
                if (l.isEmpty()) {

                    idMS = Integer.parseInt(request.getParameter("magasin"));

                    MagasinSecondaire magasinS = magasinSecondaireFacade.find(idMS);

                    // Personnel p2 = personnelFacade.find(magasinS.getAffectationmagasinSList().get(0).getPersonnel().getIdPersonnel());
                    Region r1 = p.getService().getSite().getRegion();
                    String r2 = magasinS.getSite().getRegion().getNomRegion();

                    if (r2.equalsIgnoreCase(r1.getNomRegion())) {

                        String appariel = request.getParameter("appariel");
                        int designation = Integer.parseInt(request.getParameter("designation"));
                        int quantites = Integer.parseInt(request.getParameter("quantite"));

                        Article a = articleFacade.find(designation);
                        boolean get = false;
                        StockproduitMS stock = new StockproduitMS();
                        try {
                            stock = stockproduitMSFacade.findByDesignationByidMS(idMS, a.getDesignation());
                        } catch (Exception e) {
                            get = true;
                        }

                        if (!get) {
                            CommandePersonnel cp = new CommandePersonnel();

                            boolean repeat = false;
                            if (!listeCommandes.isEmpty()) {
                                for (CommandePersonnel commande : listeCommandes) {
                                    if (commande.getDesignations().equalsIgnoreCase(stock.getDesignation())) {
                                        repeat = true;
                                        break;
                                    }
                                }
                            }

                            if (!repeat) {
                                OperationConsommateur oc = new OperationConsommateur();

                                cp.setCodeAppareil(new Appariel(appariel));
                                cp.setDate(date_du_jour.dateJour());
                                cp.setDesignations(stock.getDesignation());
                                cp.setEtatCommande("encour de traitement");
                                cp.setQuantite(quantites);
                                cp.setIdPersonnel(new Personnel(p.getIdPersonnel()));
                                cp.setIdMS(new MagasinSecondaire(idMS));

                                oc.setCodeProduit(stock.getCodeProduit());
                                oc.setAppariel(cp.getCodeAppareil().getNumeroParck());
                                oc.setCategorie(stock.getCategorie().getNomCategorie());
                                oc.setDate(date_du_jour.dateJour());
                                oc.setDesignation(stock.getDesignation());
                                oc.setPersonnel(new Personnel(p.getIdPersonnel()));
                                oc.setMagasin(new MagasinSecondaire(idMS));
                                oc.setQuantite(quantites);
                                oc.setPrix(stock.getPrixUnitaire());
                                oc.setPrixTotal(stock.getPrixUnitaire() * cp.getQuantite());

                                if (cp.getQuantite() <= stock.getQuantite() || cp.getQuantite() > stock.getQuantite()) {

                                    listeCommandes.add(cp);
                                    listeOperations.add(oc);

                                    categorieproduitMS = magasinS.getCategorieproduitMSList();
                                    request.setAttribute("produitMS", categorieproduitMS);
                                    request.setAttribute("edit", edit);
                                    request.setAttribute("nom_client", nom_client);
                                    request.setAttribute("vue", vue);
                                    request.setAttribute("listeoperation", listeOperations);
                                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                                } else {

                                    categorieproduitMS = magasinS.getCategorieproduitMSList();
                                    request.setAttribute("produitMS", categorieproduitMS);
                                    request.setAttribute("edit", edit);
                                    request.setAttribute("nom_client", nom_client);
                                    request.setAttribute("vue", vue);
                                    request.setAttribute("listecommande", listeCommandes);
                                    request.setAttribute("listeoperation", listeOperations);
                                    Message message = new Message("Une Erreur est Survenue le Stock des " + cp.getDesignations() + " est insuffissant veuillez revoir la Quantite que vous êtes entrain de commander");
                                    request.setAttribute("message", message);

                                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                                }

                            } else {
                                Message message = new Message("Une Erreur est survenue Vous essayez de commander deux fois un même Article : " + a.getDesignation());
                                request.setAttribute("message", message);
                                categorieproduitMS = magasinS.getCategorieproduitMSList();
                                request.setAttribute("produitMS", categorieproduitMS);
                                request.setAttribute("edit", edit);
                                request.setAttribute("nom_client", nom_client);
                                request.setAttribute("listecommande", listeCommandes);
                                request.setAttribute("listeoperation", listeOperations);
                                request.setAttribute("vue", vue);
                                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                            }
                        } else {
                            Message message = new Message("Une Erreur est survenue le Magasin: " + magasinSecondaireFacade.find(idMS).getNomMagasin() + " ne Possède pas cet Article");
                            request.setAttribute("message", message);
                            categorieproduitMS = magasinS.getCategorieproduitMSList();
                            request.setAttribute("produitMS", categorieproduitMS);
                            request.setAttribute("edit", edit);
                            request.setAttribute("nom_client", nom_client);
                            request.setAttribute("listecommande", listeCommandes);
                            request.setAttribute("listeoperation", listeOperations);
                            request.setAttribute("vue", vue);
                            request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                        }
                    } else {
                        //fois = 0;
                        request.setAttribute("vue", vue);
                        Message message = new Message("Vous n'est pas dans la region du " + r2);
                        request.setAttribute("message", message);

                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                    }
                } else {

                    request.setAttribute("vue", vue);
                    Message message = new Message("Vous ne pouvez pas passer une nouvelle commande tanque vous n'avez pas approuvé la réception de la commande précédente");
                    request.setAttribute("message", message);
                    request.setAttribute("listecommande", l);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                }
            } else if (action.equalsIgnoreCase("delete")) {

                String code = request.getParameter("code");
                String designation = "";
                for (int i = 0; i < listeOperations.size(); i++) {
                    OperationConsommateur oc = listeOperations.get(i);

                    if (oc.getCodeProduit().equals(code)) {
                        designation = oc.getDesignation();

                        listeOperations.remove(i);
                        break;
                    }
                }
                for (int i = 0; i < listeCommandes.size(); i++) {
                    CommandePersonnel cp = listeCommandes.get(i);

                    if (cp.getDesignations().equals(designation)) {

                        listeCommandes.remove(i);
                        break;
                    }
                }

                request.setAttribute("produitMS", categorieproduitMS);
                request.setAttribute("edit", edit);
                request.setAttribute("nom_client", nom_client);
                request.setAttribute("vue", vue);
                request.setAttribute("listeoperation", listeOperations);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("update")) {

                String designation = "";
                String code = request.getParameter("code");
                int quantite = Integer.parseInt(request.getParameter("qte"));

                for (int i = 0; i < listeOperations.size(); i++) {
                    OperationConsommateur oc = listeOperations.get(i);

                    if (listeOperations.get(i).getCodeProduit().equals(code)) {
                        listeOperations.get(i).setQuantite(quantite);
                        listeOperations.get(i).setPrixTotal(listeOperations.get(i).getPrix() * (double) quantite);
                        designation = oc.getDesignation();
                        break;

                    }
                }
                for (int i = 0; i < listeCommandes.size(); i++) {

                    if (listeCommandes.get(i).getDesignations().equalsIgnoreCase(designation)) {
                        listeCommandes.get(i).setQuantite(quantite);
                        break;

                    }
                }

                response.setContentType("application/text");
                response.getWriter().print(String.valueOf(quantite));

            } else if (action.equalsIgnoreCase("saveClient")) {
                //   double prixTotal = 0;
                Personnel p1 = personnelFacade.findByNomPrenom(nom_client);
                MagasinSecondaire magasinS = magasinSecondaireFacade.find(idMS);

                //je sauvegade la commande du personnel dans la BD
                for (int i = 0; i < listeCommandes.size(); i++) {
                    CommandePersonnel cp = listeCommandes.get(i);
                    commandePersonnelFacade.create(cp);
                }

                listeCommandes.clear();
                listeOperations.clear();
                nom_client = "";

                Notification n = new Notification();
                n.setMessage("Vous Avez Reçus une Commande De la part de : " + p1.getNomPrenom() + " \nle " + new Date().toLocaleString() + " \nMerci de bien Vouloir la Traiter");
                n.setRecepteur(magasinS.getIdMagasin());
                n.setVue(0);
                n.setDate(date_du_jour.dateJour());
                notificationFacade.create(n);
                /*
//J'envoi un SMS Au Magasin Secondaire pour le prevenir de la commande qui viens d'etre passer
                try {
                    SendSMS.SendSMS(n.getMessage(), magasinS.getAffectationmagasinSList().get(0).getPersonnel().getTelephone());
                } catch (Exception e) {
                    System.err.println("une Erreur est survenue pour l'envoi du SMS " + e.getMessage());
                }
                 */
                Message message = new Message("Votre Commande à bien été Enregistrer");
                request.setAttribute("message", message);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("init")) {
                String magasin = request.getParameter("magasin");
                String all = "tous";
                MagasinSecondaire magasinS = magasinSecondaireFacade.findByNam(magasin);
                request.setAttribute("categorie", magasinS.getCategorieproduitMSList());
                request.setAttribute("all", all);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("chargerProduitMS")) {
                int idMSS = Integer.parseInt(request.getParameter("magasin"));

                // MagasinSecondaire magasinS = magasinSecondaireFacade.find(idMS);
                // categorieproduitMS = magasinS.getCategorieproduitMSList();
                List<StockproduitMS> list = stockproduitMSFacade.findStockMP(idMSS);
                Map myMap;
                JSONArray o = new JSONArray();

                for (StockproduitMS st : list) {
                    myMap = new HashMap<>();
                    myMap.put("categorie", st.getCategorie().getNomCategorie());
                    myMap.put("code", st.getCodeProduit());
                    myMap.put("date", st.getDateLivraison());
                    myMap.put("designation", st.getDesignation());
                    myMap.put("total", st.getPrixTotal());
                    myMap.put("unitaire", st.getPrixUnitaire());
                    myMap.put("idStock", st.getIdStock());
                    myMap.put("quantite", st.getQuantite());
                    myMap.put("idcat", st.getCategorie().getIdCategorie());
                    JSONObject ar = new JSONObject(myMap);
                    o.put(ar);
                }
                list.removeAll(list);
                String per = o.toString();
                response.setContentType("application/json");
                response.getWriter().print(per);

            } else if (action.equalsIgnoreCase("ApprobationCommandePersonnel")) {
                int id;
                if (request.getParameter("personnel") != null) {
                    id = Integer.parseInt(request.getParameter("personnel"));
                } else {
                    id = p.getIdPersonnel();
                }
                List<CommandePersonnel> l = commandePersonnelFacade.findCommandeNonReceptionner(id, "non");
                if (!l.isEmpty()) {
                    for (CommandePersonnel cp : l) {
                        cp.setReception("oui");
                        commandePersonnelFacade.edit(cp);
                    }
                }
                String magasin = request.getParameter("magasin");
                String init = "init";
                request.setAttribute("edit", edit);
                request.setAttribute("nom_client", nom_client);
                request.setAttribute("al", init);
                request.setAttribute("vue", vue);
                if (magasin.equalsIgnoreCase("MP")) {
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                } else if (magasin.equalsIgnoreCase("personel")) {
                    request.getServletContext().getRequestDispatcher("/WEB-INF/personnels/main.jsp").forward(request, response);
                } else if (magasin.equalsIgnoreCase("MS")) {
                    if (request.getParameter("personnel") != null) {
                        request.setAttribute("personnell", personnelFacade.find(Integer.parseInt(request.getParameter("personnel"))));
                    }

                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                } else if (magasin.equalsIgnoreCase("Admin")) {
                    request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                } else {
                    List<OperationConsommateur> cps = operationConsommateurFacade.findAllByIdPersonnel(p.getIdPersonnel());
                    request.setAttribute("operation", cps);

                    request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                }
            } else {

                Site s = siteFacade.find(p.getService().getSite().getIdSite());
                List<MagasinSecondaire> ListMS = magasinSecondaireFacade.findByRegion(s.getRegion().getNomRegion());
                int idMagasinMS = 0;
                if (session.getAttribute("id_magasin") != null) {
                    idMagasinMS = (int) session.getAttribute("id_magasin");
                }
                try {
                    //MagasinSecondaire magasinS = magasinSecondaireFacade.find(ListMS.get(0).getIdMagasin());
                    // List<CategorieproduitMS> categorieMS = magasinS.getCategorieproduitMSList();
                    session.setAttribute("categorie", categorieproduitMSFacade.findCatByidMS(idMagasinMS));
                } catch (Exception e) {

                    Message message = new Message("Aucun Magasin n'existe Dans Votre Region");
                    request.setAttribute("message", message);

                }

                String magasin = request.getParameter("magasin");
                String init = "init";
                request.setAttribute("edit", edit);
                request.setAttribute("nom_client", nom_client);
                request.setAttribute("al", init);
                request.setAttribute("vue", vue);
                if (magasin.equalsIgnoreCase("MP")) {
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                } else if (magasin.equalsIgnoreCase("personel")) {
                    request.getServletContext().getRequestDispatcher("/WEB-INF/personnels/main.jsp").forward(request, response);
                } else if (magasin.equalsIgnoreCase("MS")) {
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                } else if (magasin.equalsIgnoreCase("Admin")) {
                    session.setAttribute("magasinS", ListMS);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                } else {
                    List<OperationConsommateur> cps = operationConsommateurFacade.findAllByIdPersonnel(p.getIdPersonnel());
                    request.setAttribute("operation", cps);
                    if (!commandePersonnelFacade.findpersonnelCommandeByidpersonnel(p.getIdPersonnel(), "encour de traitement").isEmpty()) {
                        request.setAttribute("commandesEncour", commandePersonnelFacade.findpersonnelCommandeByidpersonnel(p.getIdPersonnel(), "encour de traitement"));
                    } else {
                        if (!commandePersonnelFacade.findCommandeNonReceptionner(p.getIdPersonnel(), "non").isEmpty()) {
                            request.setAttribute("reception", "non");
                        }

                        request.setAttribute("commandesEncour", commandePersonnelFacade.findCommandeNonReceptionner(p.getIdPersonnel(), "non"));
                    }

                    request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                }

            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

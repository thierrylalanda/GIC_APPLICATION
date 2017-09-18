package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.CommandeMS;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.entity.TransfertStock;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.CommandeMSFacadeLocal;
import com.gciapplication.session.entity.CommandePersonnelFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
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

@WebServlet(name = "commandeMS", urlPatterns = {"/commandeMS"})
public class commandeMS extends HttpServlet {

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private CommandePersonnelFacadeLocal commandePersonnelFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private CommandeMSFacadeLocal commandeMSFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    List< CommandeMS> list = new ArrayList<>();

    List< MagasinPrincipal> mpp = new ArrayList<>();
    List<CommandeMS> listeCommande = new ArrayList<>();
    Personnel personnelS;
    String fournisseur, edit = "ok", vue, action = "";
    int fois = 0;
    final String etat = "encour";
    final String rejet = "rejeter";

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

            mpp = magasinPrincipalFacade.findAllByregion(p.getService().getSite().getRegion().getNomRegion());

            int idMS;
            if (session.getAttribute("id_magasin") == null) {
                idMS = Integer.parseInt(request.getParameter("id_magasin"));

            } else {
                idMS = (int) session.getAttribute("id_magasin");
            }

            int niveau = 0;
            String redirect;
            if (request.getParameter("niveau") != null) {
                niveau = Integer.parseInt(request.getParameter("niveau"));
            }
            if (niveau == 5) {
                List< MagasinPrincipal> MP = magasinPrincipalFacade.findAll();
                request.setAttribute("fournisseur", MP);
                List<TransfertStock> transfert = transfertStockFacade.findByVisaMSByidMS("encour", idMS);
                if (transfert.isEmpty()) {
                    Message message = new Message("Aucun Transfert De Stock Reçus Pour le Moment");
                    request.setAttribute("message", message);

                } else {
                    request.setAttribute("transfert", transfert);
                }

                List<Personnel> listePersonnel = commandePersonnelFacade.findByEtatCommandeReturnPersonel(idMS, "encour de traitement");

                request.setAttribute("listepersonnel", listePersonnel);

                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);

                request.setAttribute("magasin", ms);
                request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("commanderPour", "OK");
                request.setAttribute("CommandeClient", "OK");

                redirect = "/WEB-INF/administrateur/main1.jsp";

            } else {
                redirect = "/WEB-INF/magasignerS/main.jsp";

            }

            action = request.getParameter("action");
            vue = request.getParameter("vue");
            request.setAttribute("vue", vue);

            if (action.equalsIgnoreCase("delete")) {
                String cod = request.getParameter("code");
                for (int i = 0; i < list.size(); i++) {
                    CommandeMS s = list.get(i);
                    if (s.getCodeProduit().equalsIgnoreCase(cod)) {
                        list.remove(i);
                        break;
                    }
                }

                Message message = new Message();
                request.setAttribute("message", message);
                request.setAttribute("editt", edit);
                request.setAttribute("OK", "OK");
                request.setAttribute("fournisseur", mpp);
                request.setAttribute("vue", vue);
                request.setAttribute("listt", list);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("update")) {
                String codes = request.getParameter("code");
                int somm = Integer.parseInt(request.getParameter("qte"));

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCodeProduit().equalsIgnoreCase(codes)) {
                        list.get(i).setQuantiteCommande(somm);
                        break;

                    }

                }
                response.setContentType("application/text");
                response.getWriter().print(somm);

            } else if (action.equalsIgnoreCase("create")) {
                boolean repeat = false;

                int designation = Integer.parseInt(request.getParameter("designation"));
                int quantites = Integer.parseInt(request.getParameter("quantite"));
                int idMP = Integer.parseInt(request.getParameter("fournisseur"));
                MagasinPrincipal mp = magasinPrincipalFacade.find(idMP);
                Article a = articleFacade.find(designation);
                boolean get = false;
                StockproduitMS stock = new StockproduitMS();
                try {
                    stock = stockproduitMSFacade.findByDesignationByidMS(idMS, a.getDesignation());
                } catch (Exception e) {
                    get = true;
                }
                // StockproduitMS stock = new StockproduitMS();

                if (!get) {
                    String name = "";
                    if (!list.isEmpty()) {
                        for (CommandeMS commandeMS : list) {
                            if (commandeMS.getCodeProduit().equalsIgnoreCase(stock.getCodeProduit())) {
                                repeat = true;
                                name = stock.getDesignation();
                                list.remove(commandeMS);
                                break;
                            }
                        }
                    }
                    if (!repeat) {

                        CommandeMS cms = new CommandeMS();

                        cms.setIdMP(new MagasinPrincipal(idMP));
                        cms.setIdMS(new MagasinSecondaire(idMS));
                        cms.setDate(date_du_jour.dateJour());
                        cms.setQuantiteCommande(quantites);
                        cms.setTypeProduit(stock.getCategorie().getNomCategorie());
                        cms.setDesignation(stock.getDesignation());
                        cms.setCodeProduit(stock.getCodeProduit());
                        cms.setQuantiteEnStock(stock.getQuantite());
                        cms.setDerniereLivraison(stock.getDateLivraison());
                        cms.setEtatCommande(etat);
                        list.add(cms);

                        // request.setAttribute("fournisseur", mpp);
                        request.setAttribute("vue", vue);
                        request.setAttribute("magasinMP", mp);
                        request.setAttribute("editt", edit);
                        request.setAttribute("listt", list);
                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                    } else {

                        Message message = new Message("Une Erreur est survenue Vous essayez de commander deux fois un même Article : " + name);
                        request.setAttribute("message", message);
                        request.setAttribute("OK", "OK");
                        request.setAttribute("fournisseur", mpp);
                        request.setAttribute("listt", list);
                        request.setAttribute("vue", vue);
                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                    }
                } else {
                    Message message = new Message("Votre Magasin ne Possède Pas cet Article");
                    request.setAttribute("message", message);
                    request.setAttribute("OK", "OK");
                    request.setAttribute("fournisseur", mpp);
                    request.setAttribute("vue", vue);
                    request.setAttribute("listt", list);
                    request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                }

            } else if (action.equalsIgnoreCase("save")) {
                if (!list.isEmpty()) {

                    int ins = 0;

                    List< CommandeMS> listCMS = commandeMSFacade.findByEtatCommande(etat, idMS);
                    List< CommandeMS> listCMST = commandeMSFacade.findByEtatCommande("traiter", idMS);
                    for (CommandeMS cms : listCMST) {
                        listCMS.add(cms);
                    }

                    List< CommandeMS> listCMSR = new ArrayList<>();

                    List<Integer> occ = new ArrayList<>();
                    for (int i = 0; i < listCMS.size(); i++) {
                        CommandeMS gett = listCMS.get(i);
                        for (int j = 0; j < list.size(); j++) {
                            if (gett.getCodeProduit().equalsIgnoreCase(list.get(j).getCodeProduit())) {
                                listCMSR.add(gett);

                                occ.add(j);
                                list.remove(j);
                                ins = 1;
                                break;
                            }
                        }

                    }

                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            CommandeMS get = list.get(i);
                            commandeMSFacade.create(get);
                        }

                        Notification n = new Notification();
                        n.setMessage("Vous Avez Reçus une Commande De la part du Magasin : " + magasinSecondaireFacade.find(idMS).getNomMagasin() + " \nMerci de Bien Vouloir la Traiter");
                        n.setRecepteur(list.get(0).getIdMP().getIdMagasin());
                        n.setVue(0);
                        n.setDate(date_du_jour.dateJour());
                        notificationFacade.create(n);
                        /*
                        //J'envoi un SMS Au Magasin Secondaire pour le prevenir de la commande qui viens d'etre passer
                        try {
                            //SendSMS.SendSMS(n.getMessage(), list.get(0).getIdMP().getAffectationmagasinPList().get(0).getPersonnel().getTelephone());
                        } catch (Exception e) {
                            System.err.println("une Erreur est survenue pour l'envoi du SMS " + e.getMessage());
                        }
                    }*/
                        list.clear();
                        if (ins == 1) {
                            Message message = new Message("certains Articles n'ont pas été Enregistrer car ils ont encore une instance de commande en cour... ");
                            request.setAttribute("listt", listCMSR);
                            request.setAttribute("message", message);
                        } else {
                            Message message = new Message("Votre Commande a bien ete Enregistrer");

                            request.setAttribute("message", message);
                        }

                        request.setAttribute("fournisseur", mpp);
                        request.setAttribute("OK", "OK");
                        request.setAttribute("vue", vue);
                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

                    } else {
                        request.setAttribute("vue", vue);
                        String OK = "OK";
                        request.setAttribute("OK", OK);
                        Message message = new Message("Une Erreur est survenue vous n'avez passer Auccune Commande!!!");
                        request.setAttribute("message", message);

                        request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
                    }
                }
            } else if (action.equalsIgnoreCase("init")) {

                request.setAttribute("fournisseur", mpp);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("show")) {

                listeCommande.clear();
                listeCommande = commandeMSFacade.findByEtatCommande(etat, idMS);
                List<CommandeMS> listeCommandeRegeter = commandeMSFacade.findByEtatCommande(rejet, idMS);
                listeCommandeRegeter.stream().forEach((commandeMS) -> {
                    listeCommande.add(commandeMS);
                });
                mpp = magasinPrincipalFacade.findAllByregion(p.getService().getSite().getRegion().getNomRegion());
                request.setAttribute("fournisseur", mpp);
                request.setAttribute("listeCommande", listeCommande);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
//modifier une commande existante ou rejeter
            } else if (action.equalsIgnoreCase("modifier")) {

                int idC = Integer.parseInt(request.getParameter("code"));

                listeCommande.clear();
                listeCommande = commandeMSFacade.findByEtatCommande(etat, idMS);
                List<CommandeMS> listeCommandeRegeter = commandeMSFacade.findByEtatCommande(rejet, idMS);
                listeCommandeRegeter.stream().forEach((commandeMS) -> {
                    listeCommande.add(commandeMS);
                });

                CommandeMS cms = commandeMSFacade.find(idC);

                for (int i = 0; i < listeCommande.size(); i++) {
                    CommandeMS commandeMS = listeCommande.get(i);
                    if (commandeMS.getCodeProduit().equalsIgnoreCase(cms.getCodeProduit())) {
                        listeCommande.remove(i);
                        break;
                    }
                }
                request.setAttribute("cms", cms);
                String modifier = "modification";
                request.setAttribute("listeCommande", listeCommande);
                request.setAttribute("modifier", modifier);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("saveUpdate")) {
                int idC = Integer.parseInt(request.getParameter("code"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));

                CommandeMS cms = commandeMSFacade.find(idC);

                cms.setQuantiteCommande(quantite);
                cms.setEtatCommande(etat);
                commandeMSFacade.edit(cms);

                listeCommande.removeAll(listeCommande);
                listeCommande = commandeMSFacade.findByEtatCommande(etat, idMS);
                List<CommandeMS> listeCommandeRegeter = commandeMSFacade.findByEtatCommande("rejeter", idMS);
                listeCommandeRegeter.stream().forEach((commandeMS) -> {
                    listeCommande.add(commandeMS);
                });

                request.setAttribute("listeCommande", listeCommande);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (action.equalsIgnoreCase("deleteCMS")) {

                int idC = Integer.parseInt(request.getParameter("code"));

                CommandeMS cms = commandeMSFacade.find(idC);
                if (cms != null) {
                    commandeMSFacade.remove(cms);

                    listeCommande.removeAll(listeCommande);
                    listeCommande = commandeMSFacade.findByEtatCommande(etat, idMS);
                    List<CommandeMS> listeCommandeRegeter = commandeMSFacade.findByEtatCommande("rejeter", idMS);
                    listeCommandeRegeter.stream().forEach((commandeMS) -> {
                        listeCommande.add(commandeMS);
                    });
                }
                request.setAttribute("listeCommande", listeCommande);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}


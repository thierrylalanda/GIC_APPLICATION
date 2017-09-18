package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CommandePersonnel;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StatistiqueArticlesDirection;
import com.gciapplication.entity.StatistiqueArticlesMagasinMs;
import com.gciapplication.entity.StatistiqueArticlesPersonnel;
import com.gciapplication.entity.StatistiqueArticlesRegion;
import com.gciapplication.entity.StatistiqueArticlesService;
import com.gciapplication.entity.StatistiqueArticlesSite;
import com.gciapplication.entity.StatistiqueCategorieDirection;
import com.gciapplication.entity.StatistiqueCategorieMagasinMs;
import com.gciapplication.entity.StatistiqueCategoriePersonnel;
import com.gciapplication.entity.StatistiqueCategorieRegion;
import com.gciapplication.entity.StatistiqueCategorieService;
import com.gciapplication.entity.StatistiqueCategorieSite;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.entity.TransfertStock;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CommandePersonnelFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesDirectionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesMagasinMsFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesPersonnelFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesRegionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesServiceFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesSiteFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieDirectionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieMagasinMsFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategoriePersonnelFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieRegionFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieServiceFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieSiteFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CommandeRecus", urlPatterns = {"/commanderecus"})
public class CommandeRecus extends HttpServlet {

    @EJB
    private StatistiqueCategorieSiteFacadeLocal statistiqueCategorieSiteFacade;

    @EJB
    private StatistiqueCategorieServiceFacadeLocal statistiqueCategorieServiceFacade;

    @EJB
    private StatistiqueCategorieRegionFacadeLocal statistiqueCategorieRegionFacade;

    @EJB
    private StatistiqueCategoriePersonnelFacadeLocal statistiqueCategoriePersonnelFacade;

    @EJB
    private StatistiqueCategorieMagasinMsFacadeLocal statistiqueCategorieMagasinMsFacade;

    @EJB
    private StatistiqueCategorieDirectionFacadeLocal statistiqueCategorieDirectionFacade;

    @EJB
    private StatistiqueArticlesSiteFacadeLocal statistiqueArticlesSiteFacade;

    @EJB
    private StatistiqueArticlesServiceFacadeLocal statistiqueArticlesServiceFacade;

    @EJB
    private StatistiqueArticlesRegionFacadeLocal statistiqueArticlesRegionFacade;

    @EJB
    private StatistiqueArticlesPersonnelFacadeLocal statistiqueArticlesPersonnelFacade;

    @EJB
    private StatistiqueArticlesMagasinMsFacadeLocal statistiqueArticlesMagasinMsFacade;

    @EJB
    private StatistiqueArticlesDirectionFacadeLocal statistiqueArticlesDirectionFacade;

    @EJB
    private ArticleFacadeLocal articleFacade;

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private CommandePersonnelFacadeLocal commandePersonnelFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    List<Personnel> listePersonnel;
    List<CommandePersonnel> commandess = new ArrayList<>();
    List<OperationConsommateur> list = new ArrayList<>();

    Personnel p, personnell;

    private final String etatC = "encour de traitement";
    int idP, idcli;
    double prixTotal;

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

            String actions = request.getParameter("action");
            String vue = request.getParameter("vue");

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
                List< MagasinPrincipal> mpp = magasinPrincipalFacade.findAll();
                request.setAttribute("fournisseur", mpp);
                List<TransfertStock> transfert = transfertStockFacade.findByVisaMSByidMS("encour", idMS);
                if (transfert.isEmpty()) {
                    request.setAttribute("transfert", transfert);
                    Message message = new Message("Aucun Transfert De Stock Reçus Pour le Moment");
                    request.setAttribute("message", message);

                } else {
                    request.setAttribute("transfert", transfert);

                }

                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);

                request.setAttribute("magasin", ms);
                request.setAttribute("categorie", ms.getCategorieproduitMSList());

                redirect = "/WEB-INF/administrateur/main1.jsp";

            } else {
                redirect = "/WEB-INF/magasignerS/main.jsp";

            }

            p = (Personnel) session.getAttribute("personnel");
            // int idMS = p.getMagasinSecondaireList().get(0).getIdMagasin();

            if (actions.equalsIgnoreCase("allclient")) {

                listePersonnel = commandePersonnelFacade.findByEtatCommandeReturnPersonel(idMS, etatC);

                request.setAttribute("listepersonnel", listePersonnel);
                request.setAttribute("vue", vue);
                request.setAttribute("CommandeClient", "OK");
                request.setAttribute("trait", "OK");

                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (actions.equalsIgnoreCase("lister")) {

                idcli = Integer.parseInt(request.getParameter("idclient"));

                commandess.clear();
                list.clear();
                commandess = commandePersonnelFacade.findpersonnelCommande(idcli, idMS, etatC);

                prixTotal = 0;

                for (int i = 0; i < commandess.size(); i++) {

                    CommandePersonnel cp = commandess.get(i);

                    StockproduitMS mS = stockproduitMSFacade.findByDesignationByidMS(idMS, cp.getDesignations());

                    OperationConsommateur oc = new OperationConsommateur();

                    oc.setAppariel(cp.getCodeAppareil().getNumeroParck());

                    oc.setCategorie(mS.getCategorie().getNomCategorie());
                    oc.setCodeProduit(mS.getCodeProduit());
                    oc.setDate(cp.getDate());
                    oc.setDesignation(cp.getDesignations());
                    oc.setPrix(mS.getPrixUnitaire());
                    oc.setPrixTotal(mS.getPrixUnitaire() * cp.getQuantite());
                    oc.setQuantite(cp.getQuantite());
                    oc.setPersonnel(new Personnel(idcli));
                    oc.setMagasin(new MagasinSecondaire(idMS));
                    oc.setStock(mS.getQuantite());
                    oc.setOperateur(p.getNomPrenom());
                    oc.setStockApres(mS.getQuantite() - oc.getQuantite());
                    prixTotal += mS.getPrixUnitaire() * cp.getQuantite();

                    list.add(oc);

                }
                request.setAttribute("clients", personnelFacade.find(idcli));
                request.setAttribute("CommandeClient", "OK");
                request.setAttribute("trait", "OK");
                String action = "detaill";
                request.setAttribute("action", action);
                request.setAttribute("total", prixTotal);
                request.setAttribute("details", "detail");
                request.setAttribute("vue", vue);
                request.setAttribute("listepersonnel", listePersonnel);
                request.setAttribute("list", list);
                request.setAttribute("commande", commandess);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (actions.equalsIgnoreCase("updateQuantite")) {
                int idCommande = Integer.parseInt(request.getParameter("id_Commande"));
                CommandePersonnel cp = commandePersonnelFacade.find(idCommande);

                request.setAttribute("clients", personnelFacade.find(idcli));
                request.setAttribute("CommandeClient", "OK");
                request.setAttribute("trait", "OK");
                request.setAttribute("updateCommande", "yes");
                request.setAttribute("action", "detaill");
                request.setAttribute("total", prixTotal);
                request.setAttribute("details", "detail");
                request.setAttribute("vue", vue);
                request.setAttribute("commandeOne", cp);
                request.setAttribute("listepersonnel", listePersonnel);
                request.setAttribute("list", list);
                request.setAttribute("commande", commandess);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (actions.equalsIgnoreCase("SaveupdateQuantite")) {

                int idCommande = Integer.parseInt(request.getParameter("id_Commande"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                CommandePersonnel cp = commandePersonnelFacade.find(idCommande);
                commandess.remove(cp);
                cp.setQuantite(quantite);
                commandePersonnelFacade.edit(cp);
                commandess.add(cp);
                for (OperationConsommateur opera : list) {
                    if (opera.getDesignation().equalsIgnoreCase(cp.getDesignations())) {
                        list.remove(opera);
                        opera.setQuantite(cp.getQuantite());
                        opera.setPrixTotal(cp.getQuantite() * opera.getPrix());
                        list.add(opera);
                        break;
                    }
                }
                request.setAttribute("clients", personnelFacade.find(idcli));
                request.setAttribute("CommandeClient", "OK");
                request.setAttribute("trait", "OK");
                String action = "detaill";
                request.setAttribute("action", action);
                request.setAttribute("total", prixTotal);
                request.setAttribute("details", "detail");
                request.setAttribute("vue", vue);
                request.setAttribute("listepersonnel", listePersonnel);
                request.setAttribute("list", list);
                request.setAttribute("commande", commandess);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (actions.equalsIgnoreCase("deleteCommandePerso")) {
                int idCommande = Integer.parseInt(request.getParameter("id_Commande"));

                CommandePersonnel cp = commandePersonnelFacade.find(idCommande);
                commandePersonnelFacade.remove(cp);
                for (OperationConsommateur opera : list) {
                    if (opera.getDesignation().equalsIgnoreCase(cp.getDesignations())) {
                        list.remove(opera);
                        break;
                    }
                }
                commandess.remove(cp);
                request.setAttribute("clients", personnelFacade.find(idcli));
                request.setAttribute("CommandeClient", "OK");
                request.setAttribute("trait", "OK");
                String action = "detaill";
                request.setAttribute("action", action);
                request.setAttribute("total", prixTotal);
                request.setAttribute("details", "detail");
                request.setAttribute("vue", vue);
                request.setAttribute("listepersonnel", listePersonnel);
                request.setAttribute("list", list);
                request.setAttribute("commande", commandess);
                request.getServletContext().getRequestDispatcher(redirect).forward(request, response);

            } else if (actions.equalsIgnoreCase("traiter")) {
                boolean b = false;
                Personnel p1 = personnelFacade.find(idcli);

                for (int i = 0; i < commandess.size(); i++) {
                    CommandePersonnel cp = commandess.get(i);

                    StockproduitMS mS = stockproduitMSFacade.findByDesignationByidMS(idMS, cp.getDesignations());
                    if (mS.getQuantite() < cp.getQuantite()) {
                        b = true;
                        break;
                    }
                    cp.setEtatCommande("traiter");
                    cp.setReception("non");
                    commandePersonnelFacade.edit(cp);
                    mS.setQuantite(mS.getQuantite() - cp.getQuantite());
                    mS.setPrixTotal(mS.getQuantite() * mS.getPrixUnitaire());
                    stockproduitMSFacade.edit(mS);
                    idP = cp.getIdPersonnel().getIdPersonnel();

                }
                if (!b) {

                    for (int i = 0; i < commandess.size(); i++) {
                        OperationConsommateur op = list.get(i);
                        op.setDate(date_du_jour.dateJour());
                        operationConsommateurFacade.create(op);

                        CategorieProduit cpp = categorieProduitFacade.findByTypeCategorie(op.getCategorie());
                        Butget butget = butgetFacade.findOnBudgetForService(op.getMagasin().getIdMagasin(), cpp.getIdCategorieProduit(), cpp.getTypeCategorie());
                        butget.setMontantRestant(butget.getMontantRestant() - op.getPrixTotal());
                        butgetFacade.edit(butget);
                    }

                    for (int i = 0; i < commandess.size(); i++) {
                        OperationConsommateur op = list.get(i);
                        CommandePersonnel cp = commandess.get(i);
                        Date d2 = new Date(cp.getDate().getTime());
                        boolean error = false;
                        // boolean errorA = false;
                        StatistiqueArticlesDirection direction = new StatistiqueArticlesDirection();
                        StatistiqueArticlesRegion articlesRegion = new StatistiqueArticlesRegion();
                        StatistiqueArticlesService articlesService = new StatistiqueArticlesService();
                        StatistiqueArticlesSite articlesSite = new StatistiqueArticlesSite();
                        StatistiqueArticlesPersonnel articlesPersonnel = new StatistiqueArticlesPersonnel();
                        StatistiqueArticlesMagasinMs articlesMagasinMs = new StatistiqueArticlesMagasinMs();

                        StatistiqueCategorieDirection categorieDirection = new StatistiqueCategorieDirection();
                        StatistiqueCategorieRegion categorieRegion = new StatistiqueCategorieRegion();
                        StatistiqueCategorieSite categorieSite = new StatistiqueCategorieSite();
                        StatistiqueCategorieService categorieService = new StatistiqueCategorieService();
                        StatistiqueCategoriePersonnel categoriePersonnel = new StatistiqueCategoriePersonnel();
                        StatistiqueCategorieMagasinMs categorieMagasinMs = new StatistiqueCategorieMagasinMs();
                        // String a = articleFacade.findAllByDesignation(cp.getDesignations()).getCategorie().getTypeCategorie();
                        try {
                            articlesRegion = statistiqueArticlesRegionFacade.findAllByArticlesByServiceAndOneDate(p1.getService().getSite().getRegion().getIdRegion(), op.getDesignation(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueArticleReGion(articlesRegion, error, op, d2, p1);

                        error = false;
                        try {
                            direction = statistiqueArticlesDirectionFacade.findAllByArticlesByServiceAndOneDate(p1.getService().getDirection().getIdDirection(), op.getDesignation(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueArticleDirection(direction, error, op, d2, p1);

                        error = false;
                        try {
                            articlesService = statistiqueArticlesServiceFacade.findAllByArticlesByServiceAndOneDate(p1.getService().getIdService(), op.getDesignation(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueArticleService(articlesService, error, op, d2, p1);

                        error = false;
                        try {
                            articlesSite = statistiqueArticlesSiteFacade.findAllByArticlesByServiceAndOneDate(p1.getService().getSite().getIdSite(), op.getDesignation(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueArticleSite(articlesSite, error, op, d2, p1);

                        error = false;
                        try {
                            articlesPersonnel = statistiqueArticlesPersonnelFacade.findAllByArticlesByServiceAndOneDate(p1.getIdPersonnel(), op.getDesignation(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueArticlPersonnel(articlesPersonnel, error, op, d2, p1);

                        error = false;
                        try {
                            articlesMagasinMs = statistiqueArticlesMagasinMsFacade.findAllByArticlesByMagasinAndOneDate(op.getMagasin().getIdMagasin(), op.getDesignation(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueArticleMagasinMS(articlesMagasinMs, error, op, d2, p1);

                        //Statistique Categorie For ALL
                        error = false;
                        try {
                            categorieRegion = statistiqueCategorieRegionFacade.findAllBycategorieByServiceAndOneDate(p1.getService().getSite().getRegion().getIdRegion(), op.getCategorie(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueCategorieReGion(categorieRegion, error, op, d2, p1);

                        error = false;
                        try {
                            categorieDirection = statistiqueCategorieDirectionFacade.findAllBycategorieByServiceAndOneDate(p1.getService().getDirection().getIdDirection(), op.getCategorie(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueCategorieDirection(categorieDirection, error, op, d2, p1);

                        error = false;
                        try {
                            categorieService = statistiqueCategorieServiceFacade.findAllBycategorieByServiceAndOneDate(p1.getService().getIdService(), op.getCategorie(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueCategorieService(categorieService, error, op, d2, p1);

                        error = false;
                        try {
                            categorieSite = statistiqueCategorieSiteFacade.findAllBycategorieByServiceAndOneDate(p1.getService().getSite().getIdSite(), op.getCategorie(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueCategorieSite(categorieSite, error, op, d2, p1);

                        error = false;
                        try {
                            categoriePersonnel = statistiqueCategoriePersonnelFacade.findAllBycategorieByServiceAndOneDate(p1.getIdPersonnel(), op.getCategorie(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueCategoriePersonnel(categoriePersonnel, error, op, d2, p1);

                        error = false;
                        try {
                            categorieMagasinMs = statistiqueCategorieMagasinMsFacade.findAllBycategorieBymagasinAndOneDate(op.getMagasin().getIdMagasin(), op.getCategorie(), d2);

                        } catch (Exception e) {
                            error = true;
                        }
                        StatistiqueCategorieMagasinMS(categorieMagasinMs, error, op, d2, p1);

                    }//fin du for 

                    for (int i = 0; i < listePersonnel.size(); i++) {
                        Personnel p2 = listePersonnel.get(i);
                        if (p2.getIdPersonnel() == idP) {
                            listePersonnel.remove(p2);
                        }
                    }

                    idP = 0;
                    /*//J'envoi un SMS Au personnel pour le prevenir que ca commande qui viens d'etre Traiter
                String sms = "Votre Commande Passer en date du : " + list.get(0).getDate() + " \nAu Magasin : " + list.get(0).getMagasin().getNomMagasin()
                        + " \nViens D'etre traiter par Mr." + p.getNomPrenom();
              
                try {
                    SendSMS.SendSMS(sms, p1.getTelephone());
                } catch (Exception e) {
                    System.err.println("une Erreur est suvenue pour l'envoi du SMS " + e.getMessage());
                }*/

                    Message message = new Message();
                    message.setMessage("commande traiter avec succès");
                    request.setAttribute("message", message);
                    request.setAttribute("listepersonnel", listePersonnel);
                    request.setAttribute("vue", vue);
                    list.clear();
                    commandess.clear();
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);

                    critique(request, session, idMS);
                } else {
                    Message message = new Message();
                    message.setMessage("Un Stock D'article est insuffisant Pour Traitrer la Commande De: " + p1.getNomPrenom());
                    request.setAttribute("message", message);
                    request.setAttribute("clients", personnelFacade.find(idcli));
                    request.setAttribute("CommandeClient", "OK");
                    request.setAttribute("trait", "OK");
                   
                    request.setAttribute("action", "detaill");
                    request.setAttribute("total", prixTotal);
                    request.setAttribute("details", "detail");
                    request.setAttribute("vue", vue);
                    request.setAttribute("listepersonnel", listePersonnel);
                    request.setAttribute("list", list);
                    request.setAttribute("commande", commandess);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);

                }
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    void StatistiqueArticleReGion(StatistiqueArticlesRegion articlesRegion, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            articlesRegion.setQuantite(articlesRegion.getQuantite() + op.getQuantite());
            articlesRegion.setPrixtotal(articlesRegion.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesRegionFacade.edit(articlesRegion);
        } else {
            articlesRegion.setArticles(op.getDesignation());
            articlesRegion.setDateSorti(d2);
            articlesRegion.setQuantite(op.getQuantite());
            articlesRegion.setRegion(p1.getService().getSite().getRegion());
            articlesRegion.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesRegionFacade.create(articlesRegion);
        }
    }

    void StatistiqueArticleSite(StatistiqueArticlesSite articlesSite, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            articlesSite.setQuantite(articlesSite.getQuantite() + op.getQuantite());
            articlesSite.setPrixtotal(articlesSite.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesSiteFacade.edit(articlesSite);
        } else {
            articlesSite.setArticles(op.getDesignation());
            articlesSite.setDateSorti(d2);
            articlesSite.setQuantite(op.getQuantite());
            articlesSite.setSite(p1.getService().getSite());
            articlesSite.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesSiteFacade.create(articlesSite);
        }
    }

    void StatistiqueArticleService(StatistiqueArticlesService articlesService, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            articlesService.setQuantite(articlesService.getQuantite() + op.getQuantite());
            articlesService.setPrixtotal(articlesService.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesServiceFacade.edit(articlesService);
        } else {
            articlesService.setArticles(op.getDesignation());
            articlesService.setDateSorti(d2);
            articlesService.setQuantite(op.getQuantite());
            articlesService.setService(p1.getService());
            articlesService.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesServiceFacade.create(articlesService);
        }
    }

    void StatistiqueArticleDirection(StatistiqueArticlesDirection direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesDirectionFacade.edit(direction);
        } else {
            direction.setArticles(op.getDesignation());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setDirection(p1.getService().getDirection());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesDirectionFacade.create(direction);
        }
    }

    void StatistiqueArticleMagasinMS(StatistiqueArticlesMagasinMs direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesMagasinMsFacade.edit(direction);
        } else {
            direction.setArticles(op.getDesignation());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setMagasin(op.getMagasin());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesMagasinMsFacade.create(direction);
        }
    }

    void StatistiqueArticlPersonnel(StatistiqueArticlesPersonnel direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesPersonnelFacade.edit(direction);
        } else {
            direction.setArticles(op.getDesignation());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setPersonnel(p1);
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesPersonnelFacade.create(direction);
        }
    }

    void StatistiqueCategorieReGion(StatistiqueCategorieRegion direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategorieRegionFacade.edit(direction);
        } else {
            direction.setCategorie(op.getCategorie());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setRegion(p1.getService().getSite().getRegion());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategorieRegionFacade.create(direction);
        }
    }

    void StatistiqueCategorieSite(StatistiqueCategorieSite direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategorieSiteFacade.edit(direction);
        } else {
            direction.setCategorie(op.getCategorie());
            direction.setDateSortie(d2);
            direction.setQuantite(op.getQuantite());
            direction.setSite(p1.getService().getSite());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategorieSiteFacade.create(direction);
        }
    }

    void StatistiqueCategorieService(StatistiqueCategorieService direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategorieServiceFacade.edit(direction);
        } else {
            direction.setCategorie(op.getCategorie());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setService(p1.getService());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategorieServiceFacade.create(direction);
        }
    }

    void StatistiqueCategorieDirection(StatistiqueCategorieDirection direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategorieDirectionFacade.edit(direction);
        } else {
            direction.setCategorie(op.getCategorie());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setDirection(p1.getService().getDirection());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategorieDirectionFacade.create(direction);
        }
    }

    void StatistiqueCategoriePersonnel(StatistiqueCategoriePersonnel direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategoriePersonnelFacade.edit(direction);
        } else {
            direction.setCategorie(op.getCategorie());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setPersonnel(p1);
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategoriePersonnelFacade.create(direction);
        }
    }

    void StatistiqueCategorieMagasinMS(StatistiqueCategorieMagasinMs direction, boolean error, OperationConsommateur op, Date d2, Personnel p1) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategorieMagasinMsFacade.edit(direction);
        } else {
            direction.setCategorie(op.getCategorie());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setMagasin(op.getMagasin());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategorieMagasinMsFacade.create(direction);
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
}

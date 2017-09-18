package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.CommandeMS;
import com.gciapplication.entity.MouvementProduits;
import com.gciapplication.entity.Notification;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StatistiqueArticlesMagasinMp;
import com.gciapplication.entity.StatistiqueArticlesMagasinMs;
import com.gciapplication.entity.StatistiqueCategoriMagasinMp;
import com.gciapplication.entity.StatistiqueCategorieMagasinMs;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.entity.TransfertStock;
import com.gciapplication.session.entity.CommandeMSFacadeLocal;
import com.gciapplication.session.entity.MouvementProduitsFacadeLocal;
import com.gciapplication.session.entity.NotificationFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesMagasinMpFacadeLocal;
import com.gciapplication.session.entity.StatistiqueArticlesMagasinMsFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategoriMagasinMpFacadeLocal;
import com.gciapplication.session.entity.StatistiqueCategorieMagasinMsFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.sql.Date;
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
@WebServlet(name = "transfert", urlPatterns = {"/transfert"})
public class transfert extends HttpServlet {

    @EJB
    private StatistiqueCategoriMagasinMpFacadeLocal statistiqueCategoriMagasinMpFacade;

    @EJB
    private StatistiqueArticlesMagasinMpFacadeLocal statistiqueArticlesMagasinMpFacade;

    @EJB
    private StatistiqueCategorieMagasinMsFacadeLocal statistiqueCategorieMagasinMsFacade;

    @EJB
    private StatistiqueArticlesMagasinMsFacadeLocal statistiqueArticlesMagasinMsFacade;

    @EJB
    private MouvementProduitsFacadeLocal mouvementProduitsFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;
    @EJB
    private CommandeMSFacadeLocal commandeMSFacade;

    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    List<TransfertStock> transfert;

    //  int idMS;
    private final String etat = "traiter";
    private final String rejet = "rejeter";
    private final String categori = "Fourniture Bureau", categoris = "Consommables Informatique";

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
            int idMS = (int) session.getAttribute("id_magasin");
            Personnel p = (Personnel) session.getAttribute("personnel");

            String action = request.getParameter("action");
            String vue = request.getParameter("vue");

            if (action.equalsIgnoreCase("lister")) {
                transfert = transfertStockFacade.findByVisaMSByidMS("encour", idMS);
                String vues;
                int niveau = 0;
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }

                if (niveau == 5) {
                    vues = "/WEB-INF/administrateur/main1.jsp";

                } else {
                    vues = "/WEB-INF/magasignerS/main.jsp";

                }
                if (transfert.isEmpty()) {
                    Message message = new Message("Aucun Transfert Pour le Moment");
                    request.setAttribute("message", message);

                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher(vues).forward(request, response);
                } else {
                    request.setAttribute("fournisseur", transfert.get(0).getMp().getDescription());
                    request.setAttribute("num", transfert.get(0).getIdTransfert());
                    request.setAttribute("transfert", transfert);
                    session.setAttribute("transferts", transfert);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher(vues).forward(request, response);
                }

            } else if (action.equalsIgnoreCase("valider")) {
                //je recuper les commandes non traiter du MS
                if (!transfert.isEmpty()) {
                    List< CommandeMS> listCMS = commandeMSFacade.findByEtatCommande(etat, idMS);
                    CommandeMS cms = new CommandeMS();
                    TransfertStock get = new TransfertStock();
                    Message message = null;
                    MouvementProduits mouvementProduitsMS = new MouvementProduits();
                    MouvementProduits mouvementProduitMP = new MouvementProduits();
                    boolean complet = false;
                    //je parcourt le transferts de stock envoyer par le MP 
                    for (int i = 0; i < transfert.size(); i++) {
                        get = transfert.get(i);
                        //je cherche les differents stock de produits concerner
                        StockproduitMS stock = stockproduitMSFacade.findByCodeProduitByidMS(idMS, get.getCodeProduit());
                        StockproduitMP stock1 = stockproduitMPFacade.findByCodeProduitByidMP(get.getMp().getIdMagasin(), get.getCodeProduit());
                        //je parcourt les commandes non traiter du MS pour verifier si le transfert est partiel ou complet
                        int st = stock.getQuantite();
                        for (int j = 0; j < listCMS.size(); j++) {
                            cms = listCMS.get(j);
                            if (get.getCodeProduit().equalsIgnoreCase(cms.getCodeProduit()) && get.getQuantite() == cms.getQuantiteCommande()) {
                                cms.setEtatCommande("OK");
                                cms.setDerniereLivraison(date_du_jour.dateJour());
                                get.setCommentaireMS("Tous est OK");
                                commandeMSFacade.edit(cms);
                                message = new Message("le Transfert à bien été effectuer");
                                complet = true;
                                break;
                            } else if (get.getCodeProduit().equalsIgnoreCase(cms.getCodeProduit()) && get.getQuantite() < cms.getQuantiteCommande()) {
                                cms.setQuantiteCommande(cms.getQuantiteCommande() - get.getQuantite());
                                cms.setEtatCommande("incomplet");
                                cms.setDerniereLivraison(date_du_jour.dateJour());
                                get.setCommentaireMS("transfert reçus partiellement!!");
                                message = new Message("le Transfert à été éffectuer Partiellement");
                                commandeMSFacade.edit(cms);
                                complet = false;
                                break;
                            }
                        }
                        //if (complet) {//transfet partiel a gerer ici par le else

//je met ajour les differents stock
                        stock.setQuantite(stock.getQuantite() + get.getQuantite());
                        stock.setPrixUnitaire(get.getPrixUnitaire());
                        stock.setPrixTotal(stock.getQuantite() * stock.getPrixUnitaire());
                        stock.setDateLivraison(get.getDate());

                        mouvementProduitsMS.setQuantiteApres(stock.getQuantite());
                        mouvementProduitsMS.setOperateur(p.getNomPrenom());
                        mouvementProduitsMS.setIdTransfert(new TransfertStock(get.getIdTransfert()));
                        mouvementProduitsMS.setIdMagasin(idMS);
                        mouvementProduitsMS.setQuantiteInit(st);
                        mouvementProduitsMS.setDateOperation(date_du_jour.dateJour());

                        mouvementProduitMP.setIdTransfert(new TransfertStock(get.getIdTransfert()));
                        mouvementProduitMP.setOperateur(get.getOperateur());
                        mouvementProduitMP.setDateOperation(date_du_jour.dateJour());
                        mouvementProduitMP.setQuantiteInit(stock1.getQuantite());
                        stock1.setQuantite(stock1.getQuantite() - get.getQuantite());
                        mouvementProduitMP.setQuantiteApres(stock1.getQuantite());
                        stock1.setPrixTotal(stock1.getQuantite() * stock1.getPrixUnitaire());
                        get.setVisaMS("OK");
                        //get.setCommentaireMS("RAS"); 
                        get.setEtat("OK");

                        transfertStockFacade.edit(get);
                        mouvementProduitsFacade.create(mouvementProduitsMS);
                        mouvementProduitsFacade.create(mouvementProduitMP);
                        stockproduitMSFacade.edit(stock);
                        stockproduitMPFacade.edit(stock1);

                        StatistiqueCategorieMagasinMs categorieMagasinMs = new StatistiqueCategorieMagasinMs();
                        StatistiqueArticlesMagasinMs articlesMagasinMs = new StatistiqueArticlesMagasinMs();
                        StatistiqueCategoriMagasinMp categorieMagasinMp = new StatistiqueCategoriMagasinMp();
                        StatistiqueArticlesMagasinMp articlesMagasinMp = new StatistiqueArticlesMagasinMp();
                        Date d2 = new Date(get.getDate().getTime());
                        complet = false;
                        try {
                            categorieMagasinMs = statistiqueCategorieMagasinMsFacade.findAllBycategorieBymagasinAndOneDate(get.getMs().getIdMagasin(), get.getTypeProduit(), d2);

                        } catch (Exception e) {
                            complet = true;
                        }
                        StatistiqueCategorieMagasinMS(categorieMagasinMs, complet, get, d2);

                        complet = false;
                        try {
                            articlesMagasinMs = statistiqueArticlesMagasinMsFacade.findAllByArticlesByMagasinAndOneDate(get.getMs().getIdMagasin(), get.getDesignation(), d2);

                        } catch (Exception e) {
                            complet = true;
                        }
                        StatistiqueArticleMagasinMS(articlesMagasinMs, complet, get, d2);
                        //statistique MP
                        complet = false;
                        try {
                            categorieMagasinMp = statistiqueCategoriMagasinMpFacade.findAllBycategorieBymagasinAndOneDate(get.getMp().getIdMagasin(), get.getTypeProduit(), d2);

                        } catch (Exception e) {
                            complet = true;
                        }
                        StatistiqueCategorieMagasinMP(categorieMagasinMp, complet, get, d2);

                        complet = false;
                        try {
                            articlesMagasinMp = statistiqueArticlesMagasinMpFacade.findAllByArticlesByMagasinAndOneDate(get.getMp().getIdMagasin(), get.getDesignation(), d2);

                        } catch (Exception e) {
                            complet = true;
                        }
                        StatistiqueArticleMagasinMP(articlesMagasinMp, complet, get, d2);

                    }//fin du parcour des transferts
                    critique(request, session, idMS);
                    transfert.clear();
                    listCMS.clear();

                    request.setAttribute("message", message);

                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                } else {
                    Message message = new Message("Aucun Tranfert pour L'instant");
                    request.setAttribute("message", message);

                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                }
            } else if (action.equalsIgnoreCase("validerOn")) {
                String codeOn = request.getParameter("code");
                int idTrans = Integer.parseInt(request.getParameter("id"));

                MouvementProduits mouvementProduits = new MouvementProduits();
                MouvementProduits mouvementProduitMP = new MouvementProduits();
                List< CommandeMS> listCMSS = commandeMSFacade.findByEtatCommande(etat, idMS);
                CommandeMS cm = new CommandeMS();
                for (int j = 0; j < listCMSS.size(); j++) {
                    CommandeMS cms = listCMSS.get(j);
                    if (cms.getCodeProduit().equalsIgnoreCase(codeOn)) {
                        cm = cms;
                        break;
                    }

                }

                TransfertStock ts = transfertStockFacade.find(idTrans);

                if (cm.getQuantiteCommande() == ts.getQuantite()) {
                    cm.setEtatCommande("OK");
                    ts.setVisaMS("OK");
                    ts.setEtat("OK");
                    commandeMSFacade.edit(cm);

                    StockproduitMS stock = stockproduitMSFacade.findByCodeProduitByidMS(idMS, ts.getCodeProduit());
                    StockproduitMP stock1 = stockproduitMPFacade.findByCodeProduitByidMP(ts.getMp().getIdMagasin(), ts.getCodeProduit());

                    mouvementProduits.setQuantiteInit(stock.getQuantite());
                    mouvementProduitMP.setQuantiteInit(stock1.getQuantite());

                    //  ts.setStockinit(stock.getQuantite());
                    stock1.setQuantite(stock1.getQuantite() - ts.getQuantite());
                    stock1.setPrixTotal(stock1.getQuantite() * stock1.getPrixUnitaire());
                    stockproduitMPFacade.edit(stock1);

                    stock.setQuantite(stock.getQuantite() + ts.getQuantite());

                    mouvementProduits.setQuantiteApres(stock.getQuantite());
                    mouvementProduitMP.setQuantiteApres(stock1.getQuantite());
                    // ts.setStockapres(stock.getQuantite());
                    stock.setPrixTotal(stock.getQuantite() * stock.getPrixUnitaire());
                    stockproduitMSFacade.edit(stock);

                    mouvementProduits.setOperateur(p.getNomPrenom());
                    mouvementProduits.setIdTransfert(new TransfertStock(idTrans));
                    mouvementProduits.setIdMagasin(ts.getMs().getIdMagasin());
                    mouvementProduits.setDateOperation(date_du_jour.dateJour());

                    mouvementProduitMP.setIdTransfert(new TransfertStock(idTrans));
                    mouvementProduitMP.setOperateur(ts.getOperateur());
                    mouvementProduitMP.setIdMagasin(ts.getMp().getIdMagasin());
                    mouvementProduitMP.setDateOperation(date_du_jour.dateJour());
                    mouvementProduitsFacade.create(mouvementProduits);
                    mouvementProduitsFacade.create(mouvementProduitMP);

                } else if (ts.getQuantite() < cm.getQuantiteCommande()) {

                    StockproduitMS stock = stockproduitMSFacade.findByCodeProduitByidMS(idMS, ts.getCodeProduit());
                    StockproduitMP stock1 = stockproduitMPFacade.findByCodeProduitByidMP(ts.getMp().getIdMagasin(), ts.getCodeProduit());

                    // ts.setStockinit(stock.getQuantite());
                    mouvementProduits.setQuantiteInit(stock.getQuantite());
                    mouvementProduitMP.setQuantiteInit(stock1.getQuantite());

                    stock1.setQuantite(stock1.getQuantite() - ts.getQuantite());
                    stock1.setPrixTotal(stock1.getQuantite() * stock1.getPrixUnitaire());
                    stockproduitMPFacade.edit(stock1);

                    stock.setQuantite(stock.getQuantite() + ts.getQuantite());

                    // ts.setStockapres(stock.getQuantite());
                    mouvementProduits.setQuantiteApres(stock.getQuantite());
                    mouvementProduitMP.setQuantiteApres(stock1.getQuantite());

                    stock.setPrixTotal(stock.getQuantite() * stock.getPrixUnitaire());
                    // stockproduitMSFacade.edit(stock);

                    mouvementProduits.setOperateur(p.getNomPrenom());
                    mouvementProduits.setIdTransfert(new TransfertStock(ts.getIdTransfert()));
                    mouvementProduits.setIdMagasin(ts.getMs().getIdMagasin());
                    mouvementProduits.setDateOperation(date_du_jour.dateJour());

                    mouvementProduitMP.setIdTransfert(new TransfertStock(ts.getIdTransfert()));
                    mouvementProduitMP.setOperateur(ts.getOperateur());
                    mouvementProduitMP.setIdMagasin(ts.getMp().getIdMagasin());
                    mouvementProduitMP.setDateOperation(date_du_jour.dateJour());

                    cm.setQuantiteCommande(cm.getQuantiteCommande() - ts.getQuantite());
                    cm.setQuantiteEnStock(stock.getQuantite());
                    cm.setDerniereLivraison(ts.getDate());
                    cm.setEtatCommande("incomplet");
                    commandeMSFacade.edit(cm);
                    mouvementProduitsFacade.create(mouvementProduits);
                    mouvementProduitsFacade.create(mouvementProduitMP);
                    ts.setVisaMS("OK");
                    ts.setEtat("OK");
                    Message message = new Message("le Transfert à été effectuer partiellement");
                    request.setAttribute("message", message);

                }
                StatistiqueCategorieMagasinMs categorieMagasinMs = new StatistiqueCategorieMagasinMs();
                StatistiqueArticlesMagasinMs articlesMagasinMs = new StatistiqueArticlesMagasinMs();
                StatistiqueCategoriMagasinMp categorieMagasinMp = new StatistiqueCategoriMagasinMp();
                StatistiqueArticlesMagasinMp articlesMagasinMp = new StatistiqueArticlesMagasinMp();
                Date d2 = new Date(ts.getDate().getTime());
                boolean complet = false;
                try {
                    categorieMagasinMs = statistiqueCategorieMagasinMsFacade.findAllBycategorieBymagasinAndOneDate(ts.getMs().getIdMagasin(), ts.getTypeProduit(), d2);

                } catch (Exception e) {
                    complet = true;
                }
                StatistiqueCategorieMagasinMS(categorieMagasinMs, complet, ts, d2);

                complet = false;
                try {
                    articlesMagasinMs = statistiqueArticlesMagasinMsFacade.findAllByArticlesByMagasinAndOneDate(ts.getMs().getIdMagasin(), ts.getDesignation(), d2);

                } catch (Exception e) {
                    complet = true;
                }
                StatistiqueArticleMagasinMS(articlesMagasinMs, complet, ts, d2);
                //statistique MP
                complet = false;
                try {
                    categorieMagasinMp = statistiqueCategoriMagasinMpFacade.findAllBycategorieBymagasinAndOneDate(ts.getMp().getIdMagasin(), ts.getTypeProduit(), d2);

                } catch (Exception e) {
                    complet = true;
                }
                StatistiqueCategorieMagasinMP(categorieMagasinMp, complet, ts, d2);

                complet = false;
                try {
                    articlesMagasinMp = statistiqueArticlesMagasinMpFacade.findAllByArticlesByMagasinAndOneDate(ts.getMp().getIdMagasin(), ts.getDesignation(), d2);

                } catch (Exception e) {
                    complet = true;
                }
                StatistiqueArticleMagasinMP(articlesMagasinMp, complet, ts, d2);

                critique(request, session, idMS);

                transfertStockFacade.edit(ts);

                transfert.remove(ts);

                request.setAttribute("transfert", transfert);
                session.setAttribute("transferts", transfert);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("refuser")) {
                int idd = Integer.parseInt(request.getParameter("id"));
                String raison = request.getParameter("raison");
                Notification notification = new Notification();

                TransfertStock ts = transfertStockFacade.find(idd);

                CommandeMS cmss = commandeMSFacade.findByCodeByEtat(ts.getCodeProduit(), idMS, "traiter");
                ts.setCommentaireMS(raison);
                ts.setVisaMS("refuser");
                ts.setEtat("rejeter");
                transfertStockFacade.edit(ts);
                transfert.remove(ts);

                cmss.setEtatCommande("refuser");
                commandeMSFacade.edit(cmss);

                //on envoi un notification pour informer le MP que le transferf a ete rejeter par le MS
                notification.setMessage("Le Transfert Numeros: " + ts.getIdTransfert() + " Passer en Date du " + ts.getDate().toGMTString() + "\n"
                        + " Concernant le " + ts.getTypeProduit() + ": " + ts.getDesignation() + " à été Rejeter par le "
                        + " Magasin : " + ts.getMs().getNomMagasin() + " pour des raisons de : " + raison);
                notification.setVue(0);
                notification.setExpediteur(idMS);
                notification.setRecepteur(ts.getMp().getIdMagasin());
                notification.setDate(date_du_jour.dateJour());
                notificationFacade.create(notification);

                request.setAttribute("transfert", transfert);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);

            }
            if (action.equalsIgnoreCase("refus")) {//a rectifier
                String code = request.getParameter("code");
                int idd = Integer.parseInt(request.getParameter("id"));

                TransfertStock ts = transfertStockFacade.find(idd);
                for (TransfertStock transfertStock : transfert) {
                    if (transfertStock.getCodeProduit().equalsIgnoreCase(code)) {
                        transfert.remove(transfertStock);
                        break;
                    }
                }
                request.setAttribute("transf", ts);
                String mo = "refuser";
                request.setAttribute("modifier", mo);
                request.setAttribute("transfert", transfert);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    void StatistiqueArticleMagasinMS(StatistiqueArticlesMagasinMs direction, boolean error, TransfertStock op, Date d2) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesMagasinMsFacade.edit(direction);
        } else {
            direction.setArticles(op.getDesignation());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setMagasin(op.getMs());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesMagasinMsFacade.create(direction);
        }
    }

    void StatistiqueCategorieMagasinMS(StatistiqueCategorieMagasinMs direction, boolean error, TransfertStock op, Date d2) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategorieMagasinMsFacade.edit(direction);
        } else {
            direction.setCategorie(op.getTypeProduit());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setMagasin(op.getMs());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategorieMagasinMsFacade.create(direction);
        }
    }

    void StatistiqueArticleMagasinMP(StatistiqueArticlesMagasinMp direction, boolean error, TransfertStock op, Date d2) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueArticlesMagasinMpFacade.edit(direction);
        } else {
            direction.setArticles(op.getDesignation());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setMagasin(op.getMp());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueArticlesMagasinMpFacade.create(direction);
        }
    }

    void StatistiqueCategorieMagasinMP(StatistiqueCategoriMagasinMp direction, boolean error, TransfertStock op, Date d2) {
        if (!error) {
            direction.setQuantite(direction.getQuantite() + op.getQuantite());
            direction.setPrixtotal(direction.getPrixtotal() + op.getPrixTotal());
            statistiqueCategoriMagasinMpFacade.edit(direction);
        } else {
            direction.setCategorie(op.getTypeProduit());
            direction.setDateSorti(d2);
            direction.setQuantite(op.getQuantite());
            direction.setMagasin(op.getMp());
            direction.setPrixtotal(op.getPrixTotal());
            statistiqueCategoriMagasinMpFacade.create(direction);
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

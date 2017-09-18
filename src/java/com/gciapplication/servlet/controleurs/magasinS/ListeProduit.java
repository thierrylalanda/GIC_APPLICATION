package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.Butget;
import com.gciapplication.entity.EcartinventaireMS;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.MouvementProduits;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.entity.TransfertStock;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.EcartinventaireMSFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.MouvementProduitsFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gciapplication.session.entity.TransfertStockFacadeLocal;
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
import org.json.JSONArray;

@WebServlet(name = "ListeProduit", urlPatterns = "/listeproduit")
public class ListeProduit extends HttpServlet {

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private MouvementProduitsFacadeLocal mouvementProduitsFacade;

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private EcartinventaireMSFacadeLocal ecartinventaireMSFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    //List<CategorieproduitMS> categorie;
    String action = "";
    List<OperationConsommateur> lists = new ArrayList<>();
    List<TransfertStock> listetransfert = new ArrayList<>();
    List<EcartinventaireMS> listEcart = new ArrayList<>();
    List<MouvementProduits> listMouvement = new ArrayList<>();

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
            int idMS;

            if (session.getAttribute("id_magasin") == null) {
                idMS = Integer.parseInt(request.getParameter("id_magasin"));
                listEcart = ecartinventaireMSFacade.findAllByIdMS(idMS);
                request.setAttribute("historique", listEcart);

            } else {
                idMS = (int) session.getAttribute("id_magasin");
            }

            String vue = request.getParameter("vue");
            action = request.getParameter("action");

            List<StockproduitMS> sms = stockproduitMSFacade.ProduitCritique(idMS);
            List<StockproduitMS> smss = stockproduitMSFacade.ProduitWarmin(idMS);
            if (!sms.isEmpty()) {
                String etat = "danger";
                request.setAttribute("etat", etat);
                session.removeAttribute("tail");
                session.setAttribute("tail", sms.size());
            }
            if (!smss.isEmpty()) {
                String etat1 = "warning";
                request.setAttribute("etat1", etat1);
                session.removeAttribute("taill");
                session.setAttribute("taill", smss.size());
            }

            MagasinSecondaire secondaire = magasinSecondaireFacade.find(idMS);
            List<StockproduitMS> l = stockproduitMSFacade.findStockMP(idMS);
             request.setAttribute("stockMS", l);
            request.setAttribute("idMS", idMS);

            if (action.equalsIgnoreCase("all")) {

                // categorie = secondaire.getCategorieproduitMSList();
                //request.setAttribute("categorie", categorie);
               
                String all = "tous";
                request.setAttribute("all", all);
                String actions = "all";
                request.setAttribute("actions", actions);
                String etat = "danger";
                request.setAttribute("etat", etat);
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
//Etat du Magasin MS a une Periode donnee

            } else if (action.equalsIgnoreCase("etatButgetMS")) {
                JSONArray butgetMS = new JSONArray();
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                List<Butget> BudgetMS = butgetFacade.findAllBudgetByidMS(ms.getIdMagasin());
                butgetMS.put(BudgetMS.toString());
                System.out.println(BudgetMS);
                response.setContentType("application/json");
                response.getWriter().print(butgetMS);

            } else if (action.equalsIgnoreCase("date")) {
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");

                double total = 0;
                lists.clear();
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                lists = operationConsommateurFacade.findConsommationMSByPeriode(date_du_jour.dateConvert(date1), date_du_jour.dateConvert(date2), ms.getIdMagasin());
                for (int i = 0; i < lists.size(); i++) {
                    OperationConsommateur oc = lists.get(i);
                    total += oc.getPrixTotal();
                }

                request.setAttribute("magasin", ms);
                // request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("magasin", ms);
                request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("personnel", ms.getAffectationmagasinSList().get(0).getPersonnel());

                request.setAttribute("sorti", "TWO");
                request.setAttribute("active", "OK");

                request.setAttribute("total", total);
                request.setAttribute("actions", "periode");
                request.setAttribute("operation", lists);
                request.setAttribute("vue", vue);

                if (request.getParameter("entrerS") != null) {
                    request.setAttribute("entrerS", "OK");
                } else {
                    request.setAttribute("entrerS", "ONE");
                }

                int niveau = 0;
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                switch (niveau) {
                    case 5:
                        request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                        break;
                    case 4:
                        if (request.getParameter("etat") != null) {
                            request.setAttribute("etat", "OK");
                        } else {
                            request.setAttribute("stock", "OK");
                        }
                        request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                        break;
                    default:
                        request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                        break;
                }

            } else if (action.equalsIgnoreCase("sorti")) {
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("code")) {
                String designation = request.getParameter("designation");
                StockproduitMS mS = stockproduitMSFacade.findByDesignationByidMS(secondaire.getIdMagasin(), designation);
                String code = mS.getCodeProduit();
                response.setContentType("application/text");
                response.getWriter().print(code);

            } else if (action.equalsIgnoreCase("one_produit")) {//sans designation
                String designation = request.getParameter("designation");
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");

                if (!date1.isEmpty() && !date2.isEmpty()) {
                   // Date d = date_du_jour.dateConvert(date1);
                    //Date dd = date_du_jour.dateConvert(date2);
                    lists.clear();
                    listetransfert.clear();
                    listMouvement.clear();
                    lists = operationConsommateurFacade.findConsommationMSByPeriode(date_du_jour.dateConvert(date1), date_du_jour.dateConvert(date2), idMS, designation);
                    listetransfert = transfertStockFacade.findByPeriodeMS(date_du_jour.dateConvert(date1), date_du_jour.dateConvert(date2), idMS, designation);
                    for (TransfertStock transf : listetransfert) {
                        List<MouvementProduits> listMouvementT = mouvementProduitsFacade.findAllByIdMagasin(idMS, transf.getIdTransfert());
                        for (MouvementProduits mouvementProduits : listMouvementT) {
                            listMouvement.add(mouvementProduits);
                        }

                    }

                } else {

                    lists.clear();
                    listMouvement.clear();
                    listetransfert.clear();
                    lists = operationConsommateurFacade.findAllByIdMSAndDesignation(designation, idMS);
                    listetransfert = transfertStockFacade.findByDesignation(designation, idMS);
                    for (TransfertStock transf : listetransfert) {
                        List<MouvementProduits> listMouvementT = mouvementProduitsFacade.findAllByIdMagasin(idMS, transf.getIdTransfert());
                        for (MouvementProduits mouvementProduits : listMouvementT) {
                            listMouvement.add(mouvementProduits);
                        }

                    }

                }
                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);

                request.setAttribute("magasin", ms);
                request.setAttribute("categorie", ms.getCategorieproduitMSList());

                request.setAttribute("activeInventaire", "OK");

                request.setAttribute("table", "OK");
                String info = "periode";
                request.setAttribute("info", info);
                request.setAttribute("designation", designation);
                request.setAttribute("operation", lists);
                request.setAttribute("listetransfert", listMouvement);
                request.setAttribute("vue", vue);

                if (request.getParameter("entrerS") != null) {
                    request.setAttribute("entrerSS", "ONE");

                } else {
                    request.setAttribute("Sorti", "OK");
                }

                int niveau = 0;
                if (request.getParameter("niveau") != null) {
                    if (request.getParameter("entrer") != null) {
                        request.setAttribute("sorti", "ONE");
                    } else {
                        request.setAttribute("sorti", "OK");
                    }
                    if (request.getParameter("entrerS") != null) {
                        request.setAttribute("entrerSS", "ONE");

                    } else {
                        request.setAttribute("Sorti", "OK");
                    }
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                switch (niveau) {
                    case 5:
                        request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                        break;
                    case 4:
                        if (request.getParameter("etat") != null) {
                            request.setAttribute("etat", "OK");
                        } else if (request.getParameter("mouv") != null) {
                            request.setAttribute("historique", "OK");
                        } else {
                            request.setAttribute("stock", "OK");
                        }
                        request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                        break;
                    default:
                        request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                        break;
                }
            } else if (action.equalsIgnoreCase("getInventaireMagSAdmin")) {
                lists.clear();
                listetransfert.clear();
                listMouvement.clear();
                lists = operationConsommateurFacade.findAllByIdMS(idMS);
                listetransfert = transfertStockFacade.findByVisaMSByidMS("OK", idMS);

                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);

                for (TransfertStock transf : listetransfert) {
                    List<MouvementProduits> listMouvementT = mouvementProduitsFacade.findAllByIdMagasin(idMS, transf.getIdTransfert());
                    for (MouvementProduits mouvementProduits : listMouvementT) {
                        listMouvement.add(mouvementProduits);
                    }
                }

                request.setAttribute("operation", lists);
                request.setAttribute("listetransfert", listMouvement);
                request.setAttribute("activeInventaire", "OK");

                request.setAttribute("magasin", ms);
                request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("personnel", ms.getAffectationmagasinSList().get(0).getPersonnel());
                request.setAttribute("vue", vue);
                request.setAttribute("table", "OK");
                int niveau = 0;
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                if (niveau == 5) {
                    if (request.getParameter("entrer") != null) {
                        request.setAttribute("sorti", "ONE");
                    } else {
                        request.setAttribute("sorti", "OK");
                    }
                    request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);

                } else {
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);

                }

            } else {
                lists.clear();
                listetransfert.clear();
                listMouvement.clear();
                lists = operationConsommateurFacade.findAllByIdMS(idMS);
                listetransfert = transfertStockFacade.findByVisaMSByidMS("OK", idMS);

                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);

                for (TransfertStock transf : listetransfert) {
                    List<MouvementProduits> listMouvementT = mouvementProduitsFacade.findAllByIdMagasin(idMS, transf.getIdTransfert());
                    for (MouvementProduits mouvementProduits : listMouvementT) {
                        listMouvement.add(mouvementProduits);
                    }
                }

                // request.setAttribute("categorie", ms.getCategorieproduitMSList());
                request.setAttribute("magasin", ms);
                request.setAttribute("categorie", ms.getCategorieproduitMSList());
                if (!ms.getAffectationmagasinSList().isEmpty()) {
                    request.setAttribute("personnel", ms.getAffectationmagasinSList().get(0).getPersonnel());
                }

                request.setAttribute("operation", lists);
                request.setAttribute("listetransfert", listMouvement);
                request.setAttribute("actions", "periode");
                request.setAttribute("sorti", "TWO");
                request.setAttribute("info", "periode");
                request.setAttribute("vue", vue);
                request.setAttribute("active", "OK");
                if (request.getParameter("entrerS") != null) {
                    request.setAttribute("entrerSS", "ONE");

                } else {
                    request.setAttribute("Sorti", "OK");
                }
                int niveau = 0;
                if (request.getParameter("niveau") != null) {
                    niveau = Integer.parseInt(request.getParameter("niveau"));
                }
                switch (niveau) {
                    case 5:
                        request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
                        break;
                    case 4:
                        request.setAttribute("idMS", Integer.parseInt(request.getParameter("id_magasin")));
                        if (request.getParameter("etat") != null) {
                            request.setAttribute("etat", "OK");
                        } else if (request.getParameter("mouv") != null) {
                            request.setAttribute("historique", "OK");
                        } else {
                            request.setAttribute("stock", "OK");
                        }
                        request.getServletContext().getRequestDispatcher("/WEB-INF/controleurs/main.jsp").forward(request, response);
                        break;
                    default:
                        request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
                        break;
                }
            }

        } else {
            response.sendRedirect("login.jsp");

        }
    }
}

package com.gciapplication.servlet.controleurs.magasinP;

import com.gciapplication.entity.Appariel;
import com.gciapplication.entity.Article;
import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CommandePersonnel;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.entity.StockproduitMP;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CommandePersonnelFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.StockproduitMPFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
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

@WebServlet(name = "commandeSimple", urlPatterns = {"/commandeSimple"})
public class commandeSimple extends HttpServlet {
    
    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;
    
    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;
    
    @EJB
    private ArticleFacadeLocal articleFacade;
    
    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;
    
    @EJB
    private LoginFacadeLocal loginFacade;
    
    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;
    
    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;
    
    @EJB
    private ButgetFacadeLocal butgetFacade;
    
    @EJB
    private StockproduitMPFacadeLocal stockproduitMPFacade;
    
    @EJB
    private CommandePersonnelFacadeLocal commandePersonnelFacade;
    
    ArrayList<CommandePersonnel> listeCommandes = new ArrayList<>();
    ArrayList<OperationConsommateur> listeOperations = new ArrayList<>();
    
    String save = "", edit = "ok", nom_client = "";
    int fois = 0;
    int magasinS;
    
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
            //  int id_magasigner = (int) session.getAttribute("id_magasignerP");
            int idMP = (int) session.getAttribute("id_magasinP");
            Personnel p = (Personnel) session.getAttribute("personnel");
            nom_client = p.getNomPrenom();
            String action = request.getParameter("action");
            String vue = request.getParameter("vue");
            
            if (action.equalsIgnoreCase("ajouterP")) {
                if (fois == 0) {
                    magasinS = Integer.parseInt(request.getParameter("id_magasinS"));
                    fois = 1;
                }
                request.setAttribute("magasinPP", magasinSecondaireFacade.find(magasinS));
                MagasinPrincipal magasinP = magasinPrincipalFacade.find(idMP);
                String appariel = request.getParameter("appariel");
                int designation = Integer.parseInt(request.getParameter("designation"));
                int quantites = Integer.parseInt(request.getParameter("quantite"));
                
                Article a = articleFacade.find(designation);
                boolean get = false;
                StockproduitMS stock = new StockproduitMS();
                try {
                    stock = stockproduitMSFacade.findByDesignationByidMS(magasinS, a.getDesignation());
                } catch (Exception e) {
                    get = true;
                }
                
                if (!get) {
                    CommandePersonnel cp = new CommandePersonnel();
                    
                    OperationConsommateur oc = new OperationConsommateur();
                    
                    cp.setCodeAppareil(new Appariel(appariel));
                    // cp.setIdCommande((int) (Math.random() * (10000000)));
                    cp.setDate(date_du_jour.dateJour());
                    cp.setDesignations(stock.getDesignation());
                    cp.setEtatCommande("encour de traitement");
                    cp.setQuantite(quantites);
                    cp.setIdPersonnel(new Personnel(p.getIdPersonnel()));
                    cp.setIdMS(new MagasinSecondaire(magasinS));
                    
                    oc.setCodeProduit(stock.getCodeProduit());
                    oc.setAppariel(cp.getCodeAppareil().getNumeroParck());
                    oc.setCategorie(stock.getCategorie().getNomCategorie());
                    oc.setDate(date_du_jour.dateJour());
                    oc.setDesignation(stock.getDesignation());
                    oc.setPersonnel(new Personnel(p.getIdPersonnel()));
                    oc.setOperateur(p.getNomPrenom());
                    oc.setMagasin(new MagasinSecondaire(magasinS));
                    oc.setQuantite(quantites);
                    oc.setPrix(stock.getPrixUnitaire());
                    oc.setPrixTotal(stock.getPrixUnitaire() * cp.getQuantite());
                    
                    if (cp.getQuantite() <= stock.getQuantite()) {
                        
                        listeCommandes.add(cp);
                        listeOperations.add(oc);
                        
                        request.setAttribute("produitMS", magasinP.getCategorieproduitMPList());
                        request.setAttribute("edit", edit);
                        request.setAttribute("nom_client", nom_client);
                        request.setAttribute("vue", vue);
                        request.setAttribute("listeoperation", listeOperations);
                        request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                        
                    } else {
                        
                        request.setAttribute("produitMS", magasinP.getCategorieproduitMPList());
                        request.setAttribute("edit", edit);
                        request.setAttribute("nom_client", nom_client);
                        request.setAttribute("vue", vue);
                        request.setAttribute("listecommande", listeCommandes);
                        request.setAttribute("listeoperation", listeOperations);
                        Message message = new Message("Une Erreur est survenue le Stock des " + cp.getDesignations() + " est insuffissant veuillez revoir la Quantite que vous êtes entrain de commander");
                        request.setAttribute("message", message);
                        
                        request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                    }
                } else {
                    Message message = new Message("Une Erreur est survenue le Magasin: " + magasinPrincipalFacade.find(idMP).getNomMagasin() + " ne Possède pas cet Article");
                    request.setAttribute("message", message);
                    request.setAttribute("produitMS", magasinP.getCategorieproduitMPList());
                    request.setAttribute("edit", edit);
                    request.setAttribute("nom_client", nom_client);
                    request.setAttribute("vue", vue);
                    request.setAttribute("listecommande", listeCommandes);
                    request.setAttribute("listeoperation", listeOperations);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
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
                request.setAttribute("magasinPP", magasinSecondaireFacade.find(magasinS));
                request.setAttribute("edit", edit);
                request.setAttribute("nom_client", nom_client);
                request.setAttribute("vue", vue);
                request.setAttribute("listeoperation", listeOperations);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                
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
                response.getWriter().print(quantite);
                
            } else if (action.equalsIgnoreCase("saveClient")) {
                request.setAttribute("magasinPP", magasinSecondaireFacade.find(magasinS));
                boolean iserror = false;
                for (int i = 0; i < listeCommandes.size(); i++) {
                    CommandePersonnel cp = listeCommandes.get(i);
                    cp.setEtatCommande("encour de traitement");
                    try {
                        commandePersonnelFacade.create(cp);
                    } catch (Exception e) {
                        Message message = new Message("une erreur est survenue" + e.getMessage());
                        request.setAttribute("message", message);
                        request.setAttribute("vue", vue);
                        iserror = true;
                    }
                    
                }
                if (!iserror) {
                    listeCommandes.clear();
                    listeOperations.clear();
                    // nom_client = "";

                    critiqueMP(request, session, idMP);
                    
                    Message message = new Message("Votre Commande à bien été Traiter");
                    request.setAttribute("message", message);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                    
                } else {
                    Message message = new Message("Commande Non Enregistrer Contacter L'Administrateur");
                    request.setAttribute("message", message);
                    request.setAttribute("vue", vue);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
                    
                }
                
            } else {
                
                p.setLoginList(loginFacade.findBypersonnelID(p.getIdPersonnel()));
                request.setAttribute("personnel", p);
                request.setAttribute("all", "yes");
                request.setAttribute("vue", vue);
                request.getServletContext().getRequestDispatcher("/WEB-INF/magasignierP/main.jsp").forward(request, response);
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

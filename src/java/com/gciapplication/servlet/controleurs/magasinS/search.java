package com.gciapplication.servlet.controleurs.magasinS;

import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Personnel;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Search", urlPatterns = "/search")
public class search extends HttpServlet {

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private OperationConsommateurFacadeLocal operationConsommateurFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

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
            String admin = "non", redirec;
            if (session.getAttribute("id_magasin") != null) {
                idMS = (int) session.getAttribute("id_magasin");
            } else {
                admin = request.getParameter("admin");
                idMS = Integer.parseInt(request.getParameter("id_magasin"));
            }
            if (admin.equalsIgnoreCase("yes")) {
                redirec = "/WEB-INF/administrateur/main1.jsp";
            } else {
                redirec = "/WEB-INF/magasignerS/main.jsp";
            }
            //   Personnel p=(Personnel) session.getAttribute("personnel");
            double total = 0;

            String ref = request.getParameter("nom");

            String vue = request.getParameter("vue");
            String messages = "one";
            boolean error = false ;
            if (!ref.isEmpty()) {
                Personnel p = (Personnel) session.getAttribute("personnel");
                String region1 = "Aucune";
                try {
                    region1 = p.getService().getSite().getRegion().getNomRegion();
                } catch (Exception e) {
                    error = true;
                }

                MagasinSecondaire ms = magasinSecondaireFacade.find(idMS);
                String magasin = ms.getDescription();
                Personnel personnel = personnelFacade.findByNomPrenom(ref);

                String region = personnel.getService().getSite().getRegion().getNomRegion();

                if (region.equalsIgnoreCase(region1) || admin.equalsIgnoreCase("yes")) {

                    List<OperationConsommateur> cps = operationConsommateurFacade.findAllByIdPersonnel(personnel.getIdPersonnel());
                    for (int i = 0; i < cps.size(); i++) {

                        total = cps.get(i).getPrixTotal() + total;
                    }
                    if (operationConsommateurFacade.findAllByIdPersonnel(personnel.getIdPersonnel()).isEmpty()) {
                    Message message = new Message(personnel.getNomPrenom()+" n'a effectué Aucune operation  sur cette période");
                        request.setAttribute("message", message);
                    }
                    request.setAttribute("client", personnel);

                    request.setAttribute("active", "OK");
                    request.setAttribute("rec", "ok");
                    request.setAttribute("sorti", "ONE");
                    request.setAttribute("infoPerso", "OK");
                    request.setAttribute("info", "Off");

                    request.setAttribute("magasin", ms);
                    request.setAttribute("categorie", categorieproduitMSFacade.findCatByidMS(ms.getIdMagasin()));
                    request.setAttribute("fournisseur", magasin);
                    request.setAttribute("personnel", ms.getAffectationmagasinSList().get(0).getPersonnel());
                    request.setAttribute("cps", cps);
                    request.setAttribute("operation", cps);
                    request.setAttribute("total", total);
                    request.setAttribute("actions", "ok");
                    request.setAttribute("vue", vue);

                    request.getServletContext().getRequestDispatcher(redirec).forward(request, response);
                } else {
                    request.setAttribute("info", "Off");
                    request.setAttribute("sorti", "ONE");
                    request.setAttribute("magasin", ms);
                    request.setAttribute("categorie", ms.getCategorieproduitMSList());
                    request.setAttribute("actions", "ok");

                    Message message = new Message("Ce personnel n'est pas dans votre Region Veuiller Contacter votre Administrateur");
                    request.setAttribute("message", message);
                    request.setAttribute("vue", vue);

                    request.getServletContext().getRequestDispatcher(redirec).forward(request, response);
                }

            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

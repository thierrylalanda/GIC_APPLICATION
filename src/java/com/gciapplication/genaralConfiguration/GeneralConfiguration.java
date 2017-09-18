/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.genaralConfiguration;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.CommandePersonnel;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.session.entity.ArticleFacadeLocal;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CommandePersonnelFacadeLocal;
import com.gciapplication.session.entity.OperationConsommateurFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "GeneralConfiguration", urlPatterns = {"/generalconfiguration"})
public class GeneralConfiguration extends HttpServlet {

    @EJB
    private ArticleFacadeLocal articleFacade;

    String codeappariel = "", save = "", edit = "ok", nom_client = "";
    int fois = 0;
    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private OperationConsommateurFacadeLocal consommateurFacadeLocal;

    @EJB
    private CommandePersonnelFacadeLocal commandePersonnelFacade;

    ArrayList<CommandePersonnel> listeCommandes = new ArrayList<>();
    ArrayList<OperationConsommateur> listeOperations = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
       String code="error";
       String categorie=request.getParameter("categorie");
        String designation = request.getParameter("designation");
        try {
            Article mS = articleFacade.findAllByDesignation(designation);
            if(mS.getCategorie().getTypeCategorie().equalsIgnoreCase(categorie)){
            code = mS.getCode();
            }
        } catch (Exception e) {
            System.out.println("mauvaise designation");
        }
            
            
       
             
       
        
        
        response.setContentType("application/text");
        response.getWriter().print(code);

    }

}

package com.gestion.autocomplete;

import com.gciapplication.entity.Article;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.gestion.DataSource.mysql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Servlet implementation class AutoComplete
 */
public class AutoCompleteProduit extends HttpServlet {

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public AutoCompleteProduit() {
        super();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String categorie=request.getParameter("categorie");
       System.out.println(categorie);
              CategorieProduit produit = categorieProduitFacade.findByTypeCategorie(categorie);
       
                    ArrayList<String> al = new ArrayList<>();
                    for (Article art : produit.getArticleList()) {
                        al.add(art.getDesignation());
                    }
                    
                    JSONArray json = new JSONArray(al);
                    response.setContentType("application/json");
                    response.getWriter().print(json);
                 


    }


}

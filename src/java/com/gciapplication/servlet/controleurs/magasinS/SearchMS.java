package com.gciapplication.servlet.controleurs.magasinS;


import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.StockproduitMS;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SearchMS", urlPatterns = "/searchms")
public class SearchMS extends HttpServlet {

    List<CategorieproduitMS> categorie;
    List<StockproduitMS> stocks;
    StockproduitMS stock;
    String nom_cat;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
         if (session.getAttribute("id") != null) {
        
        categorie = (List<CategorieproduitMS>) session.getAttribute("categoriee");
        //String code=request.getParameter("code_produit");

        String choix = request.getParameter("choix");
        String vue = request.getParameter("vue");
        int posi = 0;
        if (choix.equalsIgnoreCase("produit")) {
            String designation = request.getParameter("code_produit");
        }

        if (choix.equalsIgnoreCase("categorie")) {
            String catego = request.getParameter("categorie");

            for (int i = 0; i < categorie.size(); i++) {

                CategorieproduitMS get = categorie.get(i);
                if (get.getNomCategorie().equalsIgnoreCase(catego)) {
                    //categorie.remove(i);
                    posi = i;
                }

            }
            String cate = "un";
            request.setAttribute("categorie", categorie);
            request.setAttribute("posi", posi);
            request.setAttribute("cate", cate);

        } else if (choix.equalsIgnoreCase("tout")) {
            String all = "tous";
            request.setAttribute("all", all);
            request.setAttribute("categorie", categorie);
        } else if (choix.equalsIgnoreCase("produit")) {

            String designation = request.getParameter("designation");
            for (int i = 0; i < categorie.size(); i++) {
                stocks = categorie.get(i).getStockproduitMSList();
                for (int j = 0; j < stocks.size(); j++) {
                    if (stocks.get(j).getDesignation().equalsIgnoreCase(designation)) {
                        stock = stocks.get(j);
                        nom_cat = categorie.get(i).getNomCategorie();
                        request.setAttribute("stock", stock);
                        request.setAttribute("nom_cat", nom_cat);
                    }
                }

            }
            String produit = "produit";
            request.setAttribute("produit", produit);

        }
        request.setAttribute("vue", vue);
        request.getServletContext().getRequestDispatcher("/WEB-INF/magasignerS/main.jsp").forward(request, response);
   } else {
            response.sendRedirect("login.jsp");
        } }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.genaralConfiguration;

import com.gciapplication.entity.CategorieproduitMP;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.MagasinPrincipal;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.session.entity.CategorieproduitMPFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.MagasinPrincipalFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gestion.DataSource.mysql.DataStatistique;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

/**
 *
 * @author messi
 */
@Stateless
public class DataForStatistiques implements testeLocal {

    @EJB
    private CategorieproduitMPFacadeLocal categorieproduitMPFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private MagasinPrincipalFacadeLocal magasinPrincipalFacade;

    public DataForStatistiques() {
    }

    private int id;
    private String categorie;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Integer> consommationMensuelleMS() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            lists.add(DataStatistique.ConsommationMensuelMS(getCategorie(), getId(), i));
        }

        return lists;
    }

    @Override
    public List<Integer> EntrerMensuelleMS() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            lists.add(DataStatistique.EntrerMensuelMS(getCategorie(), getId(), i));
        }

        return lists;
    }

    @Override
    public List<Integer> consommationMensuelleMP() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            lists.add(DataStatistique.ConsommationMensuelMP(getCategorie(), getId(), i));
        }

        return lists;
    }

    @Override
    public List<Integer> EntrerMensuelleMP() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            lists.add(DataStatistique.EntrerMensuelMP(getCategorie(), getId(), i));
        }

        return lists;
    }

    @Override
    public void StatistiqueSortiMS(int idMS, HttpServletResponse response) {
        setId(idMS);
        MagasinSecondaire ms = magasinSecondaireFacade.find(getId());
        ms.setCategorieproduitMSList(categorieproduitMSFacade.findCatByidMS(getId()));
        Map myMap;

        JSONArray o = new JSONArray();
        for (CategorieproduitMS cat : ms.getCategorieproduitMSList()) {
            JSONArray aw = new JSONArray();
            myMap = new HashMap<>();
            JSONArray ar = new JSONArray();
            JSONArray arr = new JSONArray();
            setCategorie(cat.getNomCategorie());
            List<Integer> sortiConsommable = consommationMensuelleMS();
            List<Integer> EntrerConsommable = EntrerMensuelleMS();
            ar.add(EntrerConsommable);
            arr.add(sortiConsommable);
            aw.add(ar);
            aw.add(arr);
            myMap.put(cat.getNomCategorie(), aw);
            o.add(myMap);

        }

        String per = o.toJSONString();
        response.setContentType("application/json");
        System.out.println(per);
        try {
            response.getWriter().print(per);
        } catch (IOException ex) {
            Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void StatistiqueSortiMP(int idMP, HttpServletResponse response) {
        setId(idMP);
        MagasinPrincipal mp = magasinPrincipalFacade.find(getId());
        mp.setCategorieproduitMPList(categorieproduitMPFacade.findCatByidMP(getId()));
        Map myMap;

        JSONArray o = new JSONArray();
        for (CategorieproduitMP cat : mp.getCategorieproduitMPList()) {
            JSONArray aw = new JSONArray();
            myMap = new HashMap<>();
            JSONArray ar = new JSONArray();
            JSONArray arr = new JSONArray();
            setCategorie(cat.getNomCategorie());
            List<Integer> sortiConsommable = consommationMensuelleMP();
            List<Integer> EntrerConsommable = EntrerMensuelleMP();
            ar.add(EntrerConsommable);
            arr.add(sortiConsommable);
            aw.add(ar);
            aw.add(arr);
            myMap.put(cat.getNomCategorie(), aw);
            o.add(myMap);
        }

        String per = o.toJSONString();
        response.setContentType("application/json");
        System.out.println("MP");
        System.out.println(per);
        try {
            response.getWriter().print(per);
        } catch (IOException ex) {
            Logger.getLogger(DataForStatistiques.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void myTimer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

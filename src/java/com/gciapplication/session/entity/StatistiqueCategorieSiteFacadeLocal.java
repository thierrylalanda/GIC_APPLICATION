/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieSite;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueCategorieSiteFacadeLocal {

    void create(StatistiqueCategorieSite statistiqueCategorieSite);

    void edit(StatistiqueCategorieSite statistiqueCategorieSite);

    void remove(StatistiqueCategorieSite statistiqueCategorieSite);

    StatistiqueCategorieSite find(Object id);

    List<StatistiqueCategorieSite> findAll();

    List<StatistiqueCategorieSite> findRange(int[] range);

    int count();

    List<StatistiqueCategorieSite> findAllBySite(int site);

    List<StatistiqueCategorieSite> findAllBySiteAndcategorie(int site, String categorie);

    List<StatistiqueCategorieSite> findAllBySiteAndcategorieByPeriode(int site, String categorie, Date d1, Date d2);

    List<StatistiqueCategorieSite> findAllBycategorie(String categorie);

    List<StatistiqueCategorieSite> findAllBycategorieByPeriode(String categorie, Date d1, Date d2);

    StatistiqueCategorieSite findAllBycategorieByServiceAndOneDate(int site, String categorie, Date d1);

    int findAllByCategorieByAnneeForSite(int site, String categorie, int annee, int mois);

}

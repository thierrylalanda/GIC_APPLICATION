/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieRegion;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueCategorieRegionFacadeLocal {

    void create(StatistiqueCategorieRegion statistiqueCategorieRegion);

    void edit(StatistiqueCategorieRegion statistiqueCategorieRegion);

    void remove(StatistiqueCategorieRegion statistiqueCategorieRegion);

    StatistiqueCategorieRegion find(Object id);

    List<StatistiqueCategorieRegion> findAll();

    List<StatistiqueCategorieRegion> findRange(int[] range);

    int count();

    List<StatistiqueCategorieRegion> findAllByRegion(int region);

    List<StatistiqueCategorieRegion> findAllByRgionAndcategorie(int region, String categorie);

    List<StatistiqueCategorieRegion> findAllByRgionAndcategorieByPeriode(int region, String categorie, Date d1, Date d2);

    List<StatistiqueCategorieRegion> findAllBycategorie(String categorie);

    List<StatistiqueCategorieRegion> findAllBycategorieByPeriode(String categorie, Date d1, Date d2);

    StatistiqueCategorieRegion findAllBycategorieByServiceAndOneDate(int region, String categorie, Date d1);

    int findAllByCategorieByAnneeForRegion(int region, String categorie, int annee, int mois);

    int findAllByCategorieByNOWForRegion(String article, int mois);
    
    int findAllByCategorieByAnneeForSociete(String article, int mois,int annee);

}

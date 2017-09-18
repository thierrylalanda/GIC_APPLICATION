/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategoriePersonnel;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueCategoriePersonnelFacadeLocal {

    void create(StatistiqueCategoriePersonnel statistiqueCategoriePersonnel);

    void edit(StatistiqueCategoriePersonnel statistiqueCategoriePersonnel);

    void remove(StatistiqueCategoriePersonnel statistiqueCategoriePersonnel);

    StatistiqueCategoriePersonnel find(Object id);

    List<StatistiqueCategoriePersonnel> findAll();

    List<StatistiqueCategoriePersonnel> findRange(int[] range);

    int count();

    List<StatistiqueCategoriePersonnel> findAllBypersonnel(int personnel);

    List<StatistiqueCategoriePersonnel> findAllBypersonnelAndcategorie(int personnel, String categorie);

    List<StatistiqueCategoriePersonnel> findAllBypersonnelAndcategorieByPeriode(int personnel, String categorie, Date d1, Date d2);

    List<StatistiqueCategoriePersonnel> findAllBycategorie(String categorie);

    List<StatistiqueCategoriePersonnel> findAllBycategorieByPeriode(String categorie, Date d1, Date d2);

    StatistiqueCategoriePersonnel findAllBycategorieByServiceAndOneDate(int personnel, String categorie, Date d1);

    int findAllByCategorieByAnneeForPersonnel(int personnel, String categorie, int annee, int mois);

}

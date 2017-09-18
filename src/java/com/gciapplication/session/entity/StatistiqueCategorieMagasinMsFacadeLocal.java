/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieMagasinMs;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueCategorieMagasinMsFacadeLocal {

    void create(StatistiqueCategorieMagasinMs statistiqueCategorieMagasinMs);

    void edit(StatistiqueCategorieMagasinMs statistiqueCategorieMagasinMs);

    void remove(StatistiqueCategorieMagasinMs statistiqueCategorieMagasinMs);

    StatistiqueCategorieMagasinMs find(Object id);

    List<StatistiqueCategorieMagasinMs> findAll();

    List<StatistiqueCategorieMagasinMs> findRange(int[] range);

    int count();

    List<StatistiqueCategorieMagasinMs> findAllByMagasin(int magasin);

    List<StatistiqueCategorieMagasinMs> findAllByMagasinAndcategorie(int magasin, String categorie);

    List<StatistiqueCategorieMagasinMs> findAllByMagasinAndcategorieByPeriode(int magasin, String categorie, Date d1, Date d2);

    List<StatistiqueCategorieMagasinMs> findAllBycategorie(String categorie);

    List<StatistiqueCategorieMagasinMs> findAllBycategorieByPeriode(String categorie, Date d1, Date d2);

    StatistiqueCategorieMagasinMs findAllBycategorieBymagasinAndOneDate(int magasin, String categorie, Date d1);

   int findAllByCategorieByAnneeForMagMS(int magasin, String categorie, int annee, int mois);

}

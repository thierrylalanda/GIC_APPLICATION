/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategoriMagasinMp;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueCategoriMagasinMpFacadeLocal {

    void create(StatistiqueCategoriMagasinMp statistiqueCategoriMagasinMp);

    void edit(StatistiqueCategoriMagasinMp statistiqueCategoriMagasinMp);

    void remove(StatistiqueCategoriMagasinMp statistiqueCategoriMagasinMp);

    StatistiqueCategoriMagasinMp find(Object id);

    List<StatistiqueCategoriMagasinMp> findAll();

    List<StatistiqueCategoriMagasinMp> findRange(int[] range);

    int count();

    List<StatistiqueCategoriMagasinMp> findAllByMagasin(int magasin);

    List<StatistiqueCategoriMagasinMp> findAllByMagasinAndcategorie(int magasin, String categorie);

    List<StatistiqueCategoriMagasinMp> findAllByMagasinAndcategorieByPeriode(int magasin, String categorie, Date d1, Date d2);

    List<StatistiqueCategoriMagasinMp> findAllBycategorie(String categorie);

    List<StatistiqueCategoriMagasinMp> findAllBycategorieByPeriode(String categorie, Date d1, Date d2);

    StatistiqueCategoriMagasinMp findAllBycategorieBymagasinAndOneDate(int magasin, String categorie, Date d1);

    int findAllByCategorieByAnneeForMagMP(int magasin, String categorie, int annee, int mois);

}

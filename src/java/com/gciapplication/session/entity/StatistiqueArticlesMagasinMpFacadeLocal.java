/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesMagasinMp;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueArticlesMagasinMpFacadeLocal {

    void create(StatistiqueArticlesMagasinMp statistiqueArticlesMagasinMp);

    void edit(StatistiqueArticlesMagasinMp statistiqueArticlesMagasinMp);

    void remove(StatistiqueArticlesMagasinMp statistiqueArticlesMagasinMp);

    StatistiqueArticlesMagasinMp find(Object id);

    List<StatistiqueArticlesMagasinMp> findAll();

    List<StatistiqueArticlesMagasinMp> findRange(int[] range);

    int count();

    List<StatistiqueArticlesMagasinMp> findAllByMagasin(int magasin);

    List<StatistiqueArticlesMagasinMp> findAllByMagasinAndArticles(int magasin, String articles);

    List<StatistiqueArticlesMagasinMp> findAllByMagasinAndArticlesByPeriode(int magasin, String articles, Date d1, Date d2);

    List<StatistiqueArticlesMagasinMp> findAllByArticles(String articles);

    StatistiqueArticlesMagasinMp findAllByArticlesByPeriode(int magasin, String articles, Date d1, Date d2);

    StatistiqueArticlesMagasinMp findAllByArticlesByMagasinAndOneDate(int service, String articles, Date d1);
    
   int findAllByArticlesByAnneeForMagMP(int magasin,String article, int annee, int mois);
}

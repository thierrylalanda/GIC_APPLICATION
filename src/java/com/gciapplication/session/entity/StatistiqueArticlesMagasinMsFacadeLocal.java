/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesMagasinMp;
import com.gciapplication.entity.StatistiqueArticlesMagasinMs;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueArticlesMagasinMsFacadeLocal {

    void create(StatistiqueArticlesMagasinMs statistiqueArticlesMagasinMs);

    void edit(StatistiqueArticlesMagasinMs statistiqueArticlesMagasinMs);

    void remove(StatistiqueArticlesMagasinMs statistiqueArticlesMagasinMs);

    StatistiqueArticlesMagasinMs find(Object id);

    List<StatistiqueArticlesMagasinMs> findAll();

    List<StatistiqueArticlesMagasinMs> findRange(int[] range);

    int count();

    List<StatistiqueArticlesMagasinMs> findAllByMagasin(int magasin);

    List<StatistiqueArticlesMagasinMs> findAllByMagasinAndArticles(int magasin, String articles);

    List<StatistiqueArticlesMagasinMs> findAllByMagasinAndArticlesByPeriode(int personnel, String articles, Date d1, Date d2);

    List<StatistiqueArticlesMagasinMs> findAllByArticles(String articles);

    StatistiqueArticlesMagasinMs findAllByArticlesByPeriode(int magasin, String articles, Date d1, Date d2);

    StatistiqueArticlesMagasinMs findAllByArticlesByMagasinAndOneDate(int service, String articles, Date d1);
    
   int findAllByArticlesByAnneeForMagMS(int magasin,String article, int annee, int mois);
}

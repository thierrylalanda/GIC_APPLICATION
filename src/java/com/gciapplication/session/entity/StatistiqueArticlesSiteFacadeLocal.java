/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesSite;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueArticlesSiteFacadeLocal {

    void create(StatistiqueArticlesSite statistiqueArticlesSite);

    void edit(StatistiqueArticlesSite statistiqueArticlesSite);

    void remove(StatistiqueArticlesSite statistiqueArticlesSite);

    StatistiqueArticlesSite find(Object id);

    List<StatistiqueArticlesSite> findAll();

    List<StatistiqueArticlesSite> findRange(int[] range);

    int count();

    List<StatistiqueArticlesSite> findAllBySite(int site);

    List<StatistiqueArticlesSite> findAllBySiteAndArticles(int site, String articles);

    List<StatistiqueArticlesSite> findAllBySiteAndArticlesByPeriode(int site, String articles, Date d1, Date d2);

    List<StatistiqueArticlesSite> findAllByArticles(String articles);

    StatistiqueArticlesSite findAllByArticlesByPeriode(int site, String articles, Date d1, Date d2);

    StatistiqueArticlesSite findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1);
    
   int findAllByArticlesByAnneeForSite(int site, String article, int annee, int mois);

}

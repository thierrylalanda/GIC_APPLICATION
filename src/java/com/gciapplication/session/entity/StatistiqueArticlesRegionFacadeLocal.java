/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesRegion;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueArticlesRegionFacadeLocal {

    void create(StatistiqueArticlesRegion statistiqueArticlesRegion);

    void edit(StatistiqueArticlesRegion statistiqueArticlesRegion);

    void remove(StatistiqueArticlesRegion statistiqueArticlesRegion);

    StatistiqueArticlesRegion find(Object id);

    List<StatistiqueArticlesRegion> findAll();

    List<StatistiqueArticlesRegion> findRange(int[] range);

    int count();

    List<StatistiqueArticlesRegion> findAllByRegion(int region);

    List<StatistiqueArticlesRegion> findAllByRgionAndArticles(int region, String articles);

    List<StatistiqueArticlesRegion> findAllByRgionAndArticlesByPeriode(int region, String articles, Date d1, Date d2);

    List<StatistiqueArticlesRegion> findAllByArticles(String articles);

    StatistiqueArticlesRegion findAllByArticlesByPeriode(int region, String articles, Date d1, Date d2);

    StatistiqueArticlesRegion findAllByArticlesByServiceAndOneDate(int region, String articles, Date d1);

    int  findAllByArticlesByAnneeForRegion(int region, String article, int annee, int mois);

    int findAllByArticlesByNOWForRegion(String article, int mois);

}

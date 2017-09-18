/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesDirection;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueArticlesDirectionFacadeLocal {

    void create(StatistiqueArticlesDirection statistiqueArticlesDirection);

    void edit(StatistiqueArticlesDirection statistiqueArticlesDirection);

    void remove(StatistiqueArticlesDirection statistiqueArticlesDirection);

    StatistiqueArticlesDirection find(Object id);

    List<StatistiqueArticlesDirection> findAll();

    List<StatistiqueArticlesDirection> findRange(int[] range);

    int count();

    List<StatistiqueArticlesDirection> findAllByDirection(int direction);

    List<StatistiqueArticlesDirection> findAllByDirectionAndArticles(int direction, String articles);

    List<StatistiqueArticlesDirection> findAllByDirectionAndArticlesByPeriode(int direction, String articles, Date d1, Date d2);

    List<StatistiqueArticlesDirection> findAllByArticles(String articles);

    StatistiqueArticlesDirection findAllByArticlesByPeriode(int direction, String articles, Date d1, Date d2);

    StatistiqueArticlesDirection findAllByArticlesByServiceAndOneDate(int region, String articles, Date d1);
    
    int findAllByArticlesByAnneeForDirection(int direction,String article, int annee, int mois);
    
}

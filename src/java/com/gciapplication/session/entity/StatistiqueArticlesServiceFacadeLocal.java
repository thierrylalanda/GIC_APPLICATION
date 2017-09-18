/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesService;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueArticlesServiceFacadeLocal {

    void create(StatistiqueArticlesService statistiqueArticlesService);

    void edit(StatistiqueArticlesService statistiqueArticlesService);

    void remove(StatistiqueArticlesService statistiqueArticlesService);

    StatistiqueArticlesService find(Object id);

    List<StatistiqueArticlesService> findAll();

    List<StatistiqueArticlesService> findRange(int[] range);

    int count();
    
    List<StatistiqueArticlesService> findAllByService(int service);

    List<StatistiqueArticlesService> findAllByserviceAndArticles(int service, String articles);

    List<StatistiqueArticlesService> findAllByserviceAndArticlesByPeriode(int service, String articles, Date d1, Date d2);
    
    List<StatistiqueArticlesService> findAllByArticles(String articles);

    StatistiqueArticlesService findAllByArticlesByPeriode(int service, String articles, Date d1, Date d2);

    StatistiqueArticlesService findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1);
    
    int findAllByArticlesByAnneeForService(int service, String article, int annee, int mois);

}

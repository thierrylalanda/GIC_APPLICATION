/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesPersonnel;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueArticlesPersonnelFacadeLocal {

    void create(StatistiqueArticlesPersonnel statistiqueArticlesPersonnel);

    void edit(StatistiqueArticlesPersonnel statistiqueArticlesPersonnel);

    void remove(StatistiqueArticlesPersonnel statistiqueArticlesPersonnel);

    StatistiqueArticlesPersonnel find(Object id);

    List<StatistiqueArticlesPersonnel> findAll();

    List<StatistiqueArticlesPersonnel> findRange(int[] range);

    int count();

    List<StatistiqueArticlesPersonnel> findAllBypersonnel(int personnel);

    List<StatistiqueArticlesPersonnel> findAllBypersonnelAndArticles(int personnel, String articles);

    List<StatistiqueArticlesPersonnel> findAllBypersonnelAndArticlesByPeriode(int personnel, String articles, Date d1, Date d2);

    List<StatistiqueArticlesPersonnel> findAllByArticles(String articles);

    StatistiqueArticlesPersonnel findAllByArticlesByPeriode(int personnel, String articles, Date d1, Date d2);
    
    List<StatistiqueArticlesPersonnel> findAllByDateSortiByPersonnel(int personnel, Date d1, Date d2);

    StatistiqueArticlesPersonnel findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1);

    int findAllByArticlesByAnneeForPersonnel(int personnel, String article, int annee, int mois);

}

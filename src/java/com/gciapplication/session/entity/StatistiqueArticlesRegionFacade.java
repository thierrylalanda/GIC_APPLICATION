/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesRegion;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author messi
 */
@Stateless
public class StatistiqueArticlesRegionFacade extends AbstractFacade<StatistiqueArticlesRegion> implements StatistiqueArticlesRegionFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueArticlesRegionFacade() {
        super(StatistiqueArticlesRegion.class);
    }

    @Override
    public List<StatistiqueArticlesRegion> findAllByRegion(int region) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesRegion.findByRegion");
        query.setParameter("region", region);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesRegion> findAllByRgionAndArticles(int region, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesRegion.findAllByRgionAndCategorie");
        query.setParameter("region", region);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesRegion> findAllByRgionAndArticlesByPeriode(int region, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesRegion.findAllByRgionAndCategorieAndPeriode");
        query.setParameter("region", region);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesRegion> findAllByArticles(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesRegion.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueArticlesRegion findAllByArticlesByPeriode(int region, String articles, Date d1, Date d2) {
        StatistiqueArticlesRegion ms = new StatistiqueArticlesRegion();
        int quantite = 0;
        double prixtotal = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_region.quantite) AS quantite\n"
                + "FROM statistique_articles_region WHERE statistique_articles_region.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_region.articles = ?3 AND statistique_articles_region.region = ?4");
        query.setParameter("1", d1);
        query.setParameter("2", d2);
        query.setParameter("3", articles);
        query.setParameter("4", region);

        Query q = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_region.prixtotal) AS prixtotal\n"
                + "FROM statistique_articles_region WHERE statistique_articles_region.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_region.articles = ?3 AND statistique_articles_region.region = ?4");
        q.setParameter("1", d1);
        q.setParameter("2", d2);
        q.setParameter("3", articles);
        q.setParameter("4", region);

        if (query.getSingleResult() != null && q.getSingleResult() != null) {
           
            quantite = Integer.parseInt(query.getSingleResult().toString());
            prixtotal = Double.parseDouble(q.getSingleResult().toString());
        }
        
        ms.setArticles(articles);
        ms.setQuantite(quantite);
        ms.setPrixtotal(prixtotal);
        return ms;
    }

    @Override
    public StatistiqueArticlesRegion findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesRegion.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);

        return (StatistiqueArticlesRegion) query.getSingleResult();
    }

    @Override
    public int findAllByArticlesByAnneeForRegion(int region, String article, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_region.quantite) AS quantite FROM statistique_articles_region WHERE \n"
                + "statistique_articles_region.region = ?1 AND YEAR(statistique_articles_region.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_articles_region.date_sorti) = ?3 AND statistique_articles_region.articles = ?4");
        query.setParameter("1", region);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", article);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

    @Override
    public int findAllByArticlesByNOWForRegion(String article, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_region.quantite) AS quantite FROM statistique_articles_region WHERE \n"
                + "YEAR(statistique_articles_region.date_sorti) = YEAR(NOW()) AND \n"
                + "MONTH(statistique_articles_region.date_sorti) = ?1 AND statistique_articles_region.articles = ?2");
        query.setParameter("1", mois);
        query.setParameter("2", article);

        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

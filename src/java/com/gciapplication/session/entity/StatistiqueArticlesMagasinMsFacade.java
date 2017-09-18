/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesMagasinMs;
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
public class StatistiqueArticlesMagasinMsFacade extends AbstractFacade<StatistiqueArticlesMagasinMs> implements StatistiqueArticlesMagasinMsFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueArticlesMagasinMsFacade() {
        super(StatistiqueArticlesMagasinMs.class);
    }

    @Override
    public List<StatistiqueArticlesMagasinMs> findAllByMagasin(int magasin) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMs.findByRegion");
        query.setParameter("region", magasin);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesMagasinMs> findAllByMagasinAndArticles(int magasin, String articles) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMs.findAllByRgionAndCategorie");
        query.setParameter("region", magasin);
        query.setParameter("categorie", articles);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesMagasinMs> findAllByMagasinAndArticlesByPeriode(int magasin, String articles, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMs.findAllByRgionAndCategorieAndPeriode");
        query.setParameter("region", magasin);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesMagasinMs> findAllByArticles(String articles) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMs.findByCategorie");
        query.setParameter("categorie", articles);
        return query.getResultList();
    }

    @Override
    public StatistiqueArticlesMagasinMs findAllByArticlesByPeriode(int magasin, String articles, Date d1, Date d2) {
        StatistiqueArticlesMagasinMs ms = new StatistiqueArticlesMagasinMs();
        int quantite = 0;
        double prixtotal = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_magasin_ms.quantite) AS quantite\n"
                + "FROM statistique_articles_magasin_ms WHERE statistique_articles_magasin_ms.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_magasin_ms.articles = ?3 AND statistique_articles_magasin_ms.magasin = ?4");
        query.setParameter("1", d1);
        query.setParameter("2", d2);
        query.setParameter("3", articles);
        query.setParameter("4", magasin);
        
          Query q = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_magasin_ms.prixtotal) AS prixtotal\n"
                + "FROM statistique_articles_magasin_ms WHERE statistique_articles_magasin_ms.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_magasin_ms.articles = ?3 AND statistique_articles_magasin_ms.magasin = ?4");
        q.setParameter("1", d1);
        q.setParameter("2", d2);
        q.setParameter("3", articles);
        q.setParameter("4", magasin);

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
    public StatistiqueArticlesMagasinMs findAllByArticlesByMagasinAndOneDate(int service, String articles, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMs.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);

        return (StatistiqueArticlesMagasinMs) query.getSingleResult();
    }

    @Override
    public int findAllByArticlesByAnneeForMagMS(int magasin, String article, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_magasin_ms .quantite) AS quantite FROM statistique_articles_magasin_ms WHERE \n"
                + "statistique_articles_magasin_ms.magasin = ?1 AND YEAR(statistique_articles_magasin_ms.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_articles_magasin_ms.date_sorti) = ?3 AND statistique_articles_magasin_ms.articles = ?4");
        query.setParameter("1", magasin);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", article);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

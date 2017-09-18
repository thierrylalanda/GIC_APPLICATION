/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesSite;
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
public class StatistiqueArticlesSiteFacade extends AbstractFacade<StatistiqueArticlesSite> implements StatistiqueArticlesSiteFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueArticlesSiteFacade() {
        super(StatistiqueArticlesSite.class);
    }

    @Override
    public List<StatistiqueArticlesSite> findAllBySite(int site) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesSite.findBySite");
        query.setParameter("region", site);

        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesSite> findAllBySiteAndArticles(int site, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesSite.findAllBySiteAndCategorie");
        query.setParameter("region", site);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesSite> findAllBySiteAndArticlesByPeriode(int site, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesSite.findAllBySiteAndCategorieAndPeriode");
        query.setParameter("region", site);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesSite> findAllByArticles(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesSite.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueArticlesSite findAllByArticlesByPeriode(int site, String articles, Date d1, Date d2) {
        StatistiqueArticlesSite ms = new StatistiqueArticlesSite();
        int quantite = 0;
        double prixtotal = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_site.quantite) AS quantite\n"
                + "FROM statistique_articles_site WHERE statistique_articles_site.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_site.articles = ?3 AND statistique_articles_site.site = ?4");
        query.setParameter("1", d1);
        query.setParameter("2", d2);
        query.setParameter("3", articles);
        query.setParameter("4", site);

        Query q = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_site.prixtotal) AS prixtotal\n"
                + "FROM statistique_articles_site WHERE statistique_articles_site.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_site.articles = ?3 AND statistique_articles_site.site = ?4");
        q.setParameter("1", d1);
        q.setParameter("2", d2);
        q.setParameter("3", articles);
        q.setParameter("4", site);

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
    public StatistiqueArticlesSite findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesSite.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);

        return (StatistiqueArticlesSite) query.getSingleResult();
    }

    @Override
    public int findAllByArticlesByAnneeForSite(int site, String article, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_site.quantite) AS quantite FROM statistique_articles_site WHERE \n"
                + "statistique_articles_site.site = ?1 AND YEAR(statistique_articles_site.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_articles_site.date_sorti) = ?3 AND statistique_articles_site.articles = ?4");
        query.setParameter("1", site);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", article);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

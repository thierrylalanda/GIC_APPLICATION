/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesMagasinMp;
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
public class StatistiqueArticlesMagasinMpFacade extends AbstractFacade<StatistiqueArticlesMagasinMp> implements StatistiqueArticlesMagasinMpFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueArticlesMagasinMpFacade() {
        super(StatistiqueArticlesMagasinMp.class);
    }

    @Override
    public List<StatistiqueArticlesMagasinMp> findAllByMagasin(int magasin) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMp.findByRegion");
        query.setParameter("region", magasin);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesMagasinMp> findAllByMagasinAndArticles(int magasin, String articles) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMp.findAllByRgionAndCategorie");
        query.setParameter("region", magasin);
        query.setParameter("categorie", articles);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesMagasinMp> findAllByMagasinAndArticlesByPeriode(int magasin, String articles, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMp.findAllByRgionAndCategorieAndPeriode");
        query.setParameter("region", magasin);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesMagasinMp> findAllByArticles(String articles) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMp.findByCategorie");
        query.setParameter("categorie", articles);
        return query.getResultList();
    }

    @Override
    public StatistiqueArticlesMagasinMp findAllByArticlesByPeriode(int magasin, String articles, Date d1, Date d2) {
        StatistiqueArticlesMagasinMp ms = new StatistiqueArticlesMagasinMp();
        int quantite = 0;
        double prixtotal = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_magasin_mp.quantite) AS quantite\n"
                + "FROM statistique_articles_magasin_mp WHERE statistique_articles_magasin_mp.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_magasin_mp.articles = ?3 AND statistique_articles_magasin_mp.magasin = ?4");
        query.setParameter("1", d1);
        query.setParameter("2", d2);
        query.setParameter("3", articles);
        query.setParameter("4", magasin);

        Query q = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_magasin_mp.prixtotal) AS prixtotal\n"
                + "FROM statistique_articles_magasin_mp WHERE statistique_articles_magasin_mp.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_magasin_mp.articles = ?3 AND statistique_articles_magasin_mp.magasin = ?4");
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
    public StatistiqueArticlesMagasinMp findAllByArticlesByMagasinAndOneDate(int service, String articles, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesMagasinMp.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);

        return (StatistiqueArticlesMagasinMp) query.getSingleResult();
    }

    @Override
    public int findAllByArticlesByAnneeForMagMP(int magasin, String article, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_magasin_mp .quantite) AS quantite FROM statistique_articles_magasin_mp WHERE \n"
                + "statistique_articles_magasin_mp.magasin = ?1 AND YEAR(statistique_articles_magasin_mp.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_articles_magasin_mp.date_sorti) = ?3 AND statistique_articles_magasin_mp.articles = ?4");
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

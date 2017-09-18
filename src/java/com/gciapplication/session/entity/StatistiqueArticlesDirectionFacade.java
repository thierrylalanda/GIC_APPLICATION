/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesDirection;
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
public class StatistiqueArticlesDirectionFacade extends AbstractFacade<StatistiqueArticlesDirection> implements StatistiqueArticlesDirectionFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueArticlesDirectionFacade() {
        super(StatistiqueArticlesDirection.class);
    }

    @Override
    public List<StatistiqueArticlesDirection> findAllByDirection(int direction) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesDirection.findByDirection");
        query.setParameter("region", direction);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesDirection> findAllByDirectionAndArticles(int direction, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesDirection.findAllByDirectionAndCategorie");
        query.setParameter("region", direction);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesDirection> findAllByDirectionAndArticlesByPeriode(int direction, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesDirection.findAllByDirectionAndCategorieAndPeriode");
        query.setParameter("region", direction);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesDirection> findAllByArticles(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesDirection.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueArticlesDirection findAllByArticlesByPeriode(int direction, String articles, Date d1, Date d2) {
        StatistiqueArticlesDirection ms = new StatistiqueArticlesDirection();
        int quantite = 0;
        double prixtotal = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_direction.quantite) AS quantite\n"
                + "FROM statistique_articles_direction WHERE statistique_articles_direction.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_direction.articles = ?3 AND statistique_articles_direction.direction = ?4");
        query.setParameter("1", d1);
        query.setParameter("2", d2);
        query.setParameter("3", articles);
        query.setParameter("4", direction);

        Query q = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_direction.prixtotal) AS prixtotal\n"
                + "FROM statistique_articles_direction WHERE statistique_articles_direction.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_direction.articles = ?3 AND statistique_articles_direction.direction = ?4");
        q.setParameter("1", d1);
        q.setParameter("2", d2);
        q.setParameter("3", articles);
        q.setParameter("4", direction);

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
    public StatistiqueArticlesDirection findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesDirection.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);

        return (StatistiqueArticlesDirection) query.getSingleResult();
    }

    @Override
    public int findAllByArticlesByAnneeForDirection(int direction, String article, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_direction.quantite) AS quantite FROM statistique_articles_direction WHERE \n"
                + "statistique_articles_direction.direction = ?1 AND YEAR(statistique_articles_direction.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_articles_direction.date_sorti) = ?3 AND statistique_articles_direction.articles = ?4");
        query.setParameter("1", direction);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", article);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

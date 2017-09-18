/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesService;
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
public class StatistiqueArticlesServiceFacade extends AbstractFacade<StatistiqueArticlesService> implements StatistiqueArticlesServiceFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueArticlesServiceFacade() {
        super(StatistiqueArticlesService.class);
    }

    @Override
    public List<StatistiqueArticlesService> findAllByService(int service) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesService.findByService");
        query.setParameter("region", service);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesService> findAllByserviceAndArticles(int service, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesService.findAllByServiceAndCategorie");
        query.setParameter("region", service);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesService> findAllByArticles(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesService.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesService> findAllByserviceAndArticlesByPeriode(int service, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesService.findAllBySiteAndCategorieAndPeriode");
        query.setParameter("region", service);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public StatistiqueArticlesService findAllByArticlesByPeriode(int service, String articles, Date d1, Date d2) {
        StatistiqueArticlesService ms = new StatistiqueArticlesService();
        int quantite = 0;
        double prixtotal = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_service.quantite) AS quantite\n"
                + "FROM statistique_articles_service WHERE statistique_articles_service.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_service.articles = ?3 AND statistique_articles_service.service = ?4");
        query.setParameter("1", d1);
        query.setParameter("2", d2);
        query.setParameter("3", articles);
        query.setParameter("4", service);

        Query q = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_service.prixtotal) AS prixtotal\n"
                + "FROM statistique_articles_service WHERE statistique_articles_service.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_service.articles = ?3 AND statistique_articles_service.service = ?4");
        q.setParameter("1", d1);
        q.setParameter("2", d2);
        q.setParameter("3", articles);
        q.setParameter("4", service);

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
    public StatistiqueArticlesService findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesService.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);

        return (StatistiqueArticlesService) query.getSingleResult();
    }

    @Override
    public int findAllByArticlesByAnneeForService(int service, String article, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_service.quantite) AS quantite FROM statistique_articles_service WHERE \n"
                + "statistique_articles_service.service = ?1 AND YEAR(statistique_articles_service.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_articles_service.date_sorti) = ?3 AND statistique_articles_service.articles = ?4");
        query.setParameter("1", service);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", article);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

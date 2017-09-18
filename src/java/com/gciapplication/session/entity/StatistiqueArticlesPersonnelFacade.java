/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueArticlesPersonnel;
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
public class StatistiqueArticlesPersonnelFacade extends AbstractFacade<StatistiqueArticlesPersonnel> implements StatistiqueArticlesPersonnelFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueArticlesPersonnelFacade() {
        super(StatistiqueArticlesPersonnel.class);
    }

    @Override
    public List<StatistiqueArticlesPersonnel> findAllBypersonnel(int personnel) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findByPersonnel");
        query.setParameter("region", personnel);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesPersonnel> findAllBypersonnelAndArticles(int personnel, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findAllByPersonnelAndCategorie");
        query.setParameter("region", personnel);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesPersonnel> findAllBypersonnelAndArticlesByPeriode(int personnel, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findAllByPersonnelAndCategorieAndPeriode");
        query.setParameter("region", personnel);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueArticlesPersonnel> findAllByArticles(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueArticlesPersonnel findAllByArticlesByPeriode(int personnel, String articles, Date d1, Date d2) {
        StatistiqueArticlesPersonnel ms = new StatistiqueArticlesPersonnel();
        int quantite = 0;
        double prixtotal = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_personnel.quantite) AS quantite\n"
                + "FROM statistique_articles_personnel WHERE statistique_articles_personnel.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_personnel.articles = ?3 AND statistique_articles_personnel.personnel = ?4");
        query.setParameter("1", d1);
        query.setParameter("2", d2);
        query.setParameter("3", articles);
        query.setParameter("4", personnel);

        Query q = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_personnel.prixtotal) AS prixtotal\n"
                + "FROM statistique_articles_personnel WHERE statistique_articles_personnel.date_sorti BETWEEN ?1 AND ?2 AND \n"
                + "statistique_articles_personnel.articles = ?3 AND statistique_articles_personnel.personnel = ?4");
        q.setParameter("1", d1);
        q.setParameter("2", d2);
        q.setParameter("3", articles);
        q.setParameter("4", personnel);

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
    public StatistiqueArticlesPersonnel findAllByArticlesByServiceAndOneDate(int service, String articles, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", articles);
        query.setParameter("d1", d1);

        return (StatistiqueArticlesPersonnel) query.getSingleResult();
    }

    @Override
    public int findAllByArticlesByAnneeForPersonnel(int personnel, String article, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_articles_personnel .quantite) AS quantite FROM statistique_articles_personnel WHERE \n"
                + "statistique_articles_personnel.personnel = ?1 AND YEAR(statistique_articles_personnel.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_articles_personnel.date_sorti) = ?3 AND statistique_articles_personnel.articles = ?4");
        query.setParameter("1", personnel);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", article);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

    @Override
    public List<StatistiqueArticlesPersonnel> findAllByDateSortiByPersonnel(int personnel, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueArticlesPersonnel.findByDateSortiAndPersonnel");
        query.setParameter("personnel", personnel);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

}

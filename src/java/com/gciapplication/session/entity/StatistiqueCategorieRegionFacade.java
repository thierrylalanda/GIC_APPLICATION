/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieRegion;
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
public class StatistiqueCategorieRegionFacade extends AbstractFacade<StatistiqueCategorieRegion> implements StatistiqueCategorieRegionFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueCategorieRegionFacade() {
        super(StatistiqueCategorieRegion.class);
    }

    @Override
    public List<StatistiqueCategorieRegion> findAllByRegion(int region) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieRegion.findByRegion");
        query.setParameter("region", region);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieRegion> findAllByRgionAndcategorie(int region, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieRegion.findAllByRgionAndCategorie");
        query.setParameter("region", region);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieRegion> findAllByRgionAndcategorieByPeriode(int region, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieRegion.findAllByRgionAndCategorieAndPeriode");
        query.setParameter("region", region);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieRegion> findAllBycategorieByPeriode(String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieRegion.findAllByCategorieAndPeriode");
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieRegion> findAllBycategorie(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieRegion.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueCategorieRegion findAllBycategorieByServiceAndOneDate(int service, String categorie, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieRegion.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);

        return (StatistiqueCategorieRegion) query.getSingleResult();
    }

    @Override
    public int findAllByCategorieByAnneeForRegion(int region, String categorie, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_region.quantite) AS quantite FROM statistique_categorie_region WHERE \n"
                + "statistique_categorie_region.region = ?1 AND YEAR(statistique_categorie_region.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_categorie_region.date_sorti) = ?3 AND statistique_categorie_region.categorie = ?4");
        query.setParameter("1", region);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", categorie);

        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

    @Override
    public int findAllByCategorieByNOWForRegion(String article, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_region.quantite) AS quantite FROM statistique_categorie_region WHERE \n"
                + "YEAR(statistique_categorie_region.date_sorti) = YEAR(NOW()) AND \n"
                + "MONTH(statistique_categorie_region.date_sorti) = ?1 AND statistique_categorie_region.articles = ?2");
        query.setParameter("1", mois);
        query.setParameter("2", article);

        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

    @Override
    public int findAllByCategorieByAnneeForSociete(String article, int mois, int annee) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_region.quantite) AS quantite FROM statistique_categorie_region WHERE \n"
                + "YEAR(statistique_categorie_region.date_sorti) = ?1 AND \n"
                + "MONTH(statistique_categorie_region.date_sorti) = ?2 AND statistique_categorie_region.categorie = ?3");
        query.setParameter("1", annee);
        query.setParameter("2", mois);
        query.setParameter("3", article);

        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }
}

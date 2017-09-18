/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieSite;
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
public class StatistiqueCategorieSiteFacade extends AbstractFacade<StatistiqueCategorieSite> implements StatistiqueCategorieSiteFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueCategorieSiteFacade() {
        super(StatistiqueCategorieSite.class);
    }

    @Override
    public List<StatistiqueCategorieSite> findAllBySite(int site) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieSite.findBySite");
        query.setParameter("region", site);

        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieSite> findAllBySiteAndcategorie(int site, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieSite.findAllBySiteAndCategorie");
        query.setParameter("region", site);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieSite> findAllBySiteAndcategorieByPeriode(int site, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieSite.findAllBySiteAndCategorieAndPeriode");
        query.setParameter("region", site);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieSite> findAllBycategorieByPeriode(String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieSite.findAllByCategorieAndPeriode");
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieSite> findAllBycategorie(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieSite.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueCategorieSite findAllBycategorieByServiceAndOneDate(int service, String categorie, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieSite.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);

        return (StatistiqueCategorieSite) query.getSingleResult();
    }

    @Override
    public int findAllByCategorieByAnneeForSite(int site, String categorie, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_site.quantite) AS quantite FROM statistique_categorie_site WHERE \n"
                + "statistique_categorie_site.site = ?1 AND YEAR(statistique_categorie_site.date_Sortie) = ?2 AND \n"
                + "MONTH(statistique_categorie_site.date_Sortie) = ?3 AND statistique_categorie_site.categorie = ?4");
        query.setParameter("1", site);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", categorie);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }
}

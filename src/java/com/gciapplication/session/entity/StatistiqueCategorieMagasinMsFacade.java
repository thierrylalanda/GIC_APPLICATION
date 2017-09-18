/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieMagasinMs;
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
public class StatistiqueCategorieMagasinMsFacade extends AbstractFacade<StatistiqueCategorieMagasinMs> implements StatistiqueCategorieMagasinMsFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueCategorieMagasinMsFacade() {
        super(StatistiqueCategorieMagasinMs.class);
    }

    @Override
    public List<StatistiqueCategorieMagasinMs> findAllByMagasin(int magasin) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieMagasinMs.findByService");
        query.setParameter("region", magasin);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieMagasinMs> findAllByMagasinAndcategorie(int magasin, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieMagasinMs.findAllByServiceAndCategorie");
        query.setParameter("region", magasin);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieMagasinMs> findAllByMagasinAndcategorieByPeriode(int magasin, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieMagasinMs.findAllByServiseAndCategorieAndPeriode");
        query.setParameter("region", magasin);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieMagasinMs> findAllBycategorie(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieMagasinMs.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieMagasinMs> findAllBycategorieByPeriode(String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieMagasinMs.findAllByCategorieAndPeriode");
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public StatistiqueCategorieMagasinMs findAllBycategorieBymagasinAndOneDate(int magasin, String categorie, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieMagasinMs.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", magasin);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);

        return (StatistiqueCategorieMagasinMs) query.getSingleResult();
    }

    @Override
    public int findAllByCategorieByAnneeForMagMS(int magasin, String categorie, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_magasin_ms.quantite) AS quantite FROM statistique_categorie_magasin_ms WHERE \n"
                + "statistique_categorie_magasin_ms.magasin = ?1 AND YEAR(statistique_categorie_magasin_ms.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_categorie_magasin_ms.date_sorti) = ?3 AND statistique_categorie_magasin_ms.categorie = ?4");
        query.setParameter("1", magasin);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", categorie);

        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }
}

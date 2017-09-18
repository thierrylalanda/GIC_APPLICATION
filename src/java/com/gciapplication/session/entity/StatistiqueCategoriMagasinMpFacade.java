/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategoriMagasinMp;
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
public class StatistiqueCategoriMagasinMpFacade extends AbstractFacade<StatistiqueCategoriMagasinMp> implements StatistiqueCategoriMagasinMpFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueCategoriMagasinMpFacade() {
        super(StatistiqueCategoriMagasinMp.class);
    }

    @Override
    public List<StatistiqueCategoriMagasinMp> findAllByMagasin(int magasin) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriMagasinMp.findByService");
        query.setParameter("region", magasin);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriMagasinMp> findAllByMagasinAndcategorie(int magasin, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriMagasinMp.findAllByServiceAndCategorie");
        query.setParameter("region", magasin);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriMagasinMp> findAllByMagasinAndcategorieByPeriode(int magasin, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriMagasinMp.findAllByServiseAndCategorieAndPeriode");
        query.setParameter("region", magasin);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriMagasinMp> findAllBycategorie(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriMagasinMp.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriMagasinMp> findAllBycategorieByPeriode(String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriMagasinMp.findAllByCategorieAndPeriode");
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public StatistiqueCategoriMagasinMp findAllBycategorieBymagasinAndOneDate(int magasin, String categorie, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriMagasinMp.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", magasin);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);

        return (StatistiqueCategoriMagasinMp) query.getSingleResult();
    }

    @Override
    public int findAllByCategorieByAnneeForMagMP(int magasin, String categorie, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categori_magasin_mp.quantite) AS quantite FROM statistique_categori_magasin_mp WHERE \n"
                + "statistique_categori_magasin_mp.magasin = ?1 AND YEAR(statistique_categori_magasin_mp.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_categori_magasin_mp.date_sorti) = ?3 AND statistique_categori_magasin_mp.categorie = ?4");
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

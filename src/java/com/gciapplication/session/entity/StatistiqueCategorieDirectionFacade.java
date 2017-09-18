/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieDirection;
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
public class StatistiqueCategorieDirectionFacade extends AbstractFacade<StatistiqueCategorieDirection> implements StatistiqueCategorieDirectionFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueCategorieDirectionFacade() {
        super(StatistiqueCategorieDirection.class);
    }

    @Override
    public List<StatistiqueCategorieDirection> findAllByDirection(int direction) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieDirection.findByDirection");
        query.setParameter("region", direction);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieDirection> findAllByDirectionAndcategorie(int direction, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieDirection.findAllByDirectionAndCategorie");
        query.setParameter("region", direction);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieDirection> findAllByDirectionAndcategorieByPeriode(int direction, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieDirection.findAllByDirectionAndCategorieAndPeriode");
        query.setParameter("region", direction);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieDirection> findAllBycategorieByPeriode(String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieDirection.findAllByCategorieAndPeriode");
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieDirection> findAllBycategorie(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieDirection.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueCategorieDirection findAllBycategorieByServiceAndOneDate(int service, String categorie, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieDirection.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);

        return (StatistiqueCategorieDirection) query.getSingleResult();
    }

    @Override
    public int findAllByCategorieByAnneeForDirection(int direction, String categorie, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_direction.quantite) AS quantite FROM statistique_categorie_direction WHERE \n"
                + "statistique_categorie_direction.direction = ?1 AND YEAR(statistique_categorie_direction.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_categorie_direction.date_sorti) = ?3 AND statistique_categorie_direction.categorie = ?4");
        query.setParameter("1", direction);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", categorie);
        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategoriePersonnel;
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
public class StatistiqueCategoriePersonnelFacade extends AbstractFacade<StatistiqueCategoriePersonnel> implements StatistiqueCategoriePersonnelFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueCategoriePersonnelFacade() {
        super(StatistiqueCategoriePersonnel.class);
    }

    @Override
    public List<StatistiqueCategoriePersonnel> findAllBypersonnel(int personnel) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findByPersonnel");
        query.setParameter("region", personnel);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriePersonnel> findAllBypersonnelAndcategorieByPeriode(int personnel, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findAllByPersonnelAndCategorieAndPeriode");
        query.setParameter("region", personnel);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriePersonnel> findAllBypersonnelAndcategorie(int personnel, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findAllByPersonnelAndCategorie");
        query.setParameter("region", personnel);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriePersonnel> findAllBycategorieByPeriode(String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findAllByCategorieAndPeriode");
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategoriePersonnel> findAllBycategorie(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueCategoriePersonnel findAllBycategorieByServiceAndOneDate(int service, String categorie, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategoriePersonnel.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);

        return (StatistiqueCategoriePersonnel) query.getSingleResult();
    }

    @Override
    public int findAllByCategorieByAnneeForPersonnel(int personnel, String categorie, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_personnel.quantite) AS quantite FROM statistique_categorie_personnel WHERE \n"
                + "statistique_categorie_personnel.personnel = ?1 AND YEAR(statistique_categorie_personnel.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_categorie_personnel.date_sorti) = ?3 AND statistique_categorie_personnel.categorie = ?4");
        query.setParameter("1", personnel);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", categorie);

        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

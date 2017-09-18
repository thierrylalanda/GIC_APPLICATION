/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieService;
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
public class StatistiqueCategorieServiceFacade extends AbstractFacade<StatistiqueCategorieService> implements StatistiqueCategorieServiceFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatistiqueCategorieServiceFacade() {
        super(StatistiqueCategorieService.class);
    }

    @Override
    public List<StatistiqueCategorieService> findAllByService(int service) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieService.findByService");
        query.setParameter("region", service);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieService> findAllByserviceAndcategorie(int service, String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieService.findAllByServiceAndCategorie");
        query.setParameter("region", service);
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieService> findAllByserviceAndcategorieByPeriode(int service, String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieService.findAllBySiteAndCategorieAndPeriode");
        query.setParameter("region", service);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieService> findAllBycategorieByPeriode(String categorie, Date d1, Date d2) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieService.findAllByCategorieAndPeriode");
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }

    @Override
    public List<StatistiqueCategorieService> findAllBycategorie(String categorie) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieService.findByCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    @Override
    public StatistiqueCategorieService findAllBycategorieByServiceAndOneDate(int service, String categorie, Date d1) {
        Query query = getEntityManager().createNamedQuery("StatistiqueCategorieService.findAllByCategorieAndServiceAndOneDate");
        query.setParameter("service", service);
        query.setParameter("categorie", categorie);
        query.setParameter("d1", d1);

        return (StatistiqueCategorieService) query.getSingleResult();
    }

    @Override
    public int findAllByCategorieByAnneeForService(int service, String categorie, int annee, int mois) {
        int quantite = 0;
        Query query = getEntityManager().createNativeQuery("SELECT SUM(statistique_categorie_service.quantite) AS quantite FROM statistique_categorie_service WHERE \n"
                + "statistique_categorie_service.service = ?1 AND YEAR(statistique_categorie_service.date_sorti) = ?2 AND \n"
                + "MONTH(statistique_categorie_service.date_sorti) = ?3 AND statistique_categorie_service.categorie = ?4");
        query.setParameter("1", service);
        query.setParameter("2", annee);
        query.setParameter("3", mois);
        query.setParameter("4", categorie);

        if (query.getSingleResult() != null) {
            quantite = Integer.parseInt(query.getSingleResult().toString());
        }
        return quantite;
    }

}

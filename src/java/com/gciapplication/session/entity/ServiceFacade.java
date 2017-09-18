/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Service;
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
public class ServiceFacade extends AbstractFacade<Service> implements ServiceFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceFacade() {
        super(Service.class);
    }

    @Override
    public List<Service> findAllServiseByRegion(String region) {
        Query query = getEntityManager().createNamedQuery("Service.findAllServiceByRegion");
        query.setParameter("nom_Region", region);
        return query.getResultList();
    }

    @Override
    public Service lastInsert() {
        Query query = getEntityManager().createNamedQuery("Service.findLastInsert");
        return (Service) query.getSingleResult();
    }

    @Override
    public List<Service> findAllByIdSte(int idSite) {
        Query query = getEntityManager().createNamedQuery("Service.findAllByIdSite");
        query.setParameter("idSite", idSite);
        return query.getResultList();
    }

    @Override
    public List<Service> findAllByIdDirection(int idDirection) {
        Query query = getEntityManager().createNamedQuery("Service.findAllByIdDirection");
        query.setParameter("idDirection", idDirection);
        return query.getResultList();
    }

    @Override
    public List<Service> findAllBylibelleCentrecout(String libelle) {
        Query query = getEntityManager().createNamedQuery("Service.findAllByIdCentreCout");
        query.setParameter("libelle", libelle);
        return query.getResultList();
    }

    @Override
    public Service findByNameService(String service) {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM Service WHERE Service.nom_service = ?1",Service.class);
        query.setParameter("1", service);
        return (Service) query.getSingleResult(); }

}

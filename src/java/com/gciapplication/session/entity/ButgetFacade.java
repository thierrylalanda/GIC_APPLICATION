/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Butget;
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
public class ButgetFacade extends AbstractFacade<Butget> implements ButgetFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ButgetFacade() {
        super(Butget.class);
    }

    @Override
    public List<Butget> findLatButget() {
        Query query = getEntityManager().createNamedQuery("Butget.findLastBudget");
        return query.getResultList();
    }

    @Override
    public Butget findOnBudgetForService(int idMS, int idtypeProduit, String typeBudget) {
        Query query = getEntityManager().createNamedQuery("Butget.findBudgetForOnCategorieProduit");
        query.setParameter("idMS", idMS);
        query.setParameter("typebudget", typeBudget);
        query.setParameter("idCategorie", idtypeProduit);
        return (Butget) query.getSingleResult();
    }

    @Override
    public List<Butget> findAllBudgetByidMS(int idMS) {
        Query query = getEntityManager().createNamedQuery("Butget.findByIdMS");
        query.setParameter("idMagasin", idMS);
        return query.getResultList();
    }
    
     @Override
    public List<Butget> findByRegion(String region) {
       Query query = getEntityManager().createNamedQuery("Butget.findByRegion");
        query.setParameter("region", region);
        return query.getResultList();  }

    @Override
    public List<String> findByRegionAndTypeBudget(String region) {
         Query query = getEntityManager().createNamedQuery("Butget.findByRegionAndTypeBudget");
        query.setParameter("region", region);
        
        return query.getResultList();  
    }

}

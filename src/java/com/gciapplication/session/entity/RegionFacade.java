/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Region;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author messi
 */
@Stateless
public class RegionFacade extends AbstractFacade<Region> implements RegionFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }

    @Override
    public Region findByName(String name) {
        Query query = getEntityManager().createNamedQuery("Region.findByNomRegion");
        query.setParameter("nomRegion", name);
        return (Region) query.getSingleResult();
        
    }
    
}

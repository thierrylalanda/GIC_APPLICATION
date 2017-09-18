/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.MagasinPrincipal;
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
public class MagasinPrincipalFacade extends AbstractFacade<MagasinPrincipal> implements MagasinPrincipalFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MagasinPrincipalFacade() {
        super(MagasinPrincipal.class);
    }

    @Override
    public List<MagasinPrincipal> exite(int id) {
        Query query = getEntityManager().createNamedQuery("MagasinPrincipal.findByIdMagasin");
        query.setParameter("idMagasin", id);

        return query.getResultList();
    }

    @Override
    public MagasinPrincipal findByName(String name) {
        Query query = getEntityManager().createNamedQuery("MagasinPrincipal.findByName");
        query.setParameter("name", name);

        return (MagasinPrincipal) query.getSingleResult();
    }

    @Override
    public List<MagasinPrincipal> findAllByregion(String region) {

        Query query = getEntityManager().createNamedQuery("MagasinPrincipal.findByRegion");
        query.setParameter("region", region);

        return query.getResultList();
    }

    @Override
    public MagasinPrincipal lastInsert() {
        Query query = getEntityManager().createNamedQuery("MagasinPrincipal.findByLastIdMagasin");
        
        return (MagasinPrincipal) query.getSingleResult();
    }
}

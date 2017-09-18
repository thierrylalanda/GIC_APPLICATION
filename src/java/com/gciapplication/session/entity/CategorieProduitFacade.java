/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CategorieProduit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author messi
 */
@Stateless
public class CategorieProduitFacade extends AbstractFacade<CategorieProduit> implements CategorieProduitFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieProduitFacade() {
        super(CategorieProduit.class);
    }

    @Override
    public CategorieProduit findByTypeCategorie(String typeCategorie) {
        Query query = getEntityManager().createNamedQuery("CategorieProduit.findByTypeCategorie");
        query.setParameter("typeCategorie", typeCategorie);
        return (CategorieProduit) query.getSingleResult();
    }

    @Override
    public CategorieProduit findLastInsert() {
        Query query = getEntityManager().createNamedQuery("CategorieProduit.findLastInser");
        return (CategorieProduit) query.getSingleResult();
    }
    
}

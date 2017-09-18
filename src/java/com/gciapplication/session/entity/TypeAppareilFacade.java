/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.TypeAppareil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
@Stateless
public class TypeAppareilFacade extends AbstractFacade<TypeAppareil> implements TypeAppareilFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeAppareilFacade() {
        super(TypeAppareil.class);
    }

    @Override
    public TypeAppareil findByName(String type) {
        Query query = getEntityManager().createNamedQuery("TypeAppareil.findByNom");
        query.setParameter("nom", type);
        return (TypeAppareil) query.getSingleResult();
    }
    
}

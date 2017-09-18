/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.FrabriquantAppareil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author messi
 */
@Stateless
public class FrabriquantAppareilFacade extends AbstractFacade<FrabriquantAppareil> implements FrabriquantAppareilFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrabriquantAppareilFacade() {
        super(FrabriquantAppareil.class);
    }
    
}

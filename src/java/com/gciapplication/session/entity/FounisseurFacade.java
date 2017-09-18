/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Founisseur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author messi
 */
@Stateless
public class FounisseurFacade extends AbstractFacade<Founisseur> implements FounisseurFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FounisseurFacade() {
        super(Founisseur.class);
    }

    @Override
    public Founisseur findByNAME(String name) {
        Query query = getEntityManager().createNamedQuery("Founisseur.findByNomFounisseur");
        query.setParameter("nomFounisseur", name);
        return (Founisseur) query.getSingleResult();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Appariel;
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
public class ApparielFacade extends AbstractFacade<Appariel> implements ApparielFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApparielFacade() {
        super(Appariel.class);
    }

    @Override
    public List<Appariel> findAllApparielByPersonnel(int idPersonnel) {
        Query query = getEntityManager().createNamedQuery("Appariel.findAllApparielPersonnel").setParameter("idpersonnel", idPersonnel);
        return query.getResultList();
    }

    @Override
    public Appariel findDefautAppareil(int id) {
        Query query = getEntityManager().createNamedQuery("Appariel.findByDefautAppareil").setParameter("personnel", id);
        return (Appariel) query.getSingleResult();
    }

}

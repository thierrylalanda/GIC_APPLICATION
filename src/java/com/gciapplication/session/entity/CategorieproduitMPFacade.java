/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CategorieproduitMP;
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
public class CategorieproduitMPFacade extends AbstractFacade<CategorieproduitMP> implements CategorieproduitMPFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieproduitMPFacade() {
        super(CategorieproduitMP.class);
    }

    @Override
    public List<CategorieproduitMP> findCatByidMP(int idMP) {
        Query query = getEntityManager().createNamedQuery("CategorieproduitMP.findCategorieByidMP");
        query.setParameter("idMP", idMP);
        return query.getResultList();
    }
    
}

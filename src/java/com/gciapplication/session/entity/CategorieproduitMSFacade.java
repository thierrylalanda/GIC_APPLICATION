/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CategorieproduitMS;
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
public class CategorieproduitMSFacade extends AbstractFacade<CategorieproduitMS> implements CategorieproduitMSFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieproduitMSFacade() {
        super(CategorieproduitMS.class);
    }

    @Override
    public List<String> findAllD() {
        Query query = getEntityManager().createNamedQuery("CategorieproduitMS.findAl");
        return query.getResultList();
    }

    @Override
    public List<CategorieproduitMS> findCatByidMS(int idMS) {
        Query query = getEntityManager().createNamedQuery("CategorieproduitMS.findCategorieByidMS");
        query.setParameter("idMS", idMS);
        return query.getResultList();
    }
}

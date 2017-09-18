/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.MouvementProduits;
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
public class MouvementProduitsFacade extends AbstractFacade<MouvementProduits> implements MouvementProduitsFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MouvementProduitsFacade() {
        super(MouvementProduits.class);
    }

    @Override
    public List<MouvementProduits> findAllByIdMagasin(int idMagasin, int idtransfert) {
        Query query = getEntityManager().createNamedQuery("MouvementProduits.findByIdMP");
        query.setParameter("idMagasin", idMagasin);
        query.setParameter("idtransfert", idtransfert);
        return query.getResultList();
    }
}

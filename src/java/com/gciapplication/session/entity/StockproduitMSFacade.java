/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StockproduitMS;
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
public class StockproduitMSFacade extends AbstractFacade<StockproduitMS> implements StockproduitMSFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockproduitMSFacade() {
        super(StockproduitMS.class);
    }

    @Override
    public StockproduitMS findByDesignationByidMS(int idMS, String designation) {
        Query query = getEntityManager().createNamedQuery("StockproduitMS.findByDesignationByidMS");
        query.setParameter("idMagasin", idMS);
        query.setParameter("designation", designation);

        return (StockproduitMS) query.getSingleResult();

    }

    @Override
    public StockproduitMS findByCodeProduitByidMS(int idMS, String code) {
        Query query = getEntityManager().createNamedQuery("StockproduitMS.findBycodeProduitByidMS");
        query.setParameter("idMagasin", idMS);
        query.setParameter("codeProduit", code);

        return (StockproduitMS) query.getSingleResult();

    }

    @Override
    public List<StockproduitMS> ProduitCritique(int id) {
        final int quantite = 5;
        Query query = getEntityManager().createNamedQuery("StockproduitMS.findProduitCritique");
        query.setParameter("idMagasin", id);
        query.setParameter("critique", quantite);

        return query.getResultList();
    }

    @Override
    public List<StockproduitMS> ProduitWarmin(int id) {
        final int quantite = 5;
        final int warmin = 10;
        Query query = getEntityManager().createNamedQuery("StockproduitMS.findProduitWarmin");
        query.setParameter("idMagasin", id);
        query.setParameter("critique", quantite);
        query.setParameter("warmin", warmin);
        return query.getResultList();
    }

    @Override
    public List<StockproduitMS> findStockMP(int idMS) {
        Query query = getEntityManager().createNamedQuery("StockproduitMS.findStockByMS");
        query.setParameter("idMS", idMS);
        return query.getResultList();
    }

    @Override
    public List<StockproduitMS> findStockByIdCategorie(int idcategorie) {
        Query query = getEntityManager().createNamedQuery("StockproduitMS.findStockByidCategorie");
        query.setParameter("categorie", idcategorie);
        return query.getResultList();
    }

    @Override
    public StockproduitMS findLastInsert() {
        Query query = getEntityManager().createNamedQuery("StockproduitMS.findStockByLasInsert");        

        return (StockproduitMS) query.getSingleResult();
    }
}

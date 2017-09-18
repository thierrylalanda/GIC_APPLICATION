/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StockproduitMP;
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
public class StockproduitMPFacade extends AbstractFacade<StockproduitMP> implements StockproduitMPFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockproduitMPFacade() {
        super(StockproduitMP.class);
    }

    @Override
    public StockproduitMP findByCodeProduitByidMP(int idMP, String code) {
        Query query = getEntityManager().createNamedQuery("StockproduitMP.findBycodeProduitByidMP");
        query.setParameter("codeProduit", code);
        query.setParameter("idMP", idMP);

        return (StockproduitMP) query.getSingleResult();

    }

    @Override
    public StockproduitMP findByDesignationByidMP(int idMP, String designation) {
        Query query = getEntityManager().createNamedQuery("StockproduitMP.findByDesignation");
        query.setParameter("id", idMP);
        query.setParameter("designation", designation);

        return (StockproduitMP) query.getSingleResult();
    }

    @Override
    public List<StockproduitMP> ProduitCritique(int id) {
        final int quantite = 5;
        Query query = getEntityManager().createNamedQuery("StockproduitMP.findProduitCritique");
        query.setParameter("idMagasin", id);
        query.setParameter("critique", quantite);

        return query.getResultList();
    }

    @Override
    public List<StockproduitMP> ProduitWarmin(int id) {
        final int quantite = 5;
        final int warmin = 10;
        Query query = getEntityManager().createNamedQuery("StockproduitMP.findProduitWarmin");
        query.setParameter("idMagasin", id);
        query.setParameter("critique", quantite);
        query.setParameter("warmin", warmin);
        return query.getResultList();
    }

    @Override
    public List<StockproduitMP> findByidMP(int idMP) {
        Query query = getEntityManager().createNamedQuery("StockproduitMP.findByidMP");
        query.setParameter("idMagasin", idMP);
        return query.getResultList();
    }

    @Override
    public List<StockproduitMP> findStockByIdCategorie(int idcategorie) {
        Query query = getEntityManager().createNamedQuery("StockproduitMP.findStockByIdCategorie");
        query.setParameter("categorie", idcategorie);
        return query.getResultList();
    }

    @Override
    public StockproduitMP findLastInsert() {
        Query query = getEntityManager().createNamedQuery("StockproduitMP.findByLastInsert");

        return (StockproduitMP) query.getSingleResult();
    }
}

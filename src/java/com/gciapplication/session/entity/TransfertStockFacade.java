/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.TransfertStock;
import com.gciapplication.servlet.controleurs.magasinS.transfert;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author messi
 */
@Stateless
public class TransfertStockFacade extends AbstractFacade<TransfertStock> implements TransfertStockFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransfertStockFacade() {
        super(TransfertStock.class);
    }

    @Override
    public List<TransfertStock> findByVisaMSByidMS(String etat, int idMS) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByVisaMSByidMS");
        query.setParameter("idMagasin", idMS);
        query.setParameter("visaMS", etat);

        return query.getResultList();

    }

    @Override
    public List<TransfertStock> findByPeriode(Date d, Date d1, int id) {
        Timestamp t = new Timestamp(d.getTime());
        Timestamp t1 = new Timestamp(d1.getTime());
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByPeriode");
        query.setParameter("date1", t, TemporalType.TIMESTAMP);
        query.setParameter("date2", t1, TemporalType.TIMESTAMP);
        query.setParameter("idMP", id);
        return query.getResultList();
    }

    @Override
    public List<TransfertStock> findAllByIdMP(int id) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findAllByIdMP");
        query.setParameter("idMP", id);

        return query.getResultList();

    }

    @Override
    public List<TransfertStock> findAllByNameMs(String name) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findAllByNameMs");
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Override
    public List<String> findAllDinstinctByIdNameMS(int idMP) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findAllDinstinctByIdNameMS");
        query.setParameter("idMP", idMP);
        return query.getResultList();
    }

    @Override
    public List<TransfertStock> findByPeriodeMS(Date d, Date d1, int id, String designation) {
        Timestamp t = new Timestamp(d.getTime());
        Timestamp t1 = new Timestamp(d1.getTime());
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByPeriodeMS");
        query.setParameter("date1", t, TemporalType.TIMESTAMP);
        query.setParameter("date2", t1, TemporalType.TIMESTAMP);
        query.setParameter("idMS", id);

        System.out.println(t1);
        System.out.println(t);

        query.setParameter("designation", designation);
        return query.getResultList();
    }

    @Override
    public List<TransfertStock> findByDesignation(String designation, int idMS) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByDesignation");
        query.setParameter("designation", designation);
        query.setParameter("idMS", idMS);

        return query.getResultList();
    }

    @Override
    public List<TransfertStock> findByPeriodeMP(Date d, Date d1, int id, String designation) {
        Timestamp t = new Timestamp(d.getTime());
        Timestamp t1 = new Timestamp(d1.getTime());
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByPeriodeMP");
        query.setParameter("date1", t, TemporalType.TIMESTAMP);
        query.setParameter("date2", t1, TemporalType.TIMESTAMP);
        query.setParameter("idMP", id);
        query.setParameter("designation", designation);
        return query.getResultList();
    }

    @Override
    public List<TransfertStock> findByDesignationMP(String designation, int idMP) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByDesignationMP");
        query.setParameter("designation", designation);
        query.setParameter("idMP", idMP);

        return query.getResultList();
    }

    @Override
    public List<TransfertStock> findByVisaMPByidMP(String etat, int idMP) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByVisaMP");
        query.setParameter("idMagasin", idMP);
        query.setParameter("visaMS", etat);

        return query.getResultList();
    }

    @Override
    public TransfertStock findByCodeByVisaByIdMS(String code, String visa, int idMS) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByCodeProduit");
        query.setParameter("codeProduit", code);
        query.setParameter("visaMS", visa);
        query.setParameter("idMagasin", idMS);

        return (TransfertStock) query.getSingleResult();
    }

    @Override
    public List<TransfertStock> findByEtatByidMP(String etat, int idMP) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByEtatAndIdMP");
        query.setParameter("etat", etat);
        query.setParameter("idMP", idMP);
        return query.getResultList();
    }
    
      @Override
    public List<TransfertStock> findAllByCategorieAndMPAndDate(int idmagasinp, String categorie, java.util.Date datedebut, java.util.Date datefin) {
        Query query = getEntityManager().createNamedQuery("TransfertStock.findByCategorieAndMPAndDate");
        query.setParameter("idmagasinp", idmagasinp);
        query.setParameter("categorie", categorie);
        query.setParameter("datedebut", datedebut);
        query.setParameter("datefin", datefin);
        return query.getResultList();
    }
}

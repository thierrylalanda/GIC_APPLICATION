/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.OperationConsommateur;
import java.util.Date;
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
public class OperationConsommateurFacade extends AbstractFacade<OperationConsommateur> implements OperationConsommateurFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationConsommateurFacade() {
        super(OperationConsommateur.class);
    }

    @Override
    public List<OperationConsommateur> findConsommationMSByPeriode(Date d1, Date d2, int idMS) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByDate");
        query.setParameter("date1", d1);
        query.setParameter("date2", d2);
        query.setParameter("idMS", idMS);

        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findConsommationMSByPeriode(Date d1, Date d2, int idMS, String designation) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByDateByDesignation");
        query.setParameter("date1", d1);
        query.setParameter("date2", d2);
        query.setParameter("idMS", idMS);
        query.setParameter("designation", designation);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdMS(int id) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByIdMS");
        query.setParameter("idMS", id);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdMSAndDesignation(String designation, int id) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByDesignation");
        query.setParameter("designation", designation);
        query.setParameter("idMS", id);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdMP(int id) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByIdMP");
        query.setParameter("idMP", id);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdPersonnel(int idpersonnel) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByIdPersonnel");
        query.setParameter("personnel", idpersonnel);
        return query.getResultList();
    }

    @Override
    public List<String> findAllByCategorie(int idsite) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndSite");
        query.setParameter("idsite", idsite);
        return query.getResultList();
    }

    @Override
    public List<String> findAllByCaategorieAndRegion(int idregion) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndRegion");
        query.setParameter("idregion", idregion);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdMSAndCategorieAnNamePersonnel(String categorie, int name) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAnNamePersonnel");
        query.setParameter("categorie", categorie);
        query.setParameter("nom", name);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdPersonnelAndDate(int idpersonnel, java.sql.Date date) {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM operation_consommateur WHERE YEAR(operation_consommateur.`date`) = YEAR(?1) AND personnel = ?2", OperationConsommateur.class);
        query.setParameter("1", date);
        query.setParameter("2", idpersonnel);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdPersonnelAndDateNow(int idpersonnel) {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM operation_consommateur WHERE YEAR(operation_consommateur.`date`) = YEAR(NOW()) AND personnel = ?1", OperationConsommateur.class);
        query.setParameter("1", idpersonnel);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByIdMSAndCategorieAnNamePersonnelAndNow(String categorie, int name) {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM operation_consommateur WHERE YEAR(operation_consommateur.`date`) = YEAR(NOW()) AND personnel = ?1 AND operation_consommateur.categorie = ?2", OperationConsommateur.class);
        query.setParameter("1", name);
        query.setParameter("2", categorie);
        return query.getResultList();
    }

    @Override
    public int findAllByIdMSAndCategorieAnNamePersonnelAndSumQt(String categorie) {
        Query query = getEntityManager().createNativeQuery("SELECT SUM(operation_consommateur.quantite) AS quantite FROM operation_consommateur WHERE DATE(operation_consommateur.`date`) = DATE(operation_consommateur.`date`) AND operation_consommateur.categorie = ?2", OperationConsommateur.class);

        query.setParameter("2", categorie);
        return (int) query.getParameterValue("quantite");
    }

    @Override
    public List<OperationConsommateur> findAllByCategorieAndRegionAndDate(int idregion, String categorie, Date datedebut, Date datefin) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndRegionAndDate");
        query.setParameter("idregion", idregion);
        query.setParameter("categorie", categorie);
        query.setParameter("datedebut", datedebut);
        query.setParameter("datefin", datefin);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByCategorieAndMSAndDate(int idmagasin, String categorie, Date datedebut, Date datefin) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndMSAndDate");
        query.setParameter("idmagasin", idmagasin);
        query.setParameter("categorie", categorie);
        query.setParameter("datedebut", datedebut);
        query.setParameter("datefin", datefin);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByCategorieAndDirectionAndDate(int iddirection, String categorie, Date datedebut, Date datefin) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndDirectionAndDate");
        query.setParameter("iddirection", iddirection);
        query.setParameter("categorie", categorie);
        query.setParameter("datedebut", datedebut);
        query.setParameter("datefin", datefin);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByCategorieAndSiteAndDate(int idsite, String categorie, Date datedebut, Date datefin) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndSiteAndDate");
        query.setParameter("idsite", idsite);
        query.setParameter("categorie", categorie);
        query.setParameter("datedebut", datedebut);
        query.setParameter("datefin", datefin);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByCategorieAndServiceAndDate(int idservice, String categorie, Date datedebut, Date datefin) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndServiceAndDate");
        query.setParameter("idservice", idservice);
        query.setParameter("categorie", categorie);
        query.setParameter("datedebut", datedebut);
        query.setParameter("datefin", datefin);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByCategorieAndPersonnelAndDate(int idpersonnel, String categorie, Date datedebut, Date datefin) {
        Query query = getEntityManager().createNamedQuery("OperationConsommateur.findByCategorieAndPersonnelAndDate");
        query.setParameter("idpersonnel", idpersonnel);
        query.setParameter("categorie", categorie);
        query.setParameter("datedebut", datedebut);
        query.setParameter("datefin", datefin);
        return query.getResultList();
    }

    @Override
    public List<OperationConsommateur> findAllByDateSortiByPersonnel(int personnel, Date d1, Date d2) {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM operation_consommateur WHERE operation_consommateur.personnel = ?1 AND DATE(operation_consommateur.`date`) BETWEEN ?2 AND ?3", OperationConsommateur.class);
        query.setParameter("1", personnel);
        query.setParameter("2", d1);
        query.setParameter("3", d2);
        return query.getResultList();
    }
}

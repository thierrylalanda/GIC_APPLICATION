/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Personnel;
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
public class PersonnelFacade extends AbstractFacade<Personnel> implements PersonnelFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelFacade() {
        super(Personnel.class);
    }

    @Override
    public Personnel findByID(int ID) {

        Query query = getEntityManager().createNamedQuery("Personnel.findByID").setParameter("idPersonnel", ID);

        return (Personnel) query.getSingleResult();
    }

    @Override
    public Personnel findByNomPrenom(String ref) {

        Query query = getEntityManager().createNamedQuery("Personnel.findByNomPrenom").setParameter("nomPrenom", ref);

        return (Personnel) query.getSingleResult();
    }

    @Override
    public Personnel findByMatricule(String mat) {
        Query query = getEntityManager().createNamedQuery("Personnel.findByMatricule").setParameter("matricule", mat);
        return (Personnel) query.getSingleResult();
    }

    @Override
    public List<Personnel> findRole(String role) {
        Query query = getEntityManager().createNamedQuery("Personnel.findByRole");
        query.setParameter("role", role);

        return query.getResultList();
    }

    @Override
    public List<Personnel> findRoleByRegion(String role, String region) {
        Query query = getEntityManager().createNamedQuery("Personnel.findByRoleByRegion");
        query.setParameter("role", role);
        query.setParameter("region", region);
        return query.getResultList();
    }

    @Override
    public Personnel findMAXIdPersonnel() {
        Query query = getEntityManager().createNamedQuery("Personnel.findMAXIdPersonnel");

        return (Personnel) query.getSingleResult();
    }

    @Override
    public void Refrech(Personnel p) {
        getEntityManager().refresh(p);
    }

    @Override
    public Personnel findByMatriculeByMail(String mat, String mail) {
        Query query = getEntityManager().createNamedQuery("Personnel.findByMatriculeByMail");
        query.setParameter("matricule", mat);
        query.setParameter("email", mail);
        return (Personnel) query.getSingleResult();
    }

    @Override
    public List<Personnel> findAllByIdService(int idService) {
        Query query = getEntityManager().createNamedQuery("Personnel.findByIdService");
        query.setParameter("idService", idService);

        return query.getResultList();
    }

    @Override
    public List<Personnel> findAllByIdSite(int site) {
        Query query = getEntityManager().createNamedQuery("Personnel.findBySite");
        query.setParameter("site", site);

        return query.getResultList();
    }

    @Override
    public List<Personnel> findAllByIdRegion(int region) {
        Query query = getEntityManager().createNamedQuery("Personnel.findByRegion");
        query.setParameter("region", region);

        return query.getResultList();
    }

    @Override
    public List<Personnel> findAllByIdDirection(int direction) {
        Query query = getEntityManager().createNamedQuery("Personnel.findByDirection");
        query.setParameter("direction", direction);

        return query.getResultList();
    }
}

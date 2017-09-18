/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Bordereau;
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
public class BordereauFacade extends AbstractFacade<Bordereau> implements BordereauFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BordereauFacade() {
        super(Bordereau.class);
    }

    @Override
    public List<Bordereau> findByPeriodeMP(Date d, Date d1, int id, String designation) {
        Query query = getEntityManager().createNamedQuery("Bordereau.findByPeriodeMP");
        query.setParameter("date1", d);
        query.setParameter("date2", d1);
        query.setParameter("idMP", id);
        query.setParameter("designation", designation);
        return query.getResultList();
    }

    @Override
    public List<Bordereau> findByDesignationMP(String designation, int idMP) {
        Query query = getEntityManager().createNamedQuery("Bordereau.findByDesignationMP");
        query.setParameter("designation", designation);
        query.setParameter("idMP", idMP);

        return query.getResultList();
    }

    @Override
    public List<Bordereau> findAllByIdMP(int id) {
        Query query = getEntityManager().createNamedQuery("Bordereau.findAllByIdMP").setParameter("idMP", id);
        return query.getResultList();
    }
}

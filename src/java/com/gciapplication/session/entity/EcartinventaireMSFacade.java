/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.EcartinventaireMS;
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
public class EcartinventaireMSFacade extends AbstractFacade<EcartinventaireMS> implements EcartinventaireMSFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EcartinventaireMSFacade() {
        super(EcartinventaireMS.class);
    }
    @Override
    public List<EcartinventaireMS> findDate(Date date1, Date date2, int idMS) {
        Query query = getEntityManager().createNamedQuery("EcartinventaireMS.findByDate");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        query.setParameter("idMS", idMS);

        return query.getResultList();

    }

    @Override
    public List<EcartinventaireMS> findAllByIdMS(int id) {
        Query query = getEntityManager().createNamedQuery("EcartinventaireMS.findAllByIdMS");

        query.setParameter("idMS", id);

        return query.getResultList();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.VisaChef;
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
public class VisaChefFacade extends AbstractFacade<VisaChef> implements VisaChefFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VisaChefFacade() {
        super(VisaChef.class);
    }

    @Override
    public List<VisaChef> findVisaByIdMP(int id) {
        Query query = getEntityManager().createNamedQuery("VisaChef.findByIdMP").setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public List<String> findControleurByIdMP(int id) {
        Query query = getEntityManager().createNamedQuery("VisaChef.findNameByIdMP").setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public List<VisaChef> findVisaByIdCommande(int id_commande) {
        Query query = getEntityManager().createNamedQuery("VisaChef.findByIdMP");
        query.setParameter("id", id_commande);

        return query.getResultList();
    }
}

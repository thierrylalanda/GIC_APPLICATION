/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.OrdreControleur;
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
public class OrdreControleurFacade extends AbstractFacade<OrdreControleur> implements OrdreControleurFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdreControleurFacade() {
        super(OrdreControleur.class);
    }
    @Override
    public List<OrdreControleur> findAllByNiveau(int niveau, int id) {
        Query query = getEntityManager().createNamedQuery("OrdreControleur.findByNiveauAnIdResponsable");
        query.setParameter("niveau", niveau);
        query.setParameter("controleur", id);

        return query.getResultList();
    }

    @Override
    public void Delete(int id) {
        Query query = getEntityManager().createNamedQuery("OrdreControleur.Delete");

        query.setParameter("id", id);
    }

    @Override
    public int[] findAllByNiveaux() {
        Query query = getEntityManager().createNamedQuery("OrdreControleur.findByNiveaux");

        int[] integer = new int[query.getResultList().size()];
        for (int i = 0; i < query.getResultList().size(); i++) {
            integer[i] = (int) query.getResultList().get(i);
        }
        return integer;
    }

    @Override
    public List<OrdreControleur> findIDControleurs(int id) {
        Query query = getEntityManager().createNamedQuery("OrdreControleur.findIDControleur").setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<OrdreControleur> findAllByIdMP(int id) {
        Query query = getEntityManager().createNamedQuery("OrdreControleur.findAll").setParameter("id", id);
        return query.getResultList();
    }
}

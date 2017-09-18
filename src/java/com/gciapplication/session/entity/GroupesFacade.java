/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Groupes;
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
public class GroupesFacade extends AbstractFacade<Groupes> implements GroupesFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupesFacade() {
        super(Groupes.class);
    }

    @Override
    public List<Groupes> findByNiveau(int niveau) {
        Query query = getEntityManager().createNamedQuery("Groupes.findByNiveau");
        query.setParameter("niveau", niveau);
        return query.getResultList();

    }

    @Override
    public Groupes findLastInsert() {
        Query query = getEntityManager().createNamedQuery("Groupes.findLastInsert");
        return (Groupes) query.getSingleResult();
    }

    @Override
    public List<Groupes> findByNiveauByRegion(int niveau, int idRegion) {
        Query query = getEntityManager().createNamedQuery("Groupes.findByNiveauAndRegion");
        query.setParameter("niveau", niveau);
        query.setParameter("idregion", idRegion);
        return query.getResultList();
    }

    @Override
    public Groupes findByNameGroupe(String nameGroupe) {
        Query query = getEntityManager().createNamedQuery("Groupes.findByNomGroupe");
        query.setParameter("nomGroupe", nameGroupe);
        return (Groupes) query.getSingleResult();
    }

    @Override
    public List<Groupes> findByAllDefaultGroupe() {
        Query query = getEntityManager().createNamedQuery("Groupes.findByNomGroupeLike");
        return query.getResultList();
    }
}

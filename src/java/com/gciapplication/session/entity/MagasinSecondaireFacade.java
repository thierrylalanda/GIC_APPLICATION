/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.MagasinSecondaire;
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
public class MagasinSecondaireFacade extends AbstractFacade<MagasinSecondaire> implements MagasinSecondaireFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MagasinSecondaireFacade() {
        super(MagasinSecondaire.class);
    }

    @Override
    public MagasinSecondaire findByNam(String name) {
        Query query = getEntityManager().createNamedQuery("MagasinSecondaire.findByNom").setParameter("nom", name);
        return (MagasinSecondaire) query.getSingleResult();
    }

    @Override
    public List<MagasinSecondaire> findByRegion(String region) {
        Query query = getEntityManager().createNamedQuery("MagasinSecondaire.findByRegion").setParameter("region", region);

        return query.getResultList();
    }

    @Override
    public MagasinSecondaire lastInsert() {
        Query query = getEntityManager().createNamedQuery("MagasinSecondaire.findLastInsert");
        return (MagasinSecondaire) query.getSingleResult();
    }

    @Override
    public List<MagasinSecondaire> findBySite(int site) {
        Query query = getEntityManager().createNamedQuery("MagasinSecondaire.findBySite").setParameter("site", site);

        return query.getResultList();
    }

    @Override
    public List<MagasinSecondaire> findByIdregion(int idregion) {
        Query query = getEntityManager().createNamedQuery("MagasinSecondaire.findByIdRegion").setParameter("idregion", idregion);

        return query.getResultList();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CommandeMS;
import com.gciapplication.entity.MagasinSecondaire;
import java.util.ArrayList;
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
public class CommandeMSFacade extends AbstractFacade<CommandeMS> implements CommandeMSFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeMSFacade() {
        super(CommandeMS.class);
    }

    @Override
    public List<CommandeMS> findByEtatCommande(String etat, int idMS) {
        List<CommandeMS> l = new ArrayList<>();
        Query query = getEntityManager().createNamedQuery("CommandeMS.findByEtatCommande");
        query.setParameter("idMagasin", idMS);
         query.setParameter("etatCommande", etat);
        
        return query.getResultList();
    }

    @Override
    public List<CommandeMS> findByEtatCommandeForMP(String etat, int idMP) {
        Query query = getEntityManager().createNamedQuery("CommandeMS.findByEtatCommandeForMP");
        query.setParameter("idMagasin", idMP);
        query.setParameter("etatCommande", etat);

        return query.getResultList();
    }

    @Override
    public List<MagasinSecondaire> findByEtatCommandeForMPDistintc(String etat, int idMP) {
        Query query = getEntityManager().createNamedQuery("CommandeMS.findByEtatCommandeForMPDisting");

        query.setParameter("idMagasin", idMP);
        query.setParameter("etatCommande", etat);

        return query.getResultList();
    }

    @Override
    public CommandeMS findByCode(String code, int id) {
        Query query = getEntityManager().createNamedQuery("CommandeMS.findByCodeProduit");
        query.setParameter("codeProduit", code);
        query.setParameter("idMS", id);
        return (CommandeMS) query.getSingleResult();
    }

    @Override
    public CommandeMS findByCodeByEtat(String code, int id, String etat) {
        Query query = getEntityManager().createNamedQuery("CommandeMS.findByCodeProduitByEtat");
        query.setParameter("codeProduit", code);
        query.setParameter("idMS", id);
        query.setParameter("etatCommande", etat);
        return (CommandeMS) query.getSingleResult();
    }
}

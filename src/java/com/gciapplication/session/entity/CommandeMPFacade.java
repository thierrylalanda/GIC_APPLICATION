/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CommandeMP;
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
public class CommandeMPFacade extends AbstractFacade<CommandeMP> implements CommandeMPFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeMPFacade() {
        super(CommandeMP.class);
    }

    @Override
    public List<CommandeMP> findByIdMPAndEtat(int id, String etat) {

        Query query = getEntityManager().createNamedQuery("CommandeMP.findByIdMPAndEtat");
        query.setParameter("idMagasin", id);
        query.setParameter("etat", etat);
        return query.getResultList();
    }

    @Override
    public List<CommandeMP> findByIdMPAndEtatOrOk(int id, String etat, String Ok) {
        Query query = getEntityManager().createNamedQuery("CommandeMP.findByIdMPAndEtatOrOk");
        query.setParameter("idMagasin", id);
        query.setParameter("etat", etat);
        query.setParameter("ok", Ok);
        return query.getResultList();
    }

    @Override
    public List<CommandeMP> findByRecenDate(int id) {
        Query query = getEntityManager().createNamedQuery("CommandeMP.findByRecenDate");
        query.setParameter("idMagasin", id);

        return query.getResultList();
    }

    @Override
    public List<CommandeMP> findByIdMPAndEtatIndice(int id, String etat, int indice) {
        Query query = getEntityManager().createNamedQuery("CommandeMP.findByIdMPAndEtatIndice");
        query.setParameter("idMagasin", id);
        query.setParameter("indice", indice);
        query.setParameter("etat", etat);
        return query.getResultList();
    }

    @Override
    public List<CommandeMP> findByIdMPAndIdFournisseur(int idFournisseur, int idMP, String livraison) {
        Query query = getEntityManager().createNamedQuery("CommandeMP.findByIdFournisseurAndidMP");
        query.setParameter("magasin", idMP);
        query.setParameter("fournisseur", idFournisseur);
        query.setParameter("livrer", livraison);
        query.setParameter("etat", "OK");
        return query.getResultList();
    }

    @Override
    public List<CommandeMP> findByIdMPAndLivraison(int idMP, String livraison) {
 Query query = getEntityManager().createNamedQuery("CommandeMP.findByLivraisonAndidMP");
        query.setParameter("magasin", idMP);
        query.setParameter("livrer", livraison);
        return query.getResultList();
    }
}

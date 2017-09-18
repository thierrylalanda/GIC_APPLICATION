/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CommandePersonnel;
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
public class CommandePersonnelFacade extends AbstractFacade<CommandePersonnel> implements CommandePersonnelFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandePersonnelFacade() {
        super(CommandePersonnel.class);
    }

    @Override
    public List<CommandePersonnel> findpersonnelCommande(int idP, int idMS, String etat) {
        Query query = getEntityManager().createNamedQuery("CommandePersonnel.findByEtatCommande");
        query.setParameter("etatCommande", etat);
        query.setParameter("idpersonnel", idP);
        query.setParameter("idMagasin", idMS);
        return query.getResultList();
    }

    @Override
    public List<CommandePersonnel> findpersonnelCommandeByidpersonnel(int idP, String etat) {
        Query query = getEntityManager().createNamedQuery("CommandePersonnel.findByEtatCommandeByidpersonnel");
        query.setParameter("etatCommande", etat);
        query.setParameter("idpersonnel", idP);

        return query.getResultList();
    }

    @Override
    public List<CommandePersonnel> findCommandeRecus(int idMS, String etat) {
        Query query = getEntityManager().createNamedQuery("CommandePersonnel.findByEtatCommandeMS");
        query.setParameter("etatCommande", etat);
        query.setParameter("idMagasin", idMS);
        return query.getResultList();
    }

    @Override
    public List<Personnel> findByEtatCommandeReturnPersonel(int idMS, String etat) {
        Query query = getEntityManager().createNamedQuery("CommandePersonnel.findByEtatCommandeReturnPersonel");
        query.setParameter("etatCommande", etat);
        query.setParameter("idMagasin", idMS);
        return query.getResultList();

    }

    @Override
    public List<CommandePersonnel> findCommandeNonReceptionner(int personnel, String reception) {
        Query query = getEntityManager().createNamedQuery("CommandePersonnel.findByReception");
        query.setParameter("reception", reception);
        query.setParameter("personnel", personnel);

        return query.getResultList();
    }
}

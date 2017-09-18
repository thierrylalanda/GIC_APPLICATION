/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Permissions;
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
public class PermissionsFacade extends AbstractFacade<Permissions> implements PermissionsFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermissionsFacade() {
        super(Permissions.class);
    }

    @Override
    public List<Permissions> findAccesPage(int id) {
        Query query = getEntityManager().createNamedQuery("Permissions.findByIdPersonnel").setParameter("idPersonnel", id);
        return query.getResultList();
    }

    @Override
    public List<Permissions> findAllByIdGrpoupe(int idGroupe) {
        Query query = getEntityManager().createNamedQuery("Permissions.findAllByIdGroupe").setParameter("idgroupe", idGroupe);
        return query.getResultList();
    }

    @Override
    public void removeByIdGroupe(int idGroupe) {
        Query query = getEntityManager().createNamedQuery("Permissions.DeleteByIdGroupe").setParameter("idGroupe", idGroupe);
        query.executeUpdate();
    }
}

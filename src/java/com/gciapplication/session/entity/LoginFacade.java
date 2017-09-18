/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Login;
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
public class LoginFacade extends AbstractFacade<Login> implements LoginFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }

    @Override
    public ArrayList<Login> findAl(String password) {
        Query q = getEntityManager().createNamedQuery("Login.findByPassword").setParameter("password", password);
        return (ArrayList<Login>) q.getResultList();
    }

    @Override
    public List<Login> findAlUser(String username, String password) {
        Query query = getEntityManager().createNamedQuery("Login.findByUsername");
        query.setParameter("username", username);
        query.setParameter("password", password);

        return query.getResultList();
    }

    @Override
    public Login findBypersonnelID(int idPersonnel) {
        Query query = getEntityManager().createNamedQuery("Login.findLoginByPersonnel").setParameter("id", idPersonnel);
        return (Login) query.getSingleResult();
    }

    @Override
    public List<Login> findAllByIdGroupe(int idGroupe) {
        Query query = getEntityManager().createNamedQuery("Login.findByIdGroupe");
        query.setParameter("groupe", idGroupe);

        return query.getResultList();
    }
}

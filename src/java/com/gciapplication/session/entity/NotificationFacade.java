/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Notification;
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
public class NotificationFacade extends AbstractFacade<Notification> implements NotificationFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificationFacade() {
        super(Notification.class);
    }
    @Override
    public List<Notification> findAllNotificationForOne(int id, int etat) {
        Query query = getEntityManager().createNamedQuery("Notification.findByRecepteur");
        query.setParameter("recepteur", id);
        query.setParameter("etat", etat);

        return query.getResultList();
    }
    
     @Override
    public List<Notification> findAllNotificationForPersonnel(int id, int etat) {
        Query query = getEntityManager().createNamedQuery("Notification.findByPersonnelAndVue");
        query.setParameter("recepteur", id);
        query.setParameter("etat", etat);

        return query.getResultList();
    }


    @Override
    public List<Notification> findAllNotification(int id) {
        Query query = getEntityManager().createNamedQuery("Notification.findByExpediteur");
        query.setParameter("expediteur", id);
        
        return query.getResultList();
    }
}

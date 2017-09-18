/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Page;
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
public class PageFacade extends AbstractFacade<Page> implements PageFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Page> findByNiveau(int idNiveau) {
        Query query=getEntityManager().createNamedQuery("Page.findByNiveau");
        query.setParameter("niveau", idNiveau);
      return query.getResultList();   
    }
    
    public PageFacade() {
        super(Page.class);
    }
    
}

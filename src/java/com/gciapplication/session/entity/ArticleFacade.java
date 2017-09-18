/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Article;
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
public class ArticleFacade extends AbstractFacade<Article> implements ArticleFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

    @Override
    public List<Article> findAllByCategorieProduit(int idCategorieProduit) {
        Query query = getEntityManager().createNamedQuery("Article.findByIdCategorieProduit");
        query.setParameter("categorie", idCategorieProduit);
        return query.getResultList();
    }

    @Override
    public Article findAllByDesignation(String designation) {
        Query query = getEntityManager().createNamedQuery("Article.findByDesignation");
        query.setParameter("designation", designation);
        return (Article)query.getSingleResult();
    }

}

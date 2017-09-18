/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Article;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface ArticleFacadeLocal {

    void create(Article article);

    void edit(Article article);

    void remove(Article article);

    Article find(Object id);

    List<Article> findAll();
    
    List<Article> findAllByCategorieProduit(int idCategorieProduit);
    Article findAllByDesignation(String designation);

    List<Article> findRange(int[] range);

    int count();
    
}

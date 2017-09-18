/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CategorieProduit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface CategorieProduitFacadeLocal {

    void create(CategorieProduit categorieProduit);

    void edit(CategorieProduit categorieProduit);

    void remove(CategorieProduit categorieProduit);

    CategorieProduit find(Object id);
    
     CategorieProduit findByTypeCategorie(String typeCategorie);
     
     CategorieProduit findLastInsert();

    List<CategorieProduit> findAll();

    List<CategorieProduit> findRange(int[] range);

    int count();
    
}

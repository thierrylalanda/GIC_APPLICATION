/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.TypeAppareil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface TypeAppareilFacadeLocal {

    void create(TypeAppareil typeAppareil);

    void edit(TypeAppareil typeAppareil);

    void remove(TypeAppareil typeAppareil);

    TypeAppareil find(Object id);
    
     TypeAppareil findByName(String type);

    List<TypeAppareil> findAll();

    List<TypeAppareil> findRange(int[] range);

    int count();
    
}

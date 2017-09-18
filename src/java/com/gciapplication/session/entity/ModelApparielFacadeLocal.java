/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.ModelAppariel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface ModelApparielFacadeLocal {

    void create(ModelAppariel modelAppariel);

    void edit(ModelAppariel modelAppariel);

    void remove(ModelAppariel modelAppariel);

    ModelAppariel find(Object id);

    List<ModelAppariel> findAll();

    List<ModelAppariel> findRange(int[] range);

    int count();
    
}

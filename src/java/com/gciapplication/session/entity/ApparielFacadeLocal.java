/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Appariel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface ApparielFacadeLocal {

    void create(Appariel appariel);

    void edit(Appariel appariel);

    void remove(Appariel appariel);

    Appariel find(Object id);
    
    Appariel findDefautAppareil(int id);

    List<Appariel> findAll();

    List<Appariel> findRange(int[] range);

    int count();

    List<Appariel> findAllApparielByPersonnel(int idPersonnel);
}

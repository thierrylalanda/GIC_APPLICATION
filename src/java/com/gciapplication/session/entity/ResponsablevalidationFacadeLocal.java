/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Responsablevalidation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface ResponsablevalidationFacadeLocal {

    void create(Responsablevalidation responsablevalidation);

    void edit(Responsablevalidation responsablevalidation);

    void remove(Responsablevalidation responsablevalidation);

    Responsablevalidation find(Object id);

    List<Responsablevalidation> findAll();

    List<Responsablevalidation> findRange(int[] range);

    int count();
    
}

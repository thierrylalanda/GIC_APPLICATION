/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Actions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface ActionsFacadeLocal {

    void create(Actions actions);

    void edit(Actions actions);

    void remove(Actions actions);

    Actions find(Object id);

    List<Actions> findAll();

    List<Actions> findRange(int[] range);

    int count();
    
}

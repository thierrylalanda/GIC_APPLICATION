/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Direction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface DirectionFacadeLocal {

    void create(Direction direction);

    void edit(Direction direction);

    void remove(Direction direction);

    Direction find(Object id);

    List<Direction> findAll();

    List<Direction> findRange(int[] range);

    int count();
    
}

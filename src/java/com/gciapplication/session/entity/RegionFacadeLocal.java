/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Region;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface RegionFacadeLocal {

    void create(Region region);

    void edit(Region region);

    void remove(Region region);

    Region find(Object id);
    
    Region findByName(String name);

    List<Region> findAll();

    List<Region> findRange(int[] range);

    int count();
    
}

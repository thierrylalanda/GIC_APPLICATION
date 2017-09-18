/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Butget;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface ButgetFacadeLocal {

    void create(Butget butget);

    void edit(Butget butget);

    void remove(Butget butget);

    Butget find(Object id);
    
    Butget findOnBudgetForService(int idMS, int idtypeProduit,String typeBudget);

    List<Butget> findAll();
    
     List<Butget> findAllBudgetByidMS(int idMS);
    
    List<Butget> findLatButget();

    List<Butget> findRange(int[] range);
    
    List<Butget> findByRegion(String region);
    List<String> findByRegionAndTypeBudget(String region);

    int count();
    
}

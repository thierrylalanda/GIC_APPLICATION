/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Service;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface ServiceFacadeLocal {

    void create(Service service);

    void edit(Service service);

    void remove(Service service);

    Service find(Object id);
    
     Service findByNameService(String service);
    
    List<Service> findAll();
    
     List<Service> findAllByIdDirection(int idDirection);
    
    List<Service> findAllByIdSte(int idSite);
    
     List<Service> findAllBylibelleCentrecout(String libelle);

    List<Service> findRange(int[] range);

    int count();

     List<Service> findAllServiseByRegion(String region);
     
    Service lastInsert();
     
}

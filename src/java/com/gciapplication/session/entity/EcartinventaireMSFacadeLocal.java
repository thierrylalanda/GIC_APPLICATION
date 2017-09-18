/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.EcartinventaireMS;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface EcartinventaireMSFacadeLocal {

    void create(EcartinventaireMS ecartinventaireMS);

    void edit(EcartinventaireMS ecartinventaireMS);

    void remove(EcartinventaireMS ecartinventaireMS);

    EcartinventaireMS find(Object id);

    List<EcartinventaireMS> findAll();

    List<EcartinventaireMS> findRange(int[] range);

    int count();

    List<EcartinventaireMS> findDate(Date date1, Date date2, int idMS);

    List<EcartinventaireMS> findAllByIdMS(int id);
}

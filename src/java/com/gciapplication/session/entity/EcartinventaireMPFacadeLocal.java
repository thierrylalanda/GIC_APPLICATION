/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.EcartinventaireMP;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface EcartinventaireMPFacadeLocal {

    void create(EcartinventaireMP ecartinventaireMP);

    void edit(EcartinventaireMP ecartinventaireMP);

    void remove(EcartinventaireMP ecartinventaireMP);

    EcartinventaireMP find(Object id);

    List<EcartinventaireMP> findAll();

    List<EcartinventaireMP> findRange(int[] range);

    int count();

    List<EcartinventaireMP> findByPeriode(Date d, Date d1, int id);

    List<EcartinventaireMP> findAllById(int id);
}

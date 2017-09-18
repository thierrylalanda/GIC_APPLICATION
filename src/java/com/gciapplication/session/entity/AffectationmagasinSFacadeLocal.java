/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.AffectationmagasinS;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface AffectationmagasinSFacadeLocal {

    void create(AffectationmagasinS affectationmagasinS);

    void edit(AffectationmagasinS affectationmagasinS);

    void remove(AffectationmagasinS affectationmagasinS);

    AffectationmagasinS find(Object id);
    
    AffectationmagasinS findOnByIdPersonnel(int id);

    List<AffectationmagasinS> findAll();
    
    List<AffectationmagasinS> findByIDPersonnel(int idPersonnel);
    
     List<AffectationmagasinS> findAllByIdMS(int idMS);

    List<AffectationmagasinS> findRange(int[] range);

    int count();
    
}

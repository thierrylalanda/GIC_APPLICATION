/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.AffectationmagasinP;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface AffectationmagasinPFacadeLocal {

    void create(AffectationmagasinP affectationmagasinP);

    void edit(AffectationmagasinP affectationmagasinP);

    void remove(AffectationmagasinP affectationmagasinP);

    AffectationmagasinP find(Object id);
    
    AffectationmagasinP findOnByIdPersonnel(int id);

    List<AffectationmagasinP> findAll();
    
     List<AffectationmagasinP> findByIDPersonnel(int idPersonnel);

    List<AffectationmagasinP> findRange(int[] range);
    
     List<AffectationmagasinP> findAllByIdMS(int idMP);

    int count();
    
}

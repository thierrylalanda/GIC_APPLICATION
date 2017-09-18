/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.AffectationControleurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface AffectationControleursFacadeLocal {

    void create(AffectationControleurs affectationControleurs);

    void edit(AffectationControleurs affectationControleurs);

    void remove(AffectationControleurs affectationControleurs);

    AffectationControleurs find(Object id);

    AffectationControleurs findOnByIdPersonnel(int id);

    List<AffectationControleurs> findLastInsert();

    List<AffectationControleurs> findAll();

    List<AffectationControleurs> findByidResponsable(int idRespo);

    List<AffectationControleurs> findByIDPersonnel(int idPersonnel);

    List<AffectationControleurs> findRange(int[] range);

    int count();

}

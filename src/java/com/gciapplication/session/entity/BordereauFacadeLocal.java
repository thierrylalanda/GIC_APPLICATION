/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Bordereau;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface BordereauFacadeLocal {

    void create(Bordereau bordereau);

    void edit(Bordereau bordereau);

    void remove(Bordereau bordereau);

    Bordereau find(Object id);

    List<Bordereau> findAll();

    List<Bordereau> findRange(int[] range);

    int count();

    List<Bordereau> findByPeriodeMP(Date d, Date d1, int id, String designation);

    List<Bordereau> findByDesignationMP(String designation, int idMP);

    List<Bordereau> findAllByIdMP(int id);
}

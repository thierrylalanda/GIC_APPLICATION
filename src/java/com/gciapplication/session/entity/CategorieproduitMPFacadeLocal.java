/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CategorieproduitMP;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface CategorieproduitMPFacadeLocal {

    void create(CategorieproduitMP categorieproduitMP);

    void edit(CategorieproduitMP categorieproduitMP);

    void remove(CategorieproduitMP categorieproduitMP);

    CategorieproduitMP find(Object id);

    List<CategorieproduitMP> findAll();

    List<CategorieproduitMP> findCatByidMP(int idMP);

    List<CategorieproduitMP> findRange(int[] range);

    int count();

}

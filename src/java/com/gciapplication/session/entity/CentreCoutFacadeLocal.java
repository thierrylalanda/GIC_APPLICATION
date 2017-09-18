/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CentreCout;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface CentreCoutFacadeLocal {

    void create(CentreCout centreCout);

    void edit(CentreCout centreCout);

    void remove(CentreCout centreCout);

    CentreCout find(Object id);

    List<CentreCout> findAll();

    List<CentreCout> findRange(int[] range);

    int count();
    
}

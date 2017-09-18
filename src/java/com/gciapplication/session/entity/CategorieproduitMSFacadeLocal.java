/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CategorieproduitMS;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface CategorieproduitMSFacadeLocal {

    void create(CategorieproduitMS categorieproduitMS);

    void edit(CategorieproduitMS categorieproduitMS);

    void remove(CategorieproduitMS categorieproduitMS);

    CategorieproduitMS find(Object id);

    List<CategorieproduitMS> findAll();
    
    List<CategorieproduitMS> findCatByidMS(int idMS);

    List<CategorieproduitMS> findRange(int[] range);

    int count();

    List<String> findAllD();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.MagasinSecondaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface MagasinSecondaireFacadeLocal {

    void create(MagasinSecondaire magasinSecondaire);

    void edit(MagasinSecondaire magasinSecondaire);

    void remove(MagasinSecondaire magasinSecondaire);

    MagasinSecondaire find(Object id);

    List<MagasinSecondaire> findAll();

    List<MagasinSecondaire> findByRegion(String region);
    
    List<MagasinSecondaire> findBySite(int site);
    
    List<MagasinSecondaire> findByIdregion(int idregion);

    List<MagasinSecondaire> findRange(int[] range);

    int count();

    MagasinSecondaire findByNam(String name);

    MagasinSecondaire lastInsert();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.MagasinPrincipal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface MagasinPrincipalFacadeLocal {

    void create(MagasinPrincipal magasinPrincipal);

    void edit(MagasinPrincipal magasinPrincipal);

    void remove(MagasinPrincipal magasinPrincipal);

    MagasinPrincipal find(Object id);

    List<MagasinPrincipal> findAll();

    List<MagasinPrincipal> findAllByregion(String region);

    List<MagasinPrincipal> findRange(int[] range);

    int count();

    List<MagasinPrincipal> exite(int id);

    MagasinPrincipal findByName(String name);

    MagasinPrincipal lastInsert();
}

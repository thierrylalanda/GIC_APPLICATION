/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.FrabriquantAppareil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface FrabriquantAppareilFacadeLocal {

    void create(FrabriquantAppareil frabriquantAppareil);

    void edit(FrabriquantAppareil frabriquantAppareil);

    void remove(FrabriquantAppareil frabriquantAppareil);

    FrabriquantAppareil find(Object id);

    List<FrabriquantAppareil> findAll();

    List<FrabriquantAppareil> findRange(int[] range);

    int count();
    
}

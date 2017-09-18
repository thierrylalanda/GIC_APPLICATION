/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Founisseur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface FounisseurFacadeLocal {

    void create(Founisseur founisseur);

    void edit(Founisseur founisseur);

    void remove(Founisseur founisseur);

    Founisseur find(Object id);

    List<Founisseur> findAll();

    List<Founisseur> findRange(int[] range);

    int count();

    Founisseur findByNAME(String name);
}

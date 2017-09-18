/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Societe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface SocieteFacadeLocal {

    void create(Societe societe);

    void edit(Societe societe);

    void remove(Societe societe);

    Societe find(Object id);

    List<Societe> findAll();

    List<Societe> findRange(int[] range);

    int count();
    
}

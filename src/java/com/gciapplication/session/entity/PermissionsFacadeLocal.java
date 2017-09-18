/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Permissions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface PermissionsFacadeLocal {

    void create(Permissions permissions);

    void edit(Permissions permissions);

    void remove(Permissions permissions);
    
     void removeByIdGroupe(int idGroupe);

    Permissions find(Object id);

    List<Permissions> findAll();

    List<Permissions> findRange(int[] range);

    int count();

    List<Permissions> findAccesPage(int id);

    List<Permissions> findAllByIdGrpoupe(int idGroupe);
}

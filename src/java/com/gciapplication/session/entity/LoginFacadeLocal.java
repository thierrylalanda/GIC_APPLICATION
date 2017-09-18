/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Login;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface LoginFacadeLocal {

    void create(Login login);

    void edit(Login login);

    void remove(Login login);

    Login find(Object id);

    Login findBypersonnelID(int idPersonnel);

    List<Login> findAll();

    List<Login> findRange(int[] range);

    int count();

    ArrayList<Login> findAl(String password);
    
    List<Login> findAllByIdGroupe(int idGroupe);

    List<Login> findAlUser(String username, String password);
}

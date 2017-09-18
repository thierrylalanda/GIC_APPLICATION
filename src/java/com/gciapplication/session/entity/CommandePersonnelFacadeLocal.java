/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CommandePersonnel;
import com.gciapplication.entity.Personnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface CommandePersonnelFacadeLocal {

    void create(CommandePersonnel commandePersonnel);

    void edit(CommandePersonnel commandePersonnel);

    void remove(CommandePersonnel commandePersonnel);

    CommandePersonnel find(Object id);

    List<CommandePersonnel> findAll();

    List<CommandePersonnel> findRange(int[] range);

    int count();

    List<CommandePersonnel> findpersonnelCommande(int idP, int idMS, String etat);

    List<CommandePersonnel> findCommandeRecus(int idMS, String etat);
    
    List<CommandePersonnel> findCommandeNonReceptionner(int personnel, String reception);

    List<CommandePersonnel> findpersonnelCommandeByidpersonnel(int idP, String etat);

    List<Personnel> findByEtatCommandeReturnPersonel(int idMS, String etat);
}

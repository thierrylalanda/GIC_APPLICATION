/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CommandeMS;
import com.gciapplication.entity.MagasinSecondaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface CommandeMSFacadeLocal {

    void create(CommandeMS commandeMS);

    void edit(CommandeMS commandeMS);

    void remove(CommandeMS commandeMS);

    CommandeMS find(Object id);

    List<CommandeMS> findAll();

    List<CommandeMS> findRange(int[] range);

    int count();

    CommandeMS findByCode(String code, int id);

    CommandeMS findByCodeByEtat(String code, int id, String etat);   

    List<CommandeMS> findByEtatCommande(String etat, int idMS);

    List<CommandeMS> findByEtatCommandeForMP(String etat, int idMP);

    List<MagasinSecondaire> findByEtatCommandeForMPDistintc(String etat, int idMP);
}

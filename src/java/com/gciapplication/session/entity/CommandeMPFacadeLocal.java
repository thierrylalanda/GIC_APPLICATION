/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.CommandeMP;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface CommandeMPFacadeLocal {

    void create(CommandeMP commandeMP);

    void edit(CommandeMP commandeMP);

    void remove(CommandeMP commandeMP);

    CommandeMP find(Object id);

    List<CommandeMP> findAll();

    List<CommandeMP> findRange(int[] range);

    int count();

    List<CommandeMP> findByIdMPAndEtat(int id, String etat);
    
     List<CommandeMP> findByIdMPAndIdFournisseur(int idFournisseur, int idMP, String livraison);
     
     List<CommandeMP> findByIdMPAndLivraison( int idMP, String livraison);

    List<CommandeMP> findByIdMPAndEtatIndice(int id, String etat, int indice);

    List<CommandeMP> findByRecenDate(int id);

    List<CommandeMP> findByIdMPAndEtatOrOk(int id, String etat, String Ok);
}

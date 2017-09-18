/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Groupes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface GroupesFacadeLocal {

    void create(Groupes groupes);

    void edit(Groupes groupes);

    void remove(Groupes groupes);

    Groupes find(Object id);
    
    Groupes findByNameGroupe(String nameGroupe);
    
    List<Groupes> findByAllDefaultGroupe();

    Groupes findLastInsert();

    List<Groupes> findAll();

    List<Groupes> findRange(int[] range);

    int count();

    List<Groupes> findByNiveau(int niveau);

    List<Groupes> findByNiveauByRegion(int niveau, int idRegion);

}

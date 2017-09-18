/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.OrdreControleur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface OrdreControleurFacadeLocal {

    void create(OrdreControleur ordreControleur);

    void edit(OrdreControleur ordreControleur);

    void remove(OrdreControleur ordreControleur);

    OrdreControleur find(Object id);

    List<OrdreControleur> findAll();

    List<OrdreControleur> findRange(int[] range);

    int count();

    void Delete(int id);

    List<OrdreControleur> findAllByIdMP(int id);

    List<OrdreControleur> findAllByNiveau(int niveau, int id);

    int[] findAllByNiveaux();

    List<OrdreControleur> findIDControleurs(int idPersonnel);
}

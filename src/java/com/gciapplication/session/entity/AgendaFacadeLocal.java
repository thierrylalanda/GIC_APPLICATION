/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Agenda;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface AgendaFacadeLocal {

    void create(Agenda agenda);

    void edit(Agenda agenda);

    void remove(Agenda agenda);

    Agenda find(Object id);

    List<Agenda> findAll();
    
    List<Agenda> findByIdPersonnel(int IdPersonnel);
    List<Agenda> findByIdPersonnelAndDate(int IdPersonnel,Date date);

    List<Agenda> findRange(int[] range);

    int count();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.VisaChef;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface VisaChefFacadeLocal {

    void create(VisaChef visaChef);

    void edit(VisaChef visaChef);

    void remove(VisaChef visaChef);

    VisaChef find(Object id);

    List<VisaChef> findAll();

    List<VisaChef> findRange(int[] range);

    List<VisaChef> findVisaByIdMP(int id);
    
    List<VisaChef> findVisaByIdCommande(int id_commande);

    List<String> findControleurByIdMP(int id);

    int count();

}

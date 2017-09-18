/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Site;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface SiteFacadeLocal {

    void create(Site site);

    void edit(Site site);

    void remove(Site site);

    Site find(Object id);

    List<Site> findAll();

    List<Site> findRange(int[] range);

    int count();
    
}

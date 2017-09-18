/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieDirection;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueCategorieDirectionFacadeLocal {

    void create(StatistiqueCategorieDirection statistiqueCategorieDirection);

    void edit(StatistiqueCategorieDirection statistiqueCategorieDirection);

    void remove(StatistiqueCategorieDirection statistiqueCategorieDirection);

    StatistiqueCategorieDirection find(Object id);

    List<StatistiqueCategorieDirection> findAll();

    List<StatistiqueCategorieDirection> findRange(int[] range);

    int count();

    List<StatistiqueCategorieDirection> findAllByDirection(int direction);

    List<StatistiqueCategorieDirection> findAllByDirectionAndcategorie(int direction, String categorie);

    List<StatistiqueCategorieDirection> findAllByDirectionAndcategorieByPeriode(int direction, String categorie, Date d1, Date d2);

    List<StatistiqueCategorieDirection> findAllBycategorie(String categorie);

    List<StatistiqueCategorieDirection> findAllBycategorieByPeriode(String categorie, Date d1, Date d2);

    StatistiqueCategorieDirection findAllBycategorieByServiceAndOneDate(int direct, String categorie, Date d1);

     int findAllByCategorieByAnneeForDirection(int direction, String categorie, int annee, int mois);

}

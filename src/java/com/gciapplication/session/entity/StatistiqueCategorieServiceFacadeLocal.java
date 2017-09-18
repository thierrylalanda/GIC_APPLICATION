/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StatistiqueCategorieService;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatistiqueCategorieServiceFacadeLocal {

    void create(StatistiqueCategorieService statistiqueCategorieService);

    void edit(StatistiqueCategorieService statistiqueCategorieService);

    void remove(StatistiqueCategorieService statistiqueCategorieService);

    StatistiqueCategorieService find(Object id);

    List<StatistiqueCategorieService> findAll();

    List<StatistiqueCategorieService> findRange(int[] range);

    int count();

    List<StatistiqueCategorieService> findAllByService(int service);

    List<StatistiqueCategorieService> findAllByserviceAndcategorie(int service, String categorie);

    List<StatistiqueCategorieService> findAllByserviceAndcategorieByPeriode(int service, String categorie, Date d1, Date d2);

    StatistiqueCategorieService findAllBycategorieByServiceAndOneDate(int service, String categorie, Date d1);

    List<StatistiqueCategorieService> findAllBycategorie(String categorie);

    List<StatistiqueCategorieService> findAllBycategorieByPeriode(String categorie, Date d1, Date d2);

    int findAllByCategorieByAnneeForService(int service, String categorie, int annee, int mois);

}

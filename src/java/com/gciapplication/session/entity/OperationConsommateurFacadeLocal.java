/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.OperationConsommateur;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface OperationConsommateurFacadeLocal {

    void create(OperationConsommateur operationConsommateur);

    void edit(OperationConsommateur operationConsommateur);

    void remove(OperationConsommateur operationConsommateur);

    OperationConsommateur find(Object id);

    List<OperationConsommateur> findAll();

    List<OperationConsommateur> findRange(int[] range);

    int count();

    List<OperationConsommateur> findAllByIdMS(int id);

    List<OperationConsommateur> findAllByIdPersonnel(int idpersonnel);

    List<OperationConsommateur> findAllByIdPersonnelAndDate(int idpersonnel, java.sql.Date date);

    List<OperationConsommateur> findAllByIdPersonnelAndDateNow(int idpersonnel);

    List<OperationConsommateur> findAllByIdMP(int id);

    List<String> findAllByCategorie(int idsite);

    List<String> findAllByCaategorieAndRegion(int idregion);

    List<OperationConsommateur> findAllByIdMSAndDesignation(String designation, int id);

    List<OperationConsommateur> findAllByIdMSAndCategorieAnNamePersonnel(String categorie, int name);

    List<OperationConsommateur> findAllByIdMSAndCategorieAnNamePersonnelAndNow(String categorie, int name);

    int findAllByIdMSAndCategorieAnNamePersonnelAndSumQt(String categorie);

    List<OperationConsommateur> findConsommationMSByPeriode(Date d1, Date d2, int idMS);

    List<OperationConsommateur> findConsommationMSByPeriode(Date d1, Date d2, int idMS, String designation);

    List<OperationConsommateur> findAllByCategorieAndRegionAndDate(int idregion, String categorie, Date datedebut, Date datefin);

    List<OperationConsommateur> findAllByCategorieAndMSAndDate(int idmagasin, String categorie, Date datedebut, Date datefin);

    List<OperationConsommateur> findAllByCategorieAndPersonnelAndDate(int idpersonnel, String categorie, Date datedebut, Date datefin);

    List<OperationConsommateur> findAllByCategorieAndServiceAndDate(int idservice, String categorie, Date datedebut, Date datefin);

    List<OperationConsommateur> findAllByCategorieAndSiteAndDate(int idsite, String categorie, Date datedebut, Date datefin);

    List<OperationConsommateur> findAllByCategorieAndDirectionAndDate(int iddirection, String categorie, Date datedebut, Date datefin);

    List<OperationConsommateur> findAllByDateSortiByPersonnel(int personnel, Date d1, Date d2);
}

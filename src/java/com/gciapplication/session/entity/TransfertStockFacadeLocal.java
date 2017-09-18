/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.TransfertStock;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TransfertStockFacadeLocal {

    void create(TransfertStock transfertStock);

    void edit(TransfertStock transfertStock);

    void remove(TransfertStock transfertStock);

    TransfertStock find(Object id);

    List<TransfertStock> findAll();

    List<TransfertStock> findRange(int[] range);

    int count();
    
    List<TransfertStock> findAllByIdMP(int id);

    List<TransfertStock> findAllByNameMs(String name);

    List<String> findAllDinstinctByIdNameMS(int idMP);

    List<TransfertStock> findByPeriode(Date d, Date d1, int id);

    List<TransfertStock> findByPeriodeMS(Date d, Date d1, int id, String designation);

    List<TransfertStock> findByPeriodeMP(Date d, Date d1, int id, String designation);

    List<TransfertStock> findByVisaMSByidMS(String etat, int idMS);

    List<TransfertStock> findByDesignation(String designation, int idMS);

    List<TransfertStock> findByDesignationMP(String designation, int idMP);
    
     List<TransfertStock> findByVisaMPByidMP(String etat, int idMP);
     
     List<TransfertStock> findByEtatByidMP(String etat, int idMP);
     
     TransfertStock findByCodeByVisaByIdMS(String code,String visa,int idMS);
       List<TransfertStock> findAllByCategorieAndMPAndDate(int idmagasinp, String categorie, java.util.Date datedebut, java.util.Date datefin);

}

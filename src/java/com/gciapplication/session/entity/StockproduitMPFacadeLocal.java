/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StockproduitMP;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StockproduitMPFacadeLocal {

    void create(StockproduitMP stockproduitMP);

    void edit(StockproduitMP stockproduitMP);

    void remove(StockproduitMP stockproduitMP);

    StockproduitMP find(Object id);
    
    StockproduitMP findLastInsert();

    List<StockproduitMP> findAll();

    List<StockproduitMP> findStockByIdCategorie(int idcategorie);
    
    List<StockproduitMP> findRange(int[] range);

    int count();
    StockproduitMP findByCodeProduitByidMP(int idMP, String code);

    StockproduitMP findByDesignationByidMP(int idMP, String designation);

    List<StockproduitMP> ProduitCritique(int id);
    
     List<StockproduitMP> findByidMP(int idMP);

    List<StockproduitMP> ProduitWarmin(int id);
}

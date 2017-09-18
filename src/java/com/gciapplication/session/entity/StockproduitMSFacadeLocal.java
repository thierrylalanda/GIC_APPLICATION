/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.StockproduitMS;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StockproduitMSFacadeLocal {

    void create(StockproduitMS stockproduitMS);

    void edit(StockproduitMS stockproduitMS);

    void remove(StockproduitMS stockproduitMS);

    StockproduitMS find(Object id);
    
    StockproduitMS findLastInsert();

    List<StockproduitMS> findAll();

    List<StockproduitMS> findStockMP(int idMS);

    List<StockproduitMS> findStockByIdCategorie(int idcategorie);

    List<StockproduitMS> findRange(int[] range);

    int count();

    List<StockproduitMS> ProduitCritique(int id);

    List<StockproduitMS> ProduitWarmin(int id);

    StockproduitMS findByDesignationByidMS(int idMS, String designation);

    StockproduitMS findByCodeProduitByidMS(int idMS, String code);
}

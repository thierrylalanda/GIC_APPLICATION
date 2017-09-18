/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.MouvementProduits;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface MouvementProduitsFacadeLocal {

    void create(MouvementProduits mouvementProduits);

    void edit(MouvementProduits mouvementProduits);

    void remove(MouvementProduits mouvementProduits);

    MouvementProduits find(Object id);

    List<MouvementProduits> findAll();

    List<MouvementProduits> findRange(int[] range);

    int count();

    List<MouvementProduits> findAllByIdMagasin(int idMagasin, int idtransfert);
}

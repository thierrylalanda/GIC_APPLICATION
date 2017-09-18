/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Personnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface PersonnelFacadeLocal {

    void create(Personnel personnel);

    void edit(Personnel personnel);

    void remove(Personnel personnel);

    Personnel find(Object id);

    List<Personnel> findAll();

    List<Personnel> findAllByIdService(int idService);

    List<Personnel> findAllByIdSite(int site);

    List<Personnel> findAllByIdRegion(int region);

    List<Personnel> findAllByIdDirection(int direction);

    List<Personnel> findRange(int[] range);

    int count();

    Personnel findByNomPrenom(String ref);

    List<Personnel> findRole(String role);

    List<Personnel> findRoleByRegion(String role, String region);

    Personnel findByMatricule(String mat);

    Personnel findByMatriculeByMail(String mat, String mail);

    Personnel findMAXIdPersonnel();

    void Refrech(Personnel p);

    Personnel findByID(int ID);
}

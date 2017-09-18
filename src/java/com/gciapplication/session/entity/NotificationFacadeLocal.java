/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Notification;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface NotificationFacadeLocal {

    void create(Notification notification);

    void edit(Notification notification);

    void remove(Notification notification);

    Notification find(Object id);

    List<Notification> findAll();

    List<Notification> findRange(int[] range);

    int count();

    List<Notification> findAllNotificationForOne(int id, int etat);
     List<Notification> findAllNotificationForPersonnel(int id, int etat);

    List<Notification> findAllNotification(int id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.SendMessage;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author messi
 */
public class teste {

    /**
     * @param args the command line arguments
    
    public static void main(String[] args) {
        // TODO code application logic here
        EnvoiEMail mail = new EnvoiEMail();
        try {
            mail.sendMail("messi.charly155@gmail.com", "teste", "tous ces bien Passer");
        } catch (NamingException ex) {
            Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     */
}

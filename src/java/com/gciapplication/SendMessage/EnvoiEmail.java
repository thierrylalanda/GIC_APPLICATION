/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.SendMessage;

import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author messi
 */
@Stateless
public class EnvoiEmail {

    public void EnvoiEmail(String To, String subject, String messages) throws MessagingException {
       // final String serveur = "smtp.gmail.com";
        final String serveur = "smtp-mail.outlook.com";       
        final String username = "messi.charly31@gmail.com";
        final String password = "charly155";
        
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", serveur);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail-smtp-socketFactory-class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail-smtp-socketFactory-port", "587");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail-smtp-socketFactory-fallback ", "false");

        Session session = Session.getDefaultInstance(prop, null);
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
        message.setSubject(subject);
        message.setContent(messages, "text/html");
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport = session.getTransport("smtp");
        transport.connect(serveur, username, password);
        transport.sendMessage(message, message.getAllRecipients());
       
    }

}

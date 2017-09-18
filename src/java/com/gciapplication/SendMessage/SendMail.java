
package com.gciapplication.SendMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author messi
 */
public class SendMail {

    public static boolean envoyerMailSMTP(String To, String From, String pass, String cc, String cc2, String subject, String body) throws AddressException, MessagingException, UnsupportedEncodingException {
        boolean result;
        final String serveur = "smtp.gmail.com";
        final String username = From;
        final String password = pass;
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");       
        prop.put("mail.smtp.host", serveur);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(From));

        if (!cc.isEmpty()) {

            message.addRecipients(Message.RecipientType.CC, cc);
        }
        if (!cc2.isEmpty()) {

            message.addRecipients(Message.RecipientType.CC, cc2);
        }
        message.addRecipients(Message.RecipientType.TO, To);
        message.setSubject(subject);
        message.setText(body);
        message.setSentDate(new Date());
        message.saveChanges();
        session.setDebug(true);

        Transport.send(message);
        result = true;

        return result;
    }
    
}

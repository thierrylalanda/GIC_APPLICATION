/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.genaralConfiguration;

import io.swagger.client.ApiException;
import io.swagger.client.api.SmsApi;
import io.swagger.client.model.*;
import java.util.Date;

/**
 *
 * @author messi
 */
public class SendSMS {
//Adresse du Site Web https://www.isendpro.com/gestion/tableau.php
//userName = messi.charly31@gmail.com
//password = charly155    

    public static void main(String[] args) {
         String dt=new Date().toLocaleString();
         
         System.out.println(dt);
        SendSMS("salut ca marche", "690623374");

    }

    public static boolean SendSMS(String message, String number) {
        boolean envoi = false;
        SmsApi apiInstance3 = new SmsApi();
        SmsUniqueRequest smsrequestunique = new SmsUniqueRequest();
        smsrequestunique.setSms(message);
        smsrequestunique.setKeyid("5c2e5ddf693a23b0ea00bbc20c87f679");
        smsrequestunique.setNum("+237"+number);
        smsrequestunique.setEmetteur("G C I");
        smsrequestunique.setDateEnvoi(new java.sql.Date(new Date().getTime()).toString());

        try {
            SMSReponse result = apiInstance3.sendSms(smsrequestunique);
            System.out.println(result);
            envoi = true;
        } catch (ApiException e) {
            System.err.println(e.getMessage());

        }
        
        return envoi;
    }
}

package com.example.email.service;

import  com.mailersend.sdk.emails.Email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.email.model.EmailDto;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.exceptions.MailerSendException;

@Service
public class SendEmail{

@Value("${api.token}")
private String apiToken;

@Value("${api.domain}")
private String domain;
public void sendEmail(EmailDto data) {

    com.mailersend.sdk.emails.Email email = new Email();

    email.setFrom("SenderEmail", domain);
    email.addRecipient("Destinatário:",data.recipient());
    email.setSubject("Teste notifications");
    email.setPlain(data.message());
   
    MailerSend ms = new MailerSend();

    ms.setToken(apiToken);

    try {

        MailerSendResponse response = ms.emails().send(email);
        System.out.println(response.messageId);
        System.out.println("email enviado para o destinatário"+email.recipients);
    } catch (MailerSendException e) {
        e.printStackTrace();
    }
}
        
}
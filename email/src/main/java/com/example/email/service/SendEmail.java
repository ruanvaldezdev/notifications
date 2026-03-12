package com.example.email.service;

import  com.mailersend.sdk.emails.Email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.email.model.EmailDto;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.exceptions.MailerSendException;

import jakarta.annotation.PostConstruct;
@Component
public class SendEmail{

@Value("${api.token}")
private String apiToken;

@PostConstruct
@RabbitListener(queues = "${fila.direct}")
public void sendEmail(EmailDto data) {

    com.mailersend.sdk.emails.Email email = new Email();

    email.setFrom("SenderEmail", "MS_fZWQBz@test-eqvygm0wx1jl0p7w.mlsender.net ");
    email.addRecipient(null,data.recipient());
    email.setSubject("");
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
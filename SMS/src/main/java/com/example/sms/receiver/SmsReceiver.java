package com.example.sms.receiver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.sms.model.SmsDto;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


@Component
public class SmsReceiver { // Nome sugerido para clareza

    @Value("${twilio.sid}")
    private String accountSid;

    @Value("${twilio.token}")
    private String authToken;

    // 1. Inicializa o Twilio apenas UMA VEZ no startup (sem argumentos)
    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
        System.out.println("Twilio inicializado com sucesso!");
    }

   
}

package com.example.sms.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.sms.model.SmsDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
public class SenderSms {
    @Value("${number.sms.value}")
    private String number;
 @RabbitListener(queues="fila.sms")
      public void processSms(SmsDto data) {
        try {
            Message message = Message.creator(
                new PhoneNumber(data.recipient()), 
                new PhoneNumber(number),   
                data.message()
            ).create();

            System.out.println("SMS enviado! SID: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Erro ao enviar SMS: " + e.getMessage());
        }
    }
}

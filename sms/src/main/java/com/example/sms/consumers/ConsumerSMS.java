package com.example.sms.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.sms.model.SmsDto;
import com.twilio.type.PhoneNumber;

import com.twilio.rest.api.v2010.account.Message;


@Component
public class ConsumerSMS {

 @RabbitListener(queues="fila.sms")
      public void processSms(SmsDto data) {
        try {
            Message message = Message.creator(
                new PhoneNumber(data.recipient()), 
                new PhoneNumber("+18285191361"),   
                data.message()
            ).create();

            System.out.println("SMS enviado! SID: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Erro ao enviar SMS: " + e.getMessage());
        }
    }
}

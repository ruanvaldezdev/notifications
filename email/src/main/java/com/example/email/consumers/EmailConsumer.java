package com.example.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.email.model.EmailDto;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "fila.email")
    public void listenEmailQueue(@Payload EmailDto string){
        System.out.println(string);
    
    }
}

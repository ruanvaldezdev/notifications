package com.example.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.email.model.EmailDto;
import com.example.email.service.SendEmail;

@Component
public class EmailConsumer {

    @Autowired
    private SendEmail sendEmail;

    @RabbitListener(queues = "fila.email")
    public void listenEmailQueue(@Payload EmailDto data){
          try {
            sendEmail.sendEmail(data);
        } catch (Exception e) {
            // É importante tratar erros aqui para a mensagem não ficar presa na fila infinitamente
            System.err.println("Erro ao processar fila de email: " + e.getMessage());
        }
    
    }
}

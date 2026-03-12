package com.example.sms.receiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import com.twilio.Twilio;


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

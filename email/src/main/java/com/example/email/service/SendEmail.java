package com.example.email.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.email.model.EmailDto;

@Service
public class SendEmail {

    private static final String BREVO_API_URL = "https://api.brevo.com/v3/smtp/email";

    @Value("${api.token}")
    private String apiToken;

    @Value("${api.domain}")
    private String senderEmail;

    private final RestClient restClient = RestClient.create();

    public void sendEmail(EmailDto data) {

        Map<String, Object> body = Map.of(
                "sender", Map.of("name", "Notifications", "email", senderEmail),
                "to", List.of(Map.of("email", data.recipient())),
                "subject", "Teste notifications",   
                "textContent", data.message()
        );

        try {
            String response = restClient.post()
                    .uri(BREVO_API_URL)
                    .header("api-key", apiToken)
                    .header("accept", "application/json")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(String.class);

            System.out.println("Email enviado para o destinatário " + data.recipient() + ": " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

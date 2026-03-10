package api.api.model.dto;
import java.util.regex.Pattern;

import api.api.model.Channel;
import api.api.model.Priority;
import jakarta.validation.constraints.AssertTrue;


public record NotificationRequest( Channel channel, 
    String recipient,String message, Priority priority) {
    // Regex simples para exemplo
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$"; // Padrão internacional E.164

    @AssertTrue(message = "Formato do destinatário inválido para o canal selecionado")
    public boolean isValidRecipient() {
        if (recipient == null || channel == null) {
            return false;
        }

        if (channel.name().equalsIgnoreCase("EMAIL")) {
            return Pattern.matches(EMAIL_REGEX, recipient);
        }

        if (channel.name().equalsIgnoreCase("SMS")) {
            return Pattern.matches(PHONE_REGEX, recipient);
        }

        return true; // Para outros canais (como PUSH) se não houver regra
    
}}

package api.api.dto;
import api.api.model.Channel;
import api.api.model.Priority;
import api.api.persistence.ValidRecipient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ValidRecipient
public record NotificationRequest( 
    @NotNull
    Channel channel, 
    @NotBlank
    String recipient,
    @NotBlank
    String message,
    
    Priority priority) {
}

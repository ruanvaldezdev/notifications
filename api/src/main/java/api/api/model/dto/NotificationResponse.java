package api.api.model.dto;
import api.api.model.Channel;
import api.api.model.Priority;
import java.util.UUID;


public record NotificationResponse(UUID id, Channel channel, String recipient, String message, Priority priority, String data) {
    
}

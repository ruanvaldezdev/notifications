package api.api.model.dto;
import api.api.model.Channel;
import api.api.model.Priority;


public record NotificationRequest( Channel channel, 
    String recipient,String message, Priority priority) {
}

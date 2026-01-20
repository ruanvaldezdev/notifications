package api.api.model.dto;
import api.api.model.Channel;
import api.api.model.Priority;
import api.api.model.User;


public record NotificationRequest(User userId , Channel channel, String recipient,String message, Priority priority, String data) {
    
}

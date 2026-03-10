package api.api.model.dto;
import api.api.model.Channel;

public record NotificationResponse(Channel channel, String recipient, String message) {


}

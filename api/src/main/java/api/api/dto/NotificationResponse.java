package api.api.dto;
import java.util.UUID;

import api.api.model.Channel;

public record NotificationResponse(UUID idNotification,Channel channel, String recipient, String message) {


}

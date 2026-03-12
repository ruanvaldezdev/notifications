package api.api.model.dto;
import java.util.UUID;

import api.api.model.Channel;
import api.api.model.User;

public record NotificationResponse(UUID userId,String username ,UUID idNotification,Channel channel, String recipient, String message) {


}

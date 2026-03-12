package api.api.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.converters.Auto;

import api.api.config.AuthenticatedUser;
import api.api.amqp.RabbitConfig;
import api.api.model.Notification;
import api.api.model.User;
import api.api.model.dto.NotificationRequest;
import api.api.model.dto.NotificationResponse;
import api.api.persistence.NotificationRepository;
import api.api.persistence.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public ResponseEntity<String> getNotifications() {
        return ResponseEntity.ok("List of notifications");
    }
@PostMapping
public ResponseEntity<NotificationResponse> createNotification(@RequestBody @Valid NotificationRequest data) {
    
    UUID userAuthLong = AuthenticatedUser.getAuthenticatedUserId();

    User user = userRepository.findById(userAuthLong).orElseThrow();
    // Converte o Enum em String minúscula para usar como Routing Key
    String routingKey = data.channel().name().toLowerCase();

    // Mapeie o Enum para valores numéricos
    int priorityValue = switch (data.priority()) {
    case HIGH -> 10;
    case MEDIUM -> 5;
    default -> 0; // LOW
};


    // Envia usando a Routing Key dinâmica ("email", "sms" ou "push")
    rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, routingKey,data,        message -> {
            message.getMessageProperties().setPriority(priorityValue);
            return message;
        });


    Notification notification= new Notification(
        user, 
        data.channel(), 
        data.recipient(), 
        data.message(),
        data.priority()     
                    );
    
    notificationRepository.save(notification);

        NotificationResponse response = new NotificationResponse(
        user.getId(),
        user.getUsername(),
        notification.getId(),
        data.channel(), 
        data.recipient(), 
        data.message()
    );
    return ResponseEntity.ok(response);
}
}
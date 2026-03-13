package api.api.service;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import api.api.config.AuthenticatedUser;
import api.api.dto.NotificationRequest;
import api.api.dto.NotificationResponse;
import api.api.amqp.RabbitConfig;
import api.api.model.Notification;
import api.api.model.User;
import api.api.persistence.UserRepository;
import api.api.validators.NotificationRepository;
import jakarta.transaction.Transactional;


@Service
public class ServiceNotification {
 @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    
public Page<NotificationResponse> getAllNotificationsByUser(Pageable pageable) {

       UUID userAuthId = AuthenticatedUser.getAuthenticatedUserId();

       Page<Notification> notification= notificationRepository.findAllNotificationByUserId(userAuthId, pageable);

        Page<NotificationResponse> response = notification.map(n -> new NotificationResponse(
            n.getId(),
            n.getChannel(),
            n.getRecipient(),
            n.getMessage()
    ));
     return response;
}

public NotificationResponse getNotificationsById(@PathVariable UUID id) {
        
        Notification notification = notificationRepository.findById(id).
        orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe notificações do usuario que está logado"));
         
        UUID userAuthId = AuthenticatedUser.getAuthenticatedUserId();
       
          if (!notification.getUser().getId().equals(userAuthId)) {
         throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Não existe notificações do usuario que está logado"); // 403 Proibido
    }

        NotificationResponse response =new NotificationResponse(
            notification.getId(),
            notification.getChannel(), 
            notification.getRecipient(), 
            notification.getMessage()
        );

          return response;
    }
@Transactional
public NotificationResponse createNotification(@RequestBody  NotificationRequest data) {
        
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
            notification.getId(),
            data.channel(), 
            data.recipient(), 
            data.message()
        );
        return response;
    }








}
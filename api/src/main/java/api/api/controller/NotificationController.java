package api.api.controller;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api.api.dto.NotificationRequest;
import api.api.dto.NotificationResponse;
import api.api.service.ServiceNotification;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private ServiceNotification service;

    @GetMapping()
    public ResponseEntity<Page<NotificationResponse>> getAllNotificationsByUser(Pageable pageable) {
        // Guarda o resultado do service em uma variável
        Page<NotificationResponse> response = service.getAllNotificationsByUser(pageable);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationsById(@PathVariable UUID id) {
        // Guarda o resultado do service em uma variável
        NotificationResponse response = service.getNotificationsById(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody @Valid NotificationRequest data) {
        // Passa o dado para o service e retorna o resultado
        NotificationResponse response = service.createNotification(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

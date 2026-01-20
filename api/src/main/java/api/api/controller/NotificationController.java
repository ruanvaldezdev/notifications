package api.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.RpcClient;
@RestController
@RequestMapping("/notifications")
public class NotificationController {


    @GetMapping
    public ResponseEntity<String> getNotifications() {
        return ResponseEntity.ok("List of notifications");
    }

    @PostMapping
    public ResponseEntity<String> createNotification() {
        return ResponseEntity.ok("Notification created");
    }
}

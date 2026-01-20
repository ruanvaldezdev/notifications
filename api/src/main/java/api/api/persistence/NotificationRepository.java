package api.api.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.api.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    
}   
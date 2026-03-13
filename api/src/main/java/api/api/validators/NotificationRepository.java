package api.api.validators;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import api.api.model.Notification;


public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    Page<Notification> findAllNotificationByUserId(UUID userId,Pageable pageable);


}   
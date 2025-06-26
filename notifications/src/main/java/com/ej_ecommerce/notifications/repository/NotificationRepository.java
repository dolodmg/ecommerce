package com.ej_ecommerce.notifications.repository;

import com.ej_ecommerce.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationsByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}

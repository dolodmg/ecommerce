package com.ej_ecommerce.notifications.service;

import com.ej_ecommerce.notifications.dto.NotificationRequestDTO;
import com.ej_ecommerce.notifications.dto.NotificationResponseDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface iNotificationService {
    NotificationResponseDTO getNotification(Long idNotification);
    List<NotificationResponseDTO> getNotificationsByUser(Long idUser);
    List<NotificationResponseDTO> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    NotificationResponseDTO createNotification(NotificationRequestDTO notificationDTO);
    String markAsRead(Long idNotification);
    String deleteNotification(Long idNotification);
}

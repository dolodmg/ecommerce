package com.ej_ecommerce.notifications.service;

import com.ej_ecommerce.notifications.dto.NotificationRequestDTO;
import com.ej_ecommerce.notifications.dto.NotificationResponseDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface iNotificationService {
    public NotificationResponseDTO getNotification(Long idNotification);
    public List<NotificationResponseDTO> getNotificationsByUser(Long idUser);
    public List<NotificationResponseDTO> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    public NotificationResponseDTO createNotification(NotificationRequestDTO notificationDTO);
    public String markAsRead(Long idNotification);
    public String deleteNotification(Long idNotification);
}

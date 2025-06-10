package com.ej_ecommerce.notifications.service;

import com.ej_ecommerce.notifications.dto.NotificationRequestDTO;
import com.ej_ecommerce.notifications.dto.NotificationResponseDTO;
import com.ej_ecommerce.notifications.mapper.NotificationMapper;
import com.ej_ecommerce.notifications.model.Notification;
import com.ej_ecommerce.notifications.repository.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService implements iNotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationResponseDTO getNotification(Long idNotification) {
        Notification notification = notificationRepository.findById(idNotification)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una notificación con ID " + idNotification));
        return notificationMapper.toDto(notification);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUser(Long idUser) {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Notification> notifications = notificationRepository.findNotificationsByDateTimeBetween(startDate, endDate);
        return notifications.stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO notificationDTO) {
        Notification notification = notificationMapper.toEntity(notificationDTO);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDto(notification);
    }

    @Override
    public String markAsRead(Long idNotification) {
        Notification notification = notificationRepository.findById(idNotification)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una notificación con ID " + idNotification));
        if (!notification.isRead()) {
            notification.setRead(true);
        }
        notificationRepository.save(notification);
        return "La notificación ha sido marcada como leída";
    }

    @Override
    public String deleteNotification(Long idNotification) {
        try {
            notificationRepository.deleteById(idNotification);
            return "La notificación fue eliminada correctamente";
        } catch (Exception e) {
            return "No se encontró una notificación con ese ID " + e.getMessage();
        }
    }
}

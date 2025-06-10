package com.ej_ecommerce.notifications.controller;

import com.ej_ecommerce.notifications.dto.NotificationRequestDTO;
import com.ej_ecommerce.notifications.dto.NotificationResponseDTO;
import com.ej_ecommerce.notifications.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{idNotification}")
    public ResponseEntity<NotificationResponseDTO> getNotification(@PathVariable Long idNotification) {
        NotificationResponseDTO notification = notificationService.getNotification(idNotification);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUser(@PathVariable Long idUser) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByUser(idUser);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate
            ) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByDateRange(startDate, endDate);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/post")
    public ResponseEntity<NotificationResponseDTO> createNotification(@RequestBody NotificationRequestDTO notificationDTO) {
        NotificationResponseDTO notification = notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok(notification);
    }

    @PutMapping("/read/{idNotification}")
    public ResponseEntity<String> markAsRead(@PathVariable Long idNotification) {
        String message = notificationService.markAsRead(idNotification);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{idNotification}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long idNotification) {
        String message = notificationService.deleteNotification(idNotification);
        return ResponseEntity.ok(message);
    }
}

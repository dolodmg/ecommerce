package com.ej_ecommerce.notifications.controller;

import com.ej_ecommerce.notifications.dto.NotificationRequestDTO;
import com.ej_ecommerce.notifications.dto.NotificationResponseDTO;
import com.ej_ecommerce.notifications.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@Tag(name = "Notifications", description = "Notifications Endpoints")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{idNotification}")
    @Operation(summary = "Retrieve a notification by its ID")
    public ResponseEntity<NotificationResponseDTO> getNotification(@PathVariable Long idNotification) {
        NotificationResponseDTO notification = notificationService.getNotification(idNotification);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/{idUser}")
    @Operation(summary = "Retrieve all the notifications of a user")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUser(@PathVariable Long idUser) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByUser(idUser);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping
    @Operation(summary = "Retrieve all the notifications in a given date range")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate
            ) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByDateRange(startDate, endDate);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new notification")
    public ResponseEntity<NotificationResponseDTO> createNotification(@RequestBody NotificationRequestDTO notificationDTO) {
        NotificationResponseDTO notification = notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok(notification);
    }

    @PutMapping("/read/{idNotification}")
    @Operation(summary = "Mark a notification as read")
    public ResponseEntity<String> markAsRead(@PathVariable Long idNotification) {
        String message = notificationService.markAsRead(idNotification);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{idNotification}")
    @Operation(summary = "Logically delete a notification")
    public ResponseEntity<String> deleteNotification(@PathVariable Long idNotification) {
        String message = notificationService.deleteNotification(idNotification);
        return ResponseEntity.ok(message);
    }
}

package com.ej_ecommerce.notifications.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {
    private Long idUser;
    private String message;
    private LocalDateTime dateTime;
}

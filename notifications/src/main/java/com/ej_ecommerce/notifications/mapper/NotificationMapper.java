package com.ej_ecommerce.notifications.mapper;

import com.ej_ecommerce.notifications.dto.NotificationRequestDTO;
import com.ej_ecommerce.notifications.dto.NotificationResponseDTO;
import com.ej_ecommerce.notifications.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(target = "idNotification", ignore = true)
    Notification toEntity(NotificationRequestDTO dto);

    NotificationResponseDTO toDto(Notification notification);
}

package com.ej_ecommerce.users.mapper;

import com.ej_ecommerce.users.dto.UserRequestDTO;
import com.ej_ecommerce.users.dto.UserResponseDTO;
import com.ej_ecommerce.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "idUser", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toDto(User user);
}

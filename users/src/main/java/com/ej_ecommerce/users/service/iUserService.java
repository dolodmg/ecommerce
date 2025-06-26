package com.ej_ecommerce.users.service;

import com.ej_ecommerce.users.dto.UserLoginDTO;
import com.ej_ecommerce.users.dto.UserRequestDTO;
import com.ej_ecommerce.users.dto.UserResponseDTO;

import java.util.List;

public interface iUserService {
    UserResponseDTO getUser(Long idUser);
    List<UserResponseDTO> getAll();
    void register(UserRequestDTO userDTO);
    UserResponseDTO authenticate(UserLoginDTO userDTO);
    void deleteUser(Long idUser);
    UserResponseDTO updateUser(Long idUser, UserRequestDTO userDTO);

}

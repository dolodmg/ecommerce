package com.ej_ecommerce.users.controller;

import com.ej_ecommerce.users.dto.UserLoginDTO;
import com.ej_ecommerce.users.dto.UserRequestDTO;
import com.ej_ecommerce.users.dto.UserResponseDTO;
import com.ej_ecommerce.users.service.iUserService;
import jakarta.ws.rs.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final iUserService userService;

    public UserController(iUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{idUser}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long idUser) {
        UserResponseDTO user = userService.getUser(idUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> authenticate(@RequestBody UserLoginDTO userDTO) {
        UserResponseDTO user = userService.authenticate(userDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<String> delete(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario eliminado exitosamente");
    }

    @PutMapping("/edit/{idUser}")
    public ResponseEntity<UserResponseDTO> edit(@PathVariable Long idUser, @RequestBody UserRequestDTO userDTO) {
        UserResponseDTO user = userService.updateUser(idUser, userDTO);
        return ResponseEntity.ok(user);
    }
}

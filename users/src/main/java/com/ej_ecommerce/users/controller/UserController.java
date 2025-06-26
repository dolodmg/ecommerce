package com.ej_ecommerce.users.controller;

import com.ej_ecommerce.users.dto.UserLoginDTO;
import com.ej_ecommerce.users.dto.UserRequestDTO;
import com.ej_ecommerce.users.dto.UserResponseDTO;
import com.ej_ecommerce.users.service.iUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Users Endpoints")
public class UserController {
    private final iUserService userService;

    public UserController(iUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{idUser}")
    @Operation(summary = "Retrieve an user by its ID")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long idUser) {
        UserResponseDTO user = userService.getUser(idUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get/all")
    @Operation(summary = "Retrieve all the users")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    @Operation(summary = "Login with a user")
    public ResponseEntity<UserResponseDTO> authenticate(@RequestBody UserLoginDTO userDTO) {
        UserResponseDTO user = userService.authenticate(userDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{idUser}")
    @Operation(summary = "Logically delete a user")
    public ResponseEntity<String> delete(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario eliminado exitosamente");
    }

    @PutMapping("/edit/{idUser}")
    @Operation(summary = "Edit an user")
    public ResponseEntity<UserResponseDTO> edit(@PathVariable Long idUser, @RequestBody UserRequestDTO userDTO) {
        UserResponseDTO user = userService.updateUser(idUser, userDTO);
        return ResponseEntity.ok(user);
    }
}

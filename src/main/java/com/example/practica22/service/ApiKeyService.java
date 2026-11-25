package com.example.practica22.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practica22.dto.LoginRequestDTO;
import com.example.practica22.dto.LoginResponseDTO;
import com.example.practica22.entity.Person;
import com.example.practica22.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ApiKeyService {

    PersonRepository personRepository;

    public ApiKeyService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/noauth/apikey")
    public ResponseEntity<?> apiKeyGeneration(@RequestBody LoginRequestDTO credenciales) {

        if (credenciales == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Debes colocar un username y una password");
        }

        String username = credenciales.getUserName();
        String password = credenciales.getPassword();

        if ((username == null || username.isBlank()) || (password == null || password.isBlank())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Debes colocar un username y una password");
        }

        username = username.strip();

        Optional<Person> person = personRepository.findById(username);

        if (person.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username o contraseña incorrectos");

        } else {
            if (person.get().getPassword().equals(password)) {

                LoginResponseDTO respuesta = new LoginResponseDTO();

                String apiKeyUsuario = UUID.randomUUID().toString();
                LocalDateTime creacionApiKey = LocalDateTime.now();
                respuesta.setApiKey(apiKeyUsuario);

                Person usuarioValidado = person.get();
                usuarioValidado.setApiKey(apiKeyUsuario);
                usuarioValidado.setApiKeyCreation(creacionApiKey);

                personRepository.save(usuarioValidado);

                return ResponseEntity.ok(respuesta);

            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Username o contraseña incorrectos");

            }
        }

    }

}

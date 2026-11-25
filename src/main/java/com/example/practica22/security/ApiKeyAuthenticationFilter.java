package com.example.practica22.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.practica22.entity.Person;
import com.example.practica22.repository.PersonRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private PersonRepository personRepository;

    public ApiKeyAuthenticationFilter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String peticion = request.getHeader("X-API-KEY");

        if (peticion != null && !peticion.isBlank()) {

            Optional<Person> person = personRepository.findByApiKey(peticion);

            if (!person.isEmpty()) {

                Person usuario = person.get();

                if (usuario.getApiKeyCreation() != null) {
                    LocalDateTime fechaApi = usuario.getApiKeyCreation();
                    LocalDateTime vencimiento = fechaApi.plusSeconds(60);

                    LocalDateTime fechaConsulta = LocalDateTime.now();

                    if (fechaConsulta.isBefore(vencimiento)) {
                        UsernamePasswordAuthenticationToken autenticado = new UsernamePasswordAuthenticationToken(
                                usuario.getUsername(), null, Collections.emptyList());
                        SecurityContext contexto = SecurityContextHolder.createEmptyContext();
                        contexto.setAuthentication(autenticado);
                        SecurityContextHolder.setContext(contexto);
                    }
                }

            }

        }

        filterChain.doFilter(request, response);

    }
}
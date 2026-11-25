package com.example.practica22.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.practica22.repository.PersonRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, PersonRepository personRepository)
            throws Exception {

                ApiKeyAuthenticationFilter apiKeyAuthenticationFilter = new ApiKeyAuthenticationFilter(personRepository);

        httpSecurity
                .authorizeHttpRequests((auth) -> {
                    auth
                            .requestMatchers(HttpMethod.POST, "/api/noauth/apikey")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .sessionManagement((session) -> {
                    session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .csrf((csrf) -> {
                    csrf
                            .disable();
                })
                .exceptionHandling((exception) -> {
                    exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(401, "No estás autenticado (API KEY inválida o vencida)");
                        });
                })
                .addFilterBefore(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                


        return httpSecurity.build();

    }

}

package com.fitness.activityservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.http.HttpStatus;


@Service
@RequiredArgsConstructor
public class UserValidationService {

    private  final WebClient userServiceWebClient;

    public  boolean validatieUser(String userId){

        try {
            return Boolean.TRUE.equals(
                    userServiceWebClient.get()
                            .uri("/api/users/{userId}/validate", userId)
                            .retrieve()
                            .bodyToMono(Boolean.class)
                            .block()
            );
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) { // Spring's HttpStatus enum
                throw new RuntimeException("User not found: " + userId, e);
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid request: " + userId, e);
            } else {
                throw new RuntimeException("Unexpected error while validating user: " + userId, e);
            }
        }

    }
}

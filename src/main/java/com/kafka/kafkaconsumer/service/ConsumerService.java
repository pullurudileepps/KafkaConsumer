package com.kafka.kafkaconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.kafkaconsumer.dtos.WelcomeUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final ObjectMapper objectMapper;

    @Autowired
    public ConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @KafkaListener(topics = "sendEmail", groupId = "email-send")
    public void sendEmailHandler(String message) throws JsonProcessingException {
        WelcomeUserDto welcomeUserDto = objectMapper.readValue(message, WelcomeUserDto.class);
        System.out.println(welcomeUserDto);
    }
}
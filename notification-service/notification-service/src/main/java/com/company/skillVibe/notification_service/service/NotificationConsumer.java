package com.company.skillVibe.notification_service.service;

import com.company.skillVibe.notification_service.entities.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(NotificationEvent event) {
        log.info("Received notification for User ID {}: {}", event.getUserId(), event.getMessage());
    }
}

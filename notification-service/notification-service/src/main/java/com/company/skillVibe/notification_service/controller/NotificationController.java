package com.company.skillVibe.notification_service.controller;

import com.company.skillVibe.notification_service.entities.NotificationEvent;
import com.company.skillVibe.notification_service.service.NotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationProducer notificationProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestParam Long userId, @RequestParam String message) {
        NotificationEvent event = new NotificationEvent(userId, message, Instant.now().toString());
        notificationProducer.sendNotification(event);
        return ResponseEntity.ok("Notification Sent Successfully!");
    }
}

package com.saas.tourismagency.admin.backoffice.adapter.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendPrivateNotification(String userEmail, String message) {
        messagingTemplate.convertAndSendToUser(userEmail, "/queue/notifications", message);
    }

}

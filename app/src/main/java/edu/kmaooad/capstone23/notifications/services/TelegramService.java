
package edu.kmaooad.capstone23.notifications.services;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TelegramService implements NotificationTypeService {

    @Override
    public void sendNotification(String type, String userId, String command) {
        System.out.println("[Telegram Service] Dispatching Telegram alert. Event: '" + type + "', User ID: '" + userId + "', Command: '" + command + "'");
    }
}
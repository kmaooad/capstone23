package edu.kmaooad.capstone23.notifications.services;

public interface TelegramNotifyService {
    public void sendMessage(String contactInfo, String message);
}

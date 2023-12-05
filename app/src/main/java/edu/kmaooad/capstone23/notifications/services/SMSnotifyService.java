package edu.kmaooad.capstone23.notifications.services;

public interface SMSnotifyService {
    public void sendMessage(String contactInfo, String message);
}

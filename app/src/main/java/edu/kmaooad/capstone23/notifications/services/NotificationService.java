package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.observers.NotificationObserver;
import jakarta.enterprise.context.RequestScoped;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class NotificationService {

    private List<NotificationObserver> observers;

    public NotificationService(){
        observers = new ArrayList<>();
    }

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notify(Notification notification) {
        observers.forEach(o -> o.notify(notification));
    }
}

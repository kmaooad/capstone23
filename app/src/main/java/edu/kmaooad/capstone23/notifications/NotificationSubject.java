package edu.kmaooad.capstone23.notifications;

import jakarta.enterprise.context.RequestScoped;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class NotificationSubject {
    private List<NotificationObserver> observers = new ArrayList<>();

    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notify(Notification notification) {
        for (NotificationObserver observer : observers) {
            observer.update(notification);
        }
    }

    public boolean isAttached(NotificationObserver observer) {
        return observers.stream().anyMatch(o -> o.equals(observer));
    }
}

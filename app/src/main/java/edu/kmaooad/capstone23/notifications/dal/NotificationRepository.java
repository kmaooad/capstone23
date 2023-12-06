package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
   
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }

    public List<Notification> findByUserIdAndType(String userId, NotificationType type){
        return find("userId = ?1 and type = ?2", userId, type).list();
    }

    public List<Notification> findByCommand(String command){
        return find("command", command).list();
    }
}
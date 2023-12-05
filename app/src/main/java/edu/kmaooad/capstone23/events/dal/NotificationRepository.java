package edu.kmaooad.capstone23.events.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }

    public List<Notification> getByUserIdAndType(String userId, NotificationMethod method){
        return find("userId = ?1 and method = ?2", userId, method).list();
    }

    public List<Notification> getByCommand(String command){
        return find("command", command).list();
    }
}

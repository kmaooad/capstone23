package edu.kmaooad.capstone23.notifs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;



@ApplicationScoped
public class NotifRepo implements PanacheMongoRepository<Notif> {
    public Notif insert(Notif notification){
        persist(notification);
        return notification;
    }

    public Notif findNotifBy(String userId, String notificationType){
        return find("userId = :userId and notificationType =:notificationType",
                Parameters.with("userId", userId).and("notificationType", notificationType))
                .firstResult();
    }
}
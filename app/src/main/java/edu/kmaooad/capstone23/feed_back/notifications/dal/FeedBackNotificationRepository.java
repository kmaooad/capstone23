package edu.kmaooad.capstone23.feed_back.notifications.dal;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class FeedBackNotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }

    @Transactional
    public Notification getLatest() {
        List<Notification> notifications = listAll(Sort.by("dateTimeCreated").descending());
        return notifications.isEmpty() ? null : notifications.get(0);
    }

}
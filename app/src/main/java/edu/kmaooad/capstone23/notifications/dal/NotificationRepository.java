package edu.kmaooad.capstone23.notifications.dal;

import edu.kmaooad.capstone23.groups.dal.Group;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<ScheduledNotification> {

    public ScheduledNotification insert(ScheduledNotification notification){
        persist(notification);
        return notification;
    }

    public Optional<ScheduledNotification> getNotificationByMemberAndTrigger(String memberId, NotificationTriggerType triggerType) {
        return find("memberId = :memberId and notificationTriggerType =:notificationTriggerType",
                Parameters.with("memberId", memberId).and("notificationTriggerType", triggerType))
                .firstResultOptional();
    }
}

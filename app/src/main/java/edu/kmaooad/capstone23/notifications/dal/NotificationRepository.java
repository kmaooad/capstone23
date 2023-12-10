package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<SetupedNotification> {

    public SetupedNotification insert(SetupedNotification notification){
        persist(notification);
        return notification;
    }

    public Optional<SetupedNotification> getNotificationByMemberAndTrigger(String memberId, NotificationOnWhatEventProceed triggerType) {
        return find("memberId = :memberId and NotificationOnWhatEventProceed =:NotificationOnWhatEventProceed",
                Parameters.with("memberId", memberId).and("NotificationOnWhatEventProceed", triggerType))
                .firstResultOptional();
    }
}


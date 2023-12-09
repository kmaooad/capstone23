package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationSetting;
import edu.kmaooad.capstone23.notifications.dal.NotificationSettingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class NotificationSettingService {

    @Inject
    NotificationSettingRepository repository;


    public void persist(NotificationSetting setting) {
        repository.persist(setting);
    }

    public List<NotificationSetting> list(String notificationEvent, NotificationEvent cvCreated) {
       return repository.list(notificationEvent, cvCreated);
    }
}

package edu.kmaooad.capstone23.notifs.service;

import edu.kmaooad.capstone23.notifs.dal.Notif;
import edu.kmaooad.capstone23.notifs.dal.NotifRepo;
import jakarta.inject.Inject;


public class NotifServiceIMPL implements NotifService {

    @Inject
    private NotifRepo notificationsRepository;
    @Override
    public Notif insert(Notif notification) {
        return notificationsRepository.insert(notification);
    }

    @Override
    public Notif findNotifBy(String userId, String notificationType) {
        return notificationsRepository.findNotifBy(userId, notificationType);
    }
}
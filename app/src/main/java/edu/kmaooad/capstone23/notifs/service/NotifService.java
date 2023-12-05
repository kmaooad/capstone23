package edu.kmaooad.capstone23.notifs.service;

import edu.kmaooad.capstone23.notifs.dal.Notif;

public interface NotifService {
    Notif insert(Notif notification);
    Notif findNotifBy(String userId, String notificationType);
}
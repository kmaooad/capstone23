package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NRepository;
import edu.kmaooad.capstone23.notifications.dal.NType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NServiceImpl implements NService {
    @Inject
    NoRepository nRepository;
    @Inject
    NotifyProviderService notifyProviderService;

    @Override
    public Notification insert(Notification n) {
        return nRepository.insert(n);
    }

    @Override
    public boolean notify(String uInfo, NType type, String message) {
        return notifyProviderService.sendNotification(userInfo, type, message);
    }
}
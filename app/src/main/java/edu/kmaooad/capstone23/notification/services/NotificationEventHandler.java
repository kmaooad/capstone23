package edu.kmaooad.capstone23.notification.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.notification.dal.NotificationPreferencesRepository;
import edu.kmaooad.capstone23.notification.services.send.NotificationSender;
import edu.kmaooad.capstone23.notification.services.send.SenderFactory;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationEventHandler {

    @Inject
    ObjectMapper mapper;

    @Inject
    SenderFactory senderFactory;

    @Inject
    UserRepository userRepository;

    @Inject
    NotificationPreferencesRepository prefRepo;


    public <T> boolean handle(String eventType, T payload) {
        try {
            String strPayload = mapper.writeValueAsString(payload);

            var prefs = prefRepo.find("eventType", eventType).list();

            if (prefs.size() == 0) {
                return false;
            }

            for (var pref : prefs) {
                User user = userRepository.findById(pref.userId);

                NotificationSender sender = senderFactory.getSender(pref.destination);

                sender.sendForUser(pref.eventType, strPayload, user);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

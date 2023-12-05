package edu.kmaooad.capstone23.notification.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notification.commands.SetNotificationPreferenceCommand;
import edu.kmaooad.capstone23.notification.dal.NotificationPreference;
import edu.kmaooad.capstone23.notification.dal.NotificationPreferencesRepository;
import edu.kmaooad.capstone23.notification.events.UpdatedNotificationPreference;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class SetNotificationPreferenceHandler implements CommandHandler<SetNotificationPreferenceCommand, UpdatedNotificationPreference> {

    @ApplicationScoped
    private UserRepository userRepository;

    @ApplicationScoped
    private NotificationPreferencesRepository preferencesRepository;


    @Override
    public Result<UpdatedNotificationPreference> handle(SetNotificationPreferenceCommand command) {
        var userObjectId = new ObjectId(command.userId);

        var mbUser = userRepository.findByIdOptional(userObjectId);

        if (mbUser.isEmpty()) {
            return new Result<>(ErrorCode.NOT_FOUND, null);
        }

        NotificationPreference pref = new NotificationPreference();
        pref.destination = command.destination;
        pref.userId = new ObjectId(command.userId);
        pref.eventType = command.eventType;

        preferencesRepository.persist(pref);

        var resp = new UpdatedNotificationPreference();
        resp.destination = pref.destination;
        resp.id = pref.id;
        resp.eventType = pref.eventType;
        resp.userId = pref.userId;

        return new Result<>(resp);
    }
}

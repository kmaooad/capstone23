package edu.kmaooad.capstone23.proffesors.notifications.create;

import edu.kmaooad.capstone23.notifications.service.NotificationMapper;
import edu.kmaooad.capstone23.notifications.sms.SMS;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CreateProffesorNotificationMapper extends NotificationMapper<ProffesorCreated> {
    @Override
    public SMS objectToSms(ProffesorCreated proffesorCreated) {
        return new SMS("New professor","New professor with id " + proffesorCreated.getId().toString() + " added.","test@gmail.com");
    }
}

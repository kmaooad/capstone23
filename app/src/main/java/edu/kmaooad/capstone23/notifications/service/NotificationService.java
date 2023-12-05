package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.events.NotificationSent;
import edu.kmaooad.capstone23.notifications.sms.SMS;
import edu.kmaooad.capstone23.notifications.sms.SMSSender;
import jakarta.inject.Inject;

public abstract class NotificationService<T> {
    @Inject
    protected NotificationMapper<T> mapper;

    @Inject
    protected SMSSender smsSender;

    public Result<? extends NotificationSent> send(NotificationType type, T object) {
        try {
            if (type == NotificationType.SMS) {
                SMS sms = mapper.objectToSms(object);
                smsSender.send(sms);
            }

            return new Result<>(ErrorCode.EXCEPTION, "No such notification sender");
        } catch (Exception e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getMessage());
        }
    }
}

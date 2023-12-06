package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.notifications.sms.SMS;

public abstract class NotificationMapper<T> {
    public SMS objectToSms(T Object) throws Exception { throw new Exception("Map to SMS isn't ready"); }
}

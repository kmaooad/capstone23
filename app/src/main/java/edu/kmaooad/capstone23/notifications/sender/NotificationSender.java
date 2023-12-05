package edu.kmaooad.capstone23.notifications.sender;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.events.NotificationSent;

public abstract class NotificationSender<T1, T2 extends NotificationSent> {
    public abstract Result<T2> send(T1 object);
}

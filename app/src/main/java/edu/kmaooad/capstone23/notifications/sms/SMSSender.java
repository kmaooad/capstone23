package edu.kmaooad.capstone23.notifications.sms;

import edu.kmaooad.capstone23.notifications.events.SMSSent;
import edu.kmaooad.capstone23.notifications.sender.NotificationSender;

public abstract class SMSSender extends NotificationSender<SMS, SMSSent>{}

package edu.kmaooad.capstone23.notifications;

/*
 * I could have done it by subclassing: creating a class implementing some interface for each notification.
 * Moreover, if each notification type had its own behaviour (set of methods), I could implement Visitor design pattern
 * for sending notifications.
 *
 * But it would be indeed an overkill for the given scenario.
 * I decided to do without subclassing and replace it with an enum.
 * This approach is simple and still scalable and fully meets the requirements of the task.
 */
public enum NotificationType {
    SUCCESS_NOTIFICATION,
    ERROR_NOTIFICATION,
}

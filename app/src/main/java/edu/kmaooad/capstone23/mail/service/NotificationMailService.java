package edu.kmaooad.capstone23.mail.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationMailService {
    @Inject
    Mailer mailer;

    public void sendNotification(Notification notification) {
        Mail mail = new Mail()
                .addTo(notification.getEmail())
                .setText(notification.getBody());

        if (notification.getSubject() != null) mail = mail.setSubject(notification.getSubject());

        mailer.send(mail);
    }
}
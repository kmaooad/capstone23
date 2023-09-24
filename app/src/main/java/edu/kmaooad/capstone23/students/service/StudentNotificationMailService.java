package edu.kmaooad.capstone23.students.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StudentNotificationMailService {
    @Inject
    Mailer mailer;

    public void sendNotification(String email, String subject, String notification) {
        mailer.send(Mail.withText(email, subject, notification));
    }
}
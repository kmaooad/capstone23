package edu.kmaooad.capstone23.students.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class StudentNotificationMailServiceTest {
    private static final String TO = "test@capstone23.ua";

    @Inject
    MockMailbox mailbox;

    @Inject
    StudentNotificationMailService service;

    @BeforeEach
    void init() {
        mailbox.clear();
    }

    @Test
    void testNotificationSent() {
        String subject = "Student created";
        String notification = "Student x has been created";
        service.sendNotification(TO,subject,notification);

        // verify that it was sent
        List<Mail> sent = mailbox.getMailsSentTo(TO);
        Assertions.assertEquals(sent.size(),1);
        Mail actual = sent.get(0);
        Assertions.assertEquals(subject,actual.getSubject());
        Assertions.assertEquals(notification,actual.getText());
    }
}

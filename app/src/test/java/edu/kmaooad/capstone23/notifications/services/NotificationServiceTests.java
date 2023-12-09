package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.notifications.dal.NotificationEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationSetting;
import edu.kmaooad.capstone23.notifications.dal.NotificationSettingRepository;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.utils.Notifying;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class NotificationServiceTests {

    @Inject
    @Notifying
    CommandHandler<CreateCV, CVCreated> createCVHandler;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Inject
    NotificationSettingRepository settingRepository;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        settingRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        settingRepository.deleteAll();
    }

    @Test
    void createCV_GetNotificationOnEmailAndTelegram() {
        NotificationSetting emailSetting = new NotificationSetting(NotificationType.EMAIL, NotificationEvent.CV_CREATED);
        NotificationSetting telegramSetting = new NotificationSetting(NotificationType.TELEGRAM, NotificationEvent.CV_CREATED);
        settingRepository.persist(emailSetting);
        settingRepository.persist(telegramSetting);

        CreateCV createCV = provideCVToCreate();
        createCVHandler.handle(createCV);

        assertEquals("EMAIL: CV successfully created TELEGRAM: CV successfully created",
                outputStreamCaptor.toString().trim());
    }

    private CreateCV provideCVToCreate() {
        CreateCV createCV = new CreateCV();
        createCV.setDateTimeCreated(LocalDateTime.now());
        createCV.setTextInfo("some simple info 11111");
        createCV.setStatus(CV.Status.OPEN);
        createCV.setVisibility(CV.Visibility.VISIBLE);
        return createCV;
    }

}

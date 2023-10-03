package edu.kmaooad.capstone23.mail.handler;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.mail.commands.MailOrgsReport;
import edu.kmaooad.capstone23.mail.events.OrgsReportMailed;
import edu.kmaooad.capstone23.mail.handlers.MailOrgsReportHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MailOrgsReportHandlerTest {

    private String testEmail = "naukma@ukr.net";
    private int count = 5;

    @Inject
    MailOrgsReportHandler handler;

    @Test
    @DisplayName("Mail orgs report: valid input")
    public void testBasicMailReport() {
        MailOrgsReport command = new MailOrgsReport();
        command.setRecipientEmail(testEmail);
        command.setRecordsCount(5);

        Result<OrgsReportMailed> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getValue().getSizeOfReport() >= 0);
    }

    @Test
    @DisplayName("Mail orgs report: invalid input")
    public void testBasicMailReportWithValidation() {
        MailOrgsReport command = new MailOrgsReport();
        command.setRecipientEmail(testEmail);
        command.setRecordsCount(-5);

        Result<OrgsReportMailed> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Mail orgs report: invalid input email")
    public void testBasicMailReportWithValidationEmail() {
        MailOrgsReport command = new MailOrgsReport();
        command.setRecipientEmail("123");
        command.setRecordsCount(-5);

        Result<OrgsReportMailed> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }
}

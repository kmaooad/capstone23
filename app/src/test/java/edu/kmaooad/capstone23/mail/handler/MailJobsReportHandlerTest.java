package edu.kmaooad.capstone23.mail.handler;

import edu.kmaooad.capstone23.mail.commands.MailJobsReport;
import edu.kmaooad.capstone23.mail.events.JobsReportMailed;
import edu.kmaooad.capstone23.mail.handlers.MailJobsReportHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.kmaooad.capstone23.common.Result;

@QuarkusTest
public class MailJobsReportHandlerTest {

    private String testEmail = "naukma@ukr.net";
    private int count = 5;

    @Inject
    MailJobsReportHandler handler;

    @Test
    @DisplayName("Mail jobs report: valid input")
    public void testBasicMailReport() {
        MailJobsReport command = new MailJobsReport();
        command.setRecipientEmail(testEmail);
        command.setRecordsCount(5);

        Result<JobsReportMailed> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getValue().getSizeOfReport() >= 0);
    }

    @Test
    @DisplayName("Mail jobs report: invalid input")
    public void testBasicMailReportWithValidation() {
        MailJobsReport command = new MailJobsReport();
        command.setRecipientEmail(testEmail);
        command.setRecordsCount(-5);

        Result<JobsReportMailed> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Mail orgs report: invalid input email")
    public void testBasicMailReportWithValidationEmail() {
        MailJobsReport command = new MailJobsReport();
        command.setRecipientEmail("123");
        command.setRecordsCount(-5);

        Result<JobsReportMailed> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }
}
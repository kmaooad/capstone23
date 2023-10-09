package edu.kmaooad.capstone23.mail.handler;

        import edu.kmaooad.capstone23.mail.commands.MailDistribution;
        import edu.kmaooad.capstone23.mail.commands.MailJobsReport;
        import edu.kmaooad.capstone23.mail.dal.TypeOfDistribution;
        import edu.kmaooad.capstone23.mail.events.DistributionCompleted;
        import edu.kmaooad.capstone23.mail.events.JobsReportMailed;
        import edu.kmaooad.capstone23.mail.handlers.MailDistributionHandler;
        import edu.kmaooad.capstone23.mail.handlers.MailJobsReportHandler;
        import io.quarkus.test.junit.QuarkusTest;
        import jakarta.inject.Inject;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import edu.kmaooad.capstone23.common.Result;

@QuarkusTest
public class MailDistributionHandlerTest {

    private TypeOfDistribution testType = TypeOfDistribution.ORGS;
    private String testBodyMessage = "We want to inform you that something is changed!";

    @Inject
    MailDistributionHandler handler;

    @Test
    @DisplayName("Mail distribution: empty repo")
    public void testBasicMailDistribution() {
        MailDistribution command = new MailDistribution();
        command.setDistributionTarget(testType);
        command.setMessageBody(testBodyMessage);

        Result<DistributionCompleted> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Mail distribution: invalid input")
    public void testBasicMailDistributionWithValidation() {
        MailDistribution command = new MailDistribution();
        command.setDistributionTarget(testType);
        command.setMessageBody(testBodyMessage);

        Result<DistributionCompleted> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }
}
package edu.kmaooad.capstone23.mail.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.mail.commands.MailOrgsReport;
import edu.kmaooad.capstone23.mail.events.OrgsReportMailed;
import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.mail.service.NotificationMailService;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class MailOrgsReportHandler implements CommandHandler<MailOrgsReport, OrgsReportMailed> {

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private NotificationMailService service;

    private static final String subject = "Orgs report";
    private String notificationBody = "";
    private Notification notification = null;

    private String formatBodyMessage(int count) {
        String finalBody = "";
        String leftAlignFormat = "| %-6s | %-6d | %-10d | %-9d |%n";
        Org currentOrg;

        finalBody += finalBody.format("+--------+--------+------------+-----------+%n");
        finalBody += finalBody.format("| ID     | NAME   | INDUSTRY   | IS ACTIVE |%n");
        finalBody += finalBody.format("+--------+--------+------------+-----------+%n");
        for (int i = 0; i < count; i++) {
            currentOrg = orgsRepository.listAll().get(i);
            finalBody += finalBody.format(leftAlignFormat, currentOrg.id, currentOrg.name,
                    currentOrg.industry, currentOrg.isActive);
        }
        finalBody += finalBody.format("+--------+--------+------------+-----------+%n");
        return finalBody;
    }

    public Result<OrgsReportMailed> handle(MailOrgsReport command) {
        Integer countOfRecords = command.getRecordsCount();
        if (countOfRecords == null) {
            countOfRecords = Math.toIntExact(orgsRepository.count());
        }
        if (countOfRecords < 0) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect size of report!");
        }
        if (countOfRecords > Math.toIntExact(orgsRepository.count())) {
            countOfRecords = Math.toIntExact(orgsRepository.count());
        }

        notificationBody = formatBodyMessage(countOfRecords);
        notification = new Notification(command.getRecipientEmail(), subject, notificationBody);

        service.sendNotification(notification);

        OrgsReportMailed result = new OrgsReportMailed(command.getRecipientEmail(), countOfRecords);

        return new Result<>(result);
    }
}
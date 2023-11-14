package edu.kmaooad.capstone23.mail.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.mail.commands.MailOrgsReport;
import edu.kmaooad.capstone23.mail.events.OrgsReportMailed;
import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.mail.service.NotificationMailService;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.services.OrgsServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class MailOrgsReportHandler implements CommandHandler<MailOrgsReport, OrgsReportMailed> {

    @Inject
    OrgsServiceImpl orgsService;

    @Inject
    NotificationMailService service;

    private static final String subject = "Orgs report";
    private String notificationBody = "";
    private Notification notification = null;

    public String formatBodyMessage(int count) {
        String finalBody = "";
        Org currentOrg;

        finalBody += ("+--------+--------+------------+-----------+\n");
        finalBody += ("| ID     | NAME   | INDUSTRY   | IS ACTIVE |\n");
        finalBody += ("+--------+--------+------------+-----------+\n");
        for (int i = 0; i < count; i++) {
            currentOrg = orgsService.getByPos(i);
            finalBody += "| " + currentOrg.id.toString() + " | " + currentOrg.name + " | " + currentOrg.industry + " | "
                    + currentOrg.isActive + "|\n";
        }
        finalBody += ("+--------+--------+------------+-----------+\n");
        return finalBody;
    }

    public Result<OrgsReportMailed> handle(MailOrgsReport command) {
        int countOfRecords = 0;
        int count = command.getRecordsCount();
        if (orgsService.isNotNull()){
            if (count == 0) {
                countOfRecords = Math.toIntExact(orgsService.count());
            }
            if (count < 0) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect size of report!");
            }
            if (count > orgsService.count()) {
                countOfRecords = Math.toIntExact(orgsService.count());
            }
            if (count < orgsService.count()){
                countOfRecords = count;
            }
        }
        if (countOfRecords == 0){
            notificationBody = "No orgs detected!";
        } else {
            notificationBody = formatBodyMessage(countOfRecords);
        }
        notification = new Notification(command.getRecipientEmail(), subject, notificationBody);
        service.sendNotification(notification);

        OrgsReportMailed result = new OrgsReportMailed(command.getRecipientEmail(), countOfRecords);
        return new Result<>(result);
    }
}
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
    OrgsRepository orgsRepository;

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
            currentOrg = orgsRepository.listAll().get(i);
            finalBody += "| " + currentOrg.id.toString() + " | " + currentOrg.name + " | " + currentOrg.industry + " | "
                    + currentOrg.isActive + "|\n";
        }
        finalBody += ("+--------+--------+------------+-----------+\n");
        return finalBody;
    }

    public Result<OrgsReportMailed> handle(MailOrgsReport command) {
        int countOfRecords = 0;
        int count = command.getRecordsCount();
        if (orgsRepository != null){
            if (count == 0) {
                countOfRecords = Math.toIntExact(orgsRepository.count());
            }
            if (count < 0) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect size of report!");
            }
            if (count > orgsRepository.count()) {
                countOfRecords = Math.toIntExact(orgsRepository.count());
            }
            if (count < orgsRepository.count()){
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
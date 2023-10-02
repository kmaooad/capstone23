package edu.kmaooad.capstone23.mail.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.mail.commands.MailExpertReport;
import edu.kmaooad.capstone23.mail.events.ExpertsReportMailed;
import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.mail.service.NotificationMailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class MailExpertsReportHandler implements CommandHandler<MailExpertReport, ExpertsReportMailed> {

    @Inject
    ExpertsRepository expertsRepository;

    @Inject
    NotificationMailService service;

    private static final String subject = "Experts report";
    private String notificationBody = "";
    private Notification notification = null;

    public String formatBodyMessage(int count) {
        String finalBody = "";
        Expert currentExpert;

        finalBody += ("+--------+--------+-------+\n");
        finalBody += ("| ID     | NAME   | ORG   |\n");
        finalBody += ("+--------+--------+-------+\n");
        for (int i = 0; i < count; i++) {
            currentExpert = expertsRepository.listAll().get(i);
            finalBody += "| " + currentExpert.id.toString() + " | " + currentExpert.name + " | " + currentExpert.org.name + "|\n";
        }
        finalBody += ("+--------+--------+-------+\n");
        return finalBody;
    }

    public Result<ExpertsReportMailed> handle(MailExpertReport command) {
        int countOfRecords = 0;
        int count = command.getRecordsCount();
        if (expertsRepository != null){
            if (count == 0) {
                countOfRecords = Math.toIntExact(expertsRepository.count());
            }
            if (count < 0) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect size of report!");
            }
            if (count > expertsRepository.count()) {
                countOfRecords = Math.toIntExact(expertsRepository.count());
            }
            if (count < expertsRepository.count()){
                countOfRecords = count;
            }
        }
        if (countOfRecords == 0){
            notificationBody = "No experts detected!";
        } else {
            notificationBody = formatBodyMessage(countOfRecords);
        }
        notification = new Notification(command.getRecipientEmail(), subject, notificationBody);
        service.sendNotification(notification);

        ExpertsReportMailed result = new ExpertsReportMailed(command.getRecipientEmail(), countOfRecords);
        return new Result<>(result);
    }
}
package edu.kmaooad.capstone23.mail.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.mail.commands.MailJobsReport;
import edu.kmaooad.capstone23.mail.events.JobsReportMailed;
import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.mail.service.NotificationMailService;
import edu.kmaooad.capstone23.jobs.dal.Job;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class MailJobsReportHandler implements CommandHandler<MailJobsReport, JobsReportMailed> {

    @Inject
    private JobService jobService;

    @Inject
    private NotificationMailService service;

    private static final String subject = "Jobs report";
    private String notificationBody = "";
    private Notification notification = null;

    private String formatBodyMessage(int count) {
        String finalBody = "";
        String leftAlignFormat = "| %-6s | %-6d | %-10d | %-9d |%n";
        Job currentJob;

        finalBody += ("+--------+--------+------------+%n");
        finalBody += ("| ID     | NAME   | IS ACTIVE  |%n");
        finalBody += ("+--------+--------+------------+%n");
        for (int i = 0; i < count; i++) {
            currentJob = jobService.listAll().get(i);
            finalBody += "| " + currentJob.id.toString() + " | " + currentJob.name + " | " +
                    currentJob.active + " | ";
        }
        finalBody += ("+--------+--------+------------+%n");
        return finalBody;
    }

    public Result<JobsReportMailed> handle(MailJobsReport command) {
        int countOfRecords = 0;
        int count = command.getRecordsCount();
        if (jobService != null) {
            if (count == 0) {
                countOfRecords = Math.toIntExact(jobService.count());
            }
            if (count < 0) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect size of report!");
            }
            if (count > jobService.count()) {
                countOfRecords = Math.toIntExact(jobService.count());
            }
            if (count < jobService.count()) {
                countOfRecords = count;
            }
        }
        if (countOfRecords == 0) {
            notificationBody = "No jobs detected!";
        } else {
            notificationBody = formatBodyMessage(countOfRecords);
        }
        notification = new Notification(command.getRecipientEmail(), subject, notificationBody);
        service.sendNotification(notification);

        JobsReportMailed result = new JobsReportMailed(command.getRecipientEmail(), countOfRecords);
        return new Result<>(result);
    }
}
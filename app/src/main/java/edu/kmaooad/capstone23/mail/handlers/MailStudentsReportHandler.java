package edu.kmaooad.capstone23.mail.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.mail.commands.MailStudentsReport;
import edu.kmaooad.capstone23.mail.events.StudentsReportMailed;
import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.mail.service.NotificationMailService;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class MailStudentsReportHandler implements CommandHandler<MailStudentsReport, StudentsReportMailed> {

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private NotificationMailService service;

    private static final String subject = "Students report";
    private String notificationBody = "";
    private Notification notification = null;

    private String formatBodyMessage(int count) {
        String finalBody = "";
        String leftAlignFormat = "| %-6s | %-6d | %-10d | %-9d |%n";
        Student currentStudent;

        finalBody += finalBody.format("+--------+--------------+-------------+-------+%n");
        finalBody += finalBody.format("| ID     | FIRST NAME   | LAST NAME   | EMAIL |%n");
        finalBody += finalBody.format("+--------+--------------+-------------+-------+%n");
        for (int i = 0; i < count; i++) {
            currentStudent = studentRepository.listAll().get(i);
            finalBody += finalBody.format(leftAlignFormat, currentStudent.id, currentStudent.lastName,
                    currentStudent.firstName, currentStudent.email);
        }
        finalBody += finalBody.format("+--------+--------------+-------------+-------+%n");
        return finalBody;
    }

    public Result<StudentsReportMailed> handle(MailStudentsReport command) {
        Integer countOfRecords = command.getRecordsCount();
        if (countOfRecords == null) {
            countOfRecords = Math.toIntExact(studentRepository.count());
        }
        if (countOfRecords < 0) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect size of report!");
        }
        if (countOfRecords > Math.toIntExact(studentRepository.count())) {
            countOfRecords = Math.toIntExact(studentRepository.count());
        }

        notificationBody = formatBodyMessage(countOfRecords);
        notification = new Notification(command.getRecipientEmail(), subject, notificationBody);

        service.sendNotification(notification);

        StudentsReportMailed result = new StudentsReportMailed(command.getRecipientEmail(), countOfRecords);

        return new Result<>(result);
    }
}
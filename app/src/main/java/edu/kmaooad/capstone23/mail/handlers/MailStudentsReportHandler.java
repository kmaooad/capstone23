package edu.kmaooad.capstone23.mail.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.mail.commands.MailStudentsReport;
import edu.kmaooad.capstone23.mail.events.OrgsReportMailed;
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
        Student currentStudent;

        finalBody += ("+--------+--------------+-------------+-------+%n");
        finalBody += ("| ID     | FIRST NAME   | LAST NAME   | EMAIL |%n");
        finalBody += ("+--------+--------------+-------------+-------+%n");
        for (int i = 0; i < count; i++) {
            currentStudent = studentRepository.listAll().get(i);
            finalBody += " | " + currentStudent.id.toString() + " | " + currentStudent.lastName + " | " +
                    " | " + currentStudent.firstName + " | " + currentStudent.email + " | ";
        }
        finalBody += ("+--------+--------------+-------------+-------+%n");
        return finalBody;
    }

    public Result<StudentsReportMailed> handle(MailStudentsReport command) {
        int countOfRecords = 0;
        int count = command.getRecordsCount();
        if (studentRepository != null){
            if (count == 0) {
                countOfRecords = Math.toIntExact(studentRepository.count());
            }
            if (count < 0) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect size of report!");
            }
            if (count > studentRepository.count()) {
                countOfRecords = Math.toIntExact(studentRepository.count());
            }
            if (count < studentRepository.count()){
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

        StudentsReportMailed result = new StudentsReportMailed(command.getRecipientEmail(), countOfRecords);
        return new Result<>(result);
    }
}
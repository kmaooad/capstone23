package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import edu.kmaooad.capstone23.students.events.StudentNotified;
import edu.kmaooad.capstone23.students.notification.Notification;
import edu.kmaooad.capstone23.students.notification.NotificationBuilder;
import edu.kmaooad.capstone23.students.service.StudentNotificationMailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Objects;

@RequestScoped
public class NotifyStudentHandler implements CommandHandler<NotifyStudent, StudentNotified> {
    @Inject
    NotificationBuilder notificationBuilder;

    @Inject
    StudentNotificationMailService service;

    @Override
    public Result<StudentNotified> handle(NotifyStudent command) {
        if (!checkStudentExists(command.getStudentId()))
            return new Result<>(ErrorCode.NOT_FOUND, String.format("Student with id %s not found", command.getStudentId()));

        Notification notification = notificationBuilder.build(command);
        service.sendNotification(notification);
        return new Result<>(new StudentNotified(command.getStudentId()));
    }

    private boolean checkStudentExists(ObjectId studentId) {
        return Objects.nonNull(studentId); // TODO: add isStudentExists check when search/get will be done
    }
}
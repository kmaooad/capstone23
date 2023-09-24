package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import edu.kmaooad.capstone23.students.events.StudentNotified;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class NotifyStudentHandler implements CommandHandler<NotifyStudent, StudentNotified>{
    @Override
    public Result<StudentNotified> handle(NotifyStudent command) {
        return null;
    }
}
package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.UnassignActivityFromStudent;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.UnassignActivityFromStudentEvent;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnassignActivityFromStudentHandler implements CommandHandler<UnassignActivityFromStudent, UnassignActivityFromStudentEvent> {
    @Inject
    private ActivityRepository activityRepository;
    @Inject
    private StudentRepository studentRepository;

    @Override
    public Result<UnassignActivityFromStudentEvent> handle(UnassignActivityFromStudent command) {
        Activity activity = activityRepository.findById(command.getActivityId());
        Student student = studentRepository.findById(command.getStudentId());
        student.assignActivity(activity);
        student.unassignActivity(activity);
        var res = new UnassignActivityFromStudentEvent(student.id, activity.id);
        return new Result<>(res);
    }
}

package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.commands.UnassignGroupToActivity;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.events.ActivityUnassigned;
import edu.kmaooad.capstone23.groups.services.GroupService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class UnassignGroupToActivitiesHandler  implements CommandHandler<UnassignGroupToActivity, ActivityUnassigned> {
    @Inject
    private GroupService repository;

    @Inject
    private CourseRepository courseService;

    @Inject
    private ExtracurricularActivityService extracurricularActivityService;

    @Override
    public Result<ActivityUnassigned> handle(UnassignGroupToActivity command) {

        Optional<Group> group = repository.findByIdOptional(command.getGroupId().toString());
        if(group.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This group was previously deleted or never existed");
        ActivityUnassigned result = new ActivityUnassigned(command.getGroupId(), command.getActivityId());
        Group g = group.get();

        Optional<Course> course = courseService.findById(command.getActivityId().toHexString());
        Optional<ExtracurricularActivity> extActivity = extracurricularActivityService.findByIdOptional(command.getActivityId());
        if(course.isEmpty() && extActivity.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity was previously deleted or never existed");


        g.activitiesId.remove(command.getActivityId());
        repository.update(g);

        return new Result<ActivityUnassigned>(result);
    }
}

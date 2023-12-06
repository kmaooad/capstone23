package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.services.CourseService;
import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.commands.AssignGroupToActivity;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.ActivityAssigned;
import edu.kmaooad.capstone23.groups.services.GroupService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class AssignGroupToActivitiesHandler  implements CommandHandler<AssignGroupToActivity, ActivityAssigned> {

    @Inject
    private GroupService repository;

    @Inject
    private CourseService courseService;

    @Inject
    private ExtracurricularActivityService extracurricularActivityService;
    @Override
    public Result<ActivityAssigned> handle(AssignGroupToActivity command) {

        Optional<Group> group = repository.findByIdOptional(command.getGroupId().toString());
        if(group.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This group was previously deleted or never existed");

        ActivityAssigned result = new ActivityAssigned(command.getGroupId(), command.getActivityId());

        Optional<Course> course = courseService.findById(command.getActivityId().toHexString());
        Optional<ExtracurricularActivity> extActivity = extracurricularActivityService.findByIdOptional(command.getActivityId());
        if(course.isEmpty() && extActivity.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity was previously deleted or never existed");


        Group g = group.get();
        if(g.activitiesId.contains(command.getActivityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity is already assigned to this group");
        g.activitiesId.add(command.getActivityId());

        repository.update(g);

        return new Result<ActivityAssigned>(result);
    }
}
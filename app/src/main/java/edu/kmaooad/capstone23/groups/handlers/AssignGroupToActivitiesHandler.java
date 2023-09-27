package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.commands.AssignGroupToActivity;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.ActivityAssigned;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class AssignGroupToActivitiesHandler  implements CommandHandler<AssignGroupToActivity, ActivityAssigned> {

    @Inject
    private GroupsRepository repository;

    @Inject
    private ExtracurricularActivityRepository extracurricularActivityRepository;

    @Inject
    private CourseRepository courseRepository;


    @Inject

    @Override
    public Result<ActivityAssigned> handle(AssignGroupToActivity command) {

        Optional<Group> group = repository.findByIdOptional(command.getGroupId());
        if(group.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This group was previously deleted or never existed");

        ActivityAssigned result = new ActivityAssigned(command.getGroupId(), command.getActivityId());
        Optional<Course> activity = courseRepository.findByIdOptional(command.getGroupId());
        if(activity.isEmpty()){
            Optional<ExtracurricularActivity> activity1 = extracurricularActivityRepository.findByIdOptional(command.getGroupId());
            if(activity1.isEmpty())
                return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity doesn't exist");
        }
        Group g = group.get();
        if(g.activitiesId.contains(command.getActivityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity is already assigned to this group");
        g.activitiesId.add(command.getActivityId());

        repository.update(g);

        return new Result<ActivityAssigned>(result);
    }
}

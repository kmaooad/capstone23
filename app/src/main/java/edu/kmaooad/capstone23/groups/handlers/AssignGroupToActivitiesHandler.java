package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.commands.AssignGroupToActivity;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.ActivityAssign;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class AssignGroupToActivitiesHandler  implements CommandHandler<AssignGroupToActivity, ActivityAssign> {

    @Inject
    private GroupsRepository repository;

    @Override
    public Result<ActivityAssign> handle(AssignGroupToActivity command) {

        Optional<Group> group = repository.findByIdOptional(command.getGroupId());
        if(group.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This group was previously deleted or never existed");

        ActivityAssign result = new ActivityAssign(command.getGroupId(), command.getActivityId());

        Group g = group.get();
      //  g.activitiesId.add(command.getActivityId());

        repository.update(g);

        return new Result<ActivityAssign>(result);
    }
}

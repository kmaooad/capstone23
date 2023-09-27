package edu.kmaooad.capstone23.groups.handlers;

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

    @Override
    public Result<ActivityAssigned> handle(AssignGroupToActivity command) {

        Optional<Group> group = repository.findByIdOptional(command.getGroupId());
        if(group.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This group was previously deleted or never existed");

        ActivityAssigned result = new ActivityAssigned(command.getGroupId(), command.getActivityId());

        Group g = group.get();
        if(g.activitiesId.contains(command.getActivityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity is already assigned to this group");
        g.activitiesId.add(command.getActivityId());

        repository.update(g);

        return new Result<ActivityAssigned>(result);
    }
}

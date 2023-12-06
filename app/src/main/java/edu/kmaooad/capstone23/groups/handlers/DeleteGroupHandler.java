package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.commands.DeleteGroup;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.GroupDeleted;
import edu.kmaooad.capstone23.groups.services.GroupService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteGroupHandler implements CommandHandler<DeleteGroup, GroupDeleted> {

    @Inject
    private GroupService repository;

    public Result<GroupDeleted> handle(DeleteGroup command) {
        if (!ObjectId.isValid(command.getId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Object Id is invalid");

        Group group = repository.findById(command.getId());
        if (group == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Group with such Id doesn't exist");
        }

        repository.delete(group);

        return new Result<>(new GroupDeleted(group.templateId));
    }
}

package edu.kmaooad.capstone23.group_templates.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.DeleteGroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateDeleted;
import edu.kmaooad.capstone23.groups.dal.Group;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@RequestScoped
public class DeleteGroupTemplateHandler implements CommandHandler<DeleteGroupTemplate, GroupTemplateDeleted> {

    @Inject
    private GroupTemplatesRepository repository;

    public Result<GroupTemplateDeleted> handle(DeleteGroupTemplate command) {
        if(!ObjectId.isValid(command.getId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Object Id is invalid");

        GroupTemplate groupTemplate = repository.findById(new ObjectId(command.getId()));

        if (groupTemplate == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Group template with such Id doesn't exist");
        }

        List<Group> children = repository.findChildGroups(command.getId());
        if(children.size() > 0) {
            return new Result<>(ErrorCode.EXCEPTION, "Group template has groups that depend on it");
        }

        repository.delete(groupTemplate);

        return new Result<>(new GroupTemplateDeleted(groupTemplate.id.toString()));
    }
}


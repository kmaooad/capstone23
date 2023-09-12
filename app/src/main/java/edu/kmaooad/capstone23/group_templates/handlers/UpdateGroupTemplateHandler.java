package edu.kmaooad.capstone23.group_templates.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.UpdateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateGroupTemplateHandler implements CommandHandler<UpdateGroupTemplate, GroupTemplateUpdated> {

    @Inject
    private GroupTemplatesRepository repository;

    public Result<GroupTemplateUpdated> handle(UpdateGroupTemplate command) {

        if(!ObjectId.isValid(command.getId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Object Id is invalid");

        GroupTemplate groupTemplate = repository.findById(new ObjectId(command.getId()));

        if (groupTemplate == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Group template with such Id doesn't exist");
        }

        groupTemplate.name = command.getGroupTemplateName();

        repository.update(groupTemplate);

        GroupTemplateUpdated result = new GroupTemplateUpdated(groupTemplate.id.toString(), groupTemplate.name);

        return new Result<>(result);
    }
}

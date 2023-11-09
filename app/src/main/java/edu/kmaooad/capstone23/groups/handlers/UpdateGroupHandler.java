package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.services.GroupTemplatesService;
import edu.kmaooad.capstone23.groups.commands.UpdateGroup;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.events.GroupUpdated;
import edu.kmaooad.capstone23.groups.services.GroupService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateGroupHandler implements CommandHandler<UpdateGroup, GroupUpdated> {

    @Inject
    private GroupService repository;

    @Inject
    private GroupTemplatesService templatesService;

    public Result<GroupUpdated> handle(UpdateGroup command) {
        if(!ObjectId.isValid(command.getId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Object Id is invalid");

        Group group = repository.findById(command.getId());

        if (group == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Group with such Id doesn't exist");
        }
        if(!ObjectId.isValid(command.getTemplateId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Template object Id is invalid");

        GroupTemplate template = templatesService.findById(new ObjectId(command.getTemplateId()));
        if(template == null)
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Template with such object Id doesn't exist");

        group.name = command.getGroupName();
        group.templateId = command.getTemplateId();

        repository.update(group);

        GroupUpdated result = new GroupUpdated(group.id.toString(),group.name,group.templateId);

        return new Result<>(result);
    }
}

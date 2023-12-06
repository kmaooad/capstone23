package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.services.GroupTemplatesService;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.groups.services.GroupService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateGroupHandler implements CommandHandler<CreateGroup, GroupCreated> {

    @Inject
    private GroupService repository;
    @Inject
    private GroupTemplatesService templatesService;

    @Override
    public Result<GroupCreated> handle(CreateGroup command) {
        Group group = new Group();
        group.name = command.getGroupName();
        group.activitiesId = command.getActivitiesId();

        String templateId = command.getTemplateId();
        if(!ObjectId.isValid(templateId))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Template Id is invalid");
        GroupTemplate template = templatesService.findById(new ObjectId(templateId));
        if(template == null)
            return new Result<>(ErrorCode.VALIDATION_FAILED, "There is no group template with such id");

        group.templateId = template.id.toString();

        repository.insert(group);

        GroupCreated result = new GroupCreated(group.id.toString());
        return new Result<>(result);
    }
}

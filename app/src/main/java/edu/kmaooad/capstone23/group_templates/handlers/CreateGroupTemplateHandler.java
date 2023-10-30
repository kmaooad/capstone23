package edu.kmaooad.capstone23.group_templates.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.group_templates.services.GroupTemplatesService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateGroupTemplateHandler implements CommandHandler<CreateGroupTemplate, GroupTemplateCreated> {

    @Inject
    private GroupTemplatesService repository;

    public Result<GroupTemplateCreated> handle(CreateGroupTemplate command) {

        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = command.getGroupTemplateName();

        repository.insert(groupTemplate);

        GroupTemplateCreated result = new GroupTemplateCreated(groupTemplate.id.toString());

        return new Result<>(result);
    }
}

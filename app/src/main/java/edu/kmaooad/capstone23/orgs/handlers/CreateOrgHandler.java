package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingSystem;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingType;
import edu.kmaooad.capstone23.messages.services.NotifyEventService;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.orgs.services.OrgService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateOrgHandler implements CommandHandler<CreateOrg, OrgCreated> {

    @Inject
    private OrgsRepository repository;
    private OrgService service;

    @Inject
    NotifyEventService notifyEventService;

    public Result<OrgCreated> handle(CreateOrg command) {
        Org org = this.mapCommandToEntity(command);
        this.repository.insert(org);

        OrgCreated result = new OrgCreated(org.id.toString());

        notifyEventService.pushEvent(SelectUserMessagingType.CREATE_ORG_MESSAGE, "Created org: " + org.name);

        return new Result<OrgCreated>(result);
    }

    private Org mapCommandToEntity(CreateOrg command) {

        return service.createOrg(command.getOrgName(), command.description, command.industry, command.website, command.emailDomain);
    }
}
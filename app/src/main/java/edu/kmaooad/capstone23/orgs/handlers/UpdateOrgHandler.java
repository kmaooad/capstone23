package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.UpdateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.events.OrgUpdated;
import edu.kmaooad.capstone23.orgs.services.OrgsServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class UpdateOrgHandler implements CommandHandler<UpdateOrg, OrgUpdated> {

    @Inject
    private OrgsServiceImpl orgService;

    @Inject
    private EntityBanService entityBanService;


    public Result<OrgUpdated> handle(UpdateOrg command) {
        Optional<Org> existingOrg = this.orgService.findByIdOptional(command.orgId);

        if (existingOrg.isEmpty()) {
            return new Result<>(ErrorCode.EXCEPTION, "Org with given id not found");
        }
        if (entityBanService.findForEntity(IsEntityBannedV2.ORGANIZATION_BAN_ENTITY_TYPE, existingOrg.get().id.toString()).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Org is banned");
        }

        Org updatedOrg = this.updateEntity(existingOrg.get(), command);

        this.orgService.update(updatedOrg);

        OrgUpdated result = new OrgUpdated(true);

        return new Result<>(result);
    }

    private Org updateEntity(Org existingOrg, UpdateOrg command) {
        existingOrg.name = command.orgName;
        existingOrg.description = command.description;
        existingOrg.industry = command.industry;
        existingOrg.website = command.website;
        existingOrg.emailDomain = command.emailDomain;

        return existingOrg;
    }
}

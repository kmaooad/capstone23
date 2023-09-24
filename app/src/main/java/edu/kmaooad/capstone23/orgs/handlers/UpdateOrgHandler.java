package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.UpdateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class UpdateOrgHandler implements CommandHandler<UpdateOrg, OrgUpdated> {

    @Inject
    private OrgsRepository repository;

    @Inject
    EntityBanRepository entityBanRepository;

    public Result<OrgUpdated> handle(UpdateOrg command) {
        Optional<Org> existingOrg = this.repository.findByIdOptional(new ObjectId(command.orgId));

        if (existingOrg.isEmpty()) {
            return new Result<>(ErrorCode.EXCEPTION, "Org with given id not found");
        }
        if (entityBanRepository.findForEntity(BannedEntityType.Organization, existingOrg.get().id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Org is banned");
        }

        Org updatedOrg = this.updateEntity(existingOrg.get(), command);

        this.repository.update(updatedOrg);

        OrgUpdated result = new OrgUpdated(true);

        return new Result<>(result);
    }

    private Org updateEntity(Org existingOrg, UpdateOrg command) {
        existingOrg.name = command.orgName;
        existingOrg.description = command.description;
        existingOrg.industry = command.industry;
        existingOrg.website = command.website;

        return existingOrg;
    }
}

package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBanned;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityIsBanned;
import edu.kmaooad.capstone23.ban.handlers.IsBannedHandler;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.HiringStatusSettedOn;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetHiringStatusOnHandler implements CommandHandler<SetHiringStatusOn, HiringStatusSettedOn> {
    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    IsBannedHandler isBannedHandler;

    private final String hiringStatusOn = "We are hiring";


    public Result<HiringStatusSettedOn> handle(SetHiringStatusOn command) {
        String orgId = command.getOrgId();

        Org org = orgsRepository.findById(orgId);

        if (org == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org with such Id doesn't exist");
        }

        {
            IsEntityBanned request = new IsEntityBanned(org.id, BannedEntityType.Organization.name());
            Result<EntityIsBanned> result = isBannedHandler.handle(request);
            if (!result.isSuccess()) {
                return new Result<>(result.getErrorCode(), "check if banned failed with " + result.getMessage());
            }

            EntityIsBanned res = result.getValue();
            boolean isBanned = res.value();
            if (isBanned) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Org is banned");
            }
        }

        org.hiringStatus = hiringStatusOn;

        orgsRepository.update(org);

        HiringStatusSettedOn result = new HiringStatusSettedOn(org.id.toString());

        return new Result<>(result);
    }
}

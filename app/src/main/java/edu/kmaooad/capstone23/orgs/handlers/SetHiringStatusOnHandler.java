package edu.kmaooad.capstone23.orgs.handlers;

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

    private final String hiringStatusOn = "We are hiring";


    public Result<HiringStatusSettedOn> handle(SetHiringStatusOn command) {
        String orgId = command.getOrgId();

        Org org = orgsRepository.findById(orgId);

        if (org == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org with such Id doesn't exist");
        }

        org.hiringStatus = hiringStatusOn;

        orgsRepository.update(org);

        HiringStatusSettedOn result = new HiringStatusSettedOn(org.id.toString());

        return new Result(result);
    }
}
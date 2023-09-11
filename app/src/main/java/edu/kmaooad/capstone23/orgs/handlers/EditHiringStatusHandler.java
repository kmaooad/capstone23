package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatus;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.HiringStatusChanged;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class EditHiringStatusHandler implements CommandHandler<SetHiringStatus, HiringStatusChanged> {
    @Inject
    private OrgsRepository repository;

    public Result<HiringStatusChanged> handle(SetHiringStatus command) {
        Org org = repository.findById(command.getOrgID());
        org.hiringStatus = command.getHiringStatusName();

        HiringStatusChanged result = new HiringStatusChanged
                (org.hiringStatus, org.id.toString());

        return new Result<HiringStatusChanged>(result);
    }
}

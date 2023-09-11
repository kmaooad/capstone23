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
import org.bson.types.ObjectId;

@RequestScoped
public class EditHiringStatusHandler implements CommandHandler<SetHiringStatus, HiringStatusChanged> {
    @Inject
    private OrgsRepository repository;

    public Result<HiringStatusChanged> handle(SetHiringStatus command) {
        ObjectId objId = new ObjectId(command.getOrgID());

        Org org = repository.findById(objId);
        System.out.println(org);
        org.hiringStatus = command.getHiringStatus();
        repository.update(org);
        System.out.println(repository.findById(objId));

        HiringStatusChanged result = new HiringStatusChanged
                (org.hiringStatus, org.id.toString());

        return new Result<>(result);
    }
}

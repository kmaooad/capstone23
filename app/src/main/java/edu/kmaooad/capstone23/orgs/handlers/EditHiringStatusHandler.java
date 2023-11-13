package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatus;
import edu.kmaooad.capstone23.orgs.dal.*;
import edu.kmaooad.capstone23.orgs.events.HiringStatusChanged;
import edu.kmaooad.capstone23.orgs.services.OrgService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class EditHiringStatusHandler implements CommandHandler<SetHiringStatus, HiringStatusChanged> {

    @Inject
    private OrgService orgService;

    public Result<HiringStatusChanged> handle(SetHiringStatus command) {

        Org org = orgService.getOrgById(command.getOrgId());

        // Check if org is null
        if (org == null) {
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "Organization not found");
        }

        org.hiringStatus = command.getHiringStatus().toString();
        orgService.updateOrg(org);

//        List<Job> jobs = orgService.findJobsByOrgId(command.getOrgId());
//
//        for (Job job: jobs) {
//            job.isActive = false;
//            orgService.updateJob(job);
//        }
      
        HiringStatusChanged result = new HiringStatusChanged(command.getHiringStatus(), org.id.toString());

        return new Result<>(result);
    }

}

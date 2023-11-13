package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.dal.*;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOff;
import edu.kmaooad.capstone23.orgs.events.HiringStatusSettedOff;
import edu.kmaooad.capstone23.orgs.services.OrgsServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class SetHiringStatusOffHandler implements CommandHandler<SetHiringStatusOff, HiringStatusSettedOff> {

    @Inject
    private OrgsServiceImpl orgService;

    @Inject
    private JobService jobService;

    private final String hiringStatusOff = "Off";

    public Result<HiringStatusSettedOff> handle(SetHiringStatusOff command) {
        String departmentId = command.getOrgId();

        Org org = orgService.findById(departmentId);

        if (org == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Organization with such Id doesn't exist");
        }

        org.hiringStatus = hiringStatusOff;

        if (org.jobs != null) {
            org.jobs.forEach(jobId -> {
                Job job = jobService.findJobById(new ObjectId(jobId));
                if (job == null) {
                    return;
                }
                job.active = false;
                jobService.update(job);
            });
        }


        orgService.update(org);

        HiringStatusSettedOff result = new HiringStatusSettedOff(org.id.toString());

        return new Result(result);
    }
}

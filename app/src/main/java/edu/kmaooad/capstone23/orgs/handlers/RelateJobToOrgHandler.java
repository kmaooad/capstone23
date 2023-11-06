package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.orgs.commands.RelateJobToOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.JobToOrgRelated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;

@RequestScoped
public class RelateJobToOrgHandler implements CommandHandler<RelateJobToOrg, JobToOrgRelated> {
    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private JobService jobService;

    @Inject
    EntityBanService banService;

    public Result<JobToOrgRelated> handle(RelateJobToOrg command) {
        String orgId = command.getOrgId();

        Org org = orgsRepository.findById(orgId);

        if (org == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Org not found");
        }
        if(banService.findForEntity(BannedEntityType.Organization, org.id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Org is banned");
        }

        String jobId = command.getJobId();

        var maybeJob = jobService.findJobById(jobId);

        if (maybeJob.isEmpty()) {
            return new Result<>(ErrorCode.EXCEPTION, "Job not found");
        }
        if (org.jobs == null) {
            org.jobs = new ArrayList<>();
        }

        org.jobs.add(jobId);

        orgsRepository.update(org);

        JobToOrgRelated result = new JobToOrgRelated(orgId, jobId);

        return new Result(result);
    }
}

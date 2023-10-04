package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.orgs.commands.RelateJobToOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.JobToOrgRelated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@RequestScoped
public class RelateJobToOrgHandler implements CommandHandler<RelateJobToOrg, JobToOrgRelated> {
    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private JobRepository jobRepository;

    @Inject
    EntityBanRepository banRepository;

    public Result<JobToOrgRelated> handle(RelateJobToOrg command) {
        String orgId = command.getOrgId();

        Org org = orgsRepository.findById(orgId);

        if (org == null) {
            return new Result(ErrorCode.EXCEPTION, "Org not found");
        }
        if(banRepository.findForEntity(BannedEntityType.Organization, org.id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Org is banned");
        }

        String jobId = command.getJobId();

        Job job = jobRepository.findById(new ObjectId(jobId));

        if (job == null) {
            return new Result(ErrorCode.EXCEPTION, "Job not found");
        }
        if (org.jobs == null) {
            org.jobs = new ArrayList<String>();
        }

        org.jobs.add(jobId);

        orgsRepository.update(org);

        JobToOrgRelated result = new JobToOrgRelated(orgId, jobId);

        return new Result(result);
    }
}

package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatus;
import edu.kmaooad.capstone23.orgs.dal.Job;
import edu.kmaooad.capstone23.orgs.dal.JobsRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.HiringStatusChanged;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@RequestScoped
public class EditHiringStatusHandler implements CommandHandler<SetHiringStatus, HiringStatusChanged> {
    @Inject
    private OrgsRepository orgsRepository;
    @Inject
    private JobsRepository jobsRepository;

    public Result<HiringStatusChanged> handle(SetHiringStatus command) {
        ObjectId objId = new ObjectId(command.getOrgId());

        Org org = orgsRepository.findById(objId);

        // Check if org is null
        if (org == null) {
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "Organization not found");
        }

        org.hiringStatus = command.getHiringStatus().toString();
        orgsRepository.update(org);

        List<Job> jobs = jobsRepository.findByOrgId(command.getOrgId());

        for (Job job: jobs) {
            job.isActive = false;
            jobsRepository.update(job);
        }

        HiringStatusChanged result = new HiringStatusChanged(
                command.getHiringStatus(),
                org.id.toString()
        );

        return new Result<>(result);
    }

}

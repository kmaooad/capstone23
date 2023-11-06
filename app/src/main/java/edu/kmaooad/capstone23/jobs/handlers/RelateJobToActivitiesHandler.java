package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class RelateJobToActivitiesHandler  implements CommandHandler<RelateJobToActivities, ActivityRelated> {

    @Inject
    private JobService jobService;

    @Inject
    private ExtracurricularActivityService extracurricularService;

    @Override
    public Result<ActivityRelated> handle(RelateJobToActivities command) {
        var activityId = command.getActivityId();
        var jobId = command.getJobId();

        if (!extracurricularService.isExtracurricularActivityRelatedToCourse(activityId, activityId)) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity was previously deleted or never existed");
        }

        Optional<Job> job = jobService.findJobById(jobId);
        if (job.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");
        }

        Job j = job.get();
        if(j.activitiesId.contains(activityId)) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity is already related to this job");
        }

        ActivityRelated result = new ActivityRelated(jobId, activityId);
        j.activitiesId.add(activityId);
        jobService.update(j);

        return new Result<ActivityRelated>(result);
    }
}

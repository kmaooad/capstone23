package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeleteRelateJobToActivities;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.ActivityUnrelated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;
@RequestScoped
public class DeleteRelationJobToActivitiesHandler  implements CommandHandler<DeleteRelateJobToActivities, ActivityUnrelated> {
    @Inject
    private JobRepository repository;

    @Override
    public Result<ActivityUnrelated> handle(DeleteRelateJobToActivities command) {

        Optional<Job> job = repository.findByIdOptional(command.getJobId());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        ActivityUnrelated result = new ActivityUnrelated(command.getJobId(), command.getActivityId());

        Job j = job.get();
        if (!j.activitiesId.contains(command.getActivityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job doesn't contain this activity");
        j.activitiesId.remove(command.getActivityId());
        repository.update(j);

        return new Result<ActivityUnrelated>(result);
    }
}
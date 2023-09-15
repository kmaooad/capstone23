package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class RelateJobToActivitiesHandler  implements CommandHandler<RelateJobToActivities, ActivityRelated> {

    @Inject
    private JobRepository repository;

    @Override
    public Result<ActivityRelated> handle(RelateJobToActivities command) {

        Optional<Job> job = repository.findByIdOptional(command.getJobId());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        ActivityRelated result = new ActivityRelated(command.getJobId(), command.getActivityId());

        Job j = job.get();
        j.activitiesId.add(command.getActivityId());

        // Save the updated job back to the repository
        repository.update(j);

        return new Result<ActivityRelated>(result);
    }
}

package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.services.CourseService;
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
import edu.kmaooad.capstone23.jobs.service.JobService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class RelateJobToActivitiesHandler  implements CommandHandler<RelateJobToActivities, ActivityRelated> {

    @Inject
    private JobService jobService;
    @Inject
    private ExtracurricularActivityRepository extracurricularRepository;
    @Inject
    private CourseService courseService;


    @Override
    public Result<ActivityRelated> handle(RelateJobToActivities command) {

        Optional<Job> job = jobService.findJobById(command.getJobId().toString());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        Optional<Course> course = courseService.findById(command.getActivityId().toHexString());
        Optional<ExtracurricularActivity> extActivity = extracurricularRepository.findByIdOptional(command.getActivityId());
        if(course.isEmpty() && extActivity.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity was previously deleted or never existed");

        ActivityRelated result = new ActivityRelated(command.getJobId(), command.getActivityId());

        Job j = job.get();
        if(j.activitiesId.contains(command.getActivityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This activity is already related to this job");
        j.activitiesId.add(command.getActivityId());
        jobService.update(j);

        return new Result<ActivityRelated>(result);
    }
}

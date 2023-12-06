package edu.kmaooad.capstone23.jobs.services;

import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@ApplicationScoped
public class JobService {
    @Inject
    JobRepository jobRepository;

    public boolean jobExists(ObjectId jobId) {
        return jobRepository.findByIdOptional(jobId).isPresent();
    }

    public JobCreated createJob(Job job) {
        // TODO add validations for job object

        jobRepository.insert(job);

        return new JobCreated(job.id);
    }

}

package edu.kmaooad.capstone23.jobs.service;

import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import jakarta.inject.Inject;

public class JobServiceImpl implements JobService{
    @Inject
    private JobRepository jobRepository;
    @Override
    public Job insert(Job job) {
        return jobRepository.insert(job);
    }
    @Override
    public void delete(Job job) { }
}

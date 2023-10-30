package edu.kmaooad.capstone23.jobs.service;

import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JobServiceImpl implements JobService{
    @Inject
    private JobRepository jobRepository;

    @Override
    public Optional<Job> findJobById(ObjectId id) {
        return jobRepository.findByIdOptional(id);
    }

    @Override
    public List<Job> listAll() {
        return jobRepository.listAll();
    }

    @Override
    public long count() {
        return jobRepository.count();
    }

    @Override
    public Job insert(Job job) {
        return jobRepository.insert(job);
    }
    @Override
    public void delete(Job job) {
        jobRepository.delete(job);
    }

    @Override
    public void update(Job job) {
        jobRepository.update(job);
    }
}

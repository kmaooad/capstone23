package edu.kmaooad.capstone23.jobs.service;

import edu.kmaooad.capstone23.jobs.dal.Job;

import java.util.Optional;

public interface JobService {
    Optional<Job> findJobById(String id);
    Job insert(Job job);
    void delete(Job job);
    void update(Job job);
}

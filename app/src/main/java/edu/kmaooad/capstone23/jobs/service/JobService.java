package edu.kmaooad.capstone23.jobs.service;

import edu.kmaooad.capstone23.jobs.dal.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<Job> findJobById(String id);
    List<Job> listAll();
    long count();
    Job insert(Job job);
    void delete(Job job);
    void update(Job job);
    Boolean isJobRelatedToCompetence(String competenceId);
}

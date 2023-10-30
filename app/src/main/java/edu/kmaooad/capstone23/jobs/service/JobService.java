package edu.kmaooad.capstone23.jobs.service;

import edu.kmaooad.capstone23.jobs.dal.Job;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<Job> findJobById(ObjectId id);
    List<Job> listAll();
    long count();
    Job insert(Job job);
    void delete(Job job);
    void update(Job job);
}

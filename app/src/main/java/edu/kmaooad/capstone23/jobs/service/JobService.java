package edu.kmaooad.capstone23.jobs.service;

import edu.kmaooad.capstone23.jobs.dal.Job;
import org.bson.types.ObjectId;

public interface JobService {
    Job insert(Job job);
}

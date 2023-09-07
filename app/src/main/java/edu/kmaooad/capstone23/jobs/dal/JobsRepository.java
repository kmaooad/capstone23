package edu.kmaooad.capstone23.jobs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class JobsRepository implements PanacheMongoRepository<Job> {

    public Job findByName(String name) {
        return find("name", name).firstResult();
    }

    public Job insert(Job job){
        persist(job);
        return job;
    }
}
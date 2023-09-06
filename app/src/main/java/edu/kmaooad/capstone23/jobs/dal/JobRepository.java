package edu.kmaooad.capstone23.jobs.dal;

import edu.kmaooad.capstone23.orgs.dal.Org;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JobRepository  implements PanacheMongoRepository<Job> {
    public Job insert(Job job){
        persist(job);
        return job;
    }
}

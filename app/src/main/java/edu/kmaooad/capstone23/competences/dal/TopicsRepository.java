package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TopicsRepository implements PanacheMongoRepository<Topic> {


    public Topic insert(Topic topic) {
        persist(topic);
        return topic;
    }
}

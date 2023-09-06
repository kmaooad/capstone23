package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class TopicRepository implements PanacheMongoRepository<Topic> {
    public Optional<Topic> findById(String id) {
        return findByIdOptional(new ObjectId(id));
    }

    public void insert(Topic topic) {
        persist(topic);
    }
}
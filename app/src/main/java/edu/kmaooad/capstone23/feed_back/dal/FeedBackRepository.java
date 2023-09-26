package edu.kmaooad.capstone23.feed_back.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import java.util.Optional;
@ApplicationScoped
public class FeedBackRepository implements PanacheMongoRepository<FeedBack> {

    public Optional<FeedBack> findById(String id) {
        try {
            var objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public FeedBack insert(FeedBack feedBack){
        persist(feedBack);
        return feedBack;
    }

}

package edu.kmaooad.capstone23.messages.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserMessagingSystemRepository implements PanacheMongoRepository<UserMessagingSystem> {

    public UserMessagingSystem insert(UserMessagingSystem UserMessagingSystem) {

        var messageTypesOptional = findByUserIdAndType(UserMessagingSystem);
        if (messageTypesOptional.isPresent())
            return messageTypesOptional.get();

        persist(UserMessagingSystem);
        return UserMessagingSystem;
    }

    public Optional<UserMessagingSystem> findByUserIdAndType(UserMessagingSystem UserMessagingSystem) {
        var document = new Document("userId", UserMessagingSystem.userId).append("messageSystemType", UserMessagingSystem.messageSystemType)
                .append("messageSystemInfo", UserMessagingSystem.messageSystemInfo);
        return find(document).firstResultOptional();
    }

    public void remove(UserMessagingSystem UserMessagingSystem) {
        var messageType = findByUserIdAndType(UserMessagingSystem);
        if (messageType.isPresent())
            delete(messageType.get());
    }

    public List<UserMessagingSystem> findForUser(ObjectId userId) {
       var document = new Document("userId", userId);
       return find(document).list();
    }
}

package edu.kmaooad.capstone23.messages.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.Optional;

@ApplicationScoped
public class UserMessageTypesRepository implements PanacheMongoRepository<UserMessageTypes> {

    public UserMessageTypes insert(UserMessageTypes userMessageTypes)  {
        var messageTypesOptional = findByUserIdAndType(userMessageTypes);
        if(messageTypesOptional.isPresent())
            return messageTypesOptional.get();

        persist(userMessageTypes);
        return userMessageTypes;
    }

    public Optional<UserMessageTypes> findByUserIdAndType(UserMessageTypes userMessageTypes) {
        var document = new Document("userId", userMessageTypes.userId).append("messageType", userMessageTypes.messageType);
        return find(document).firstResultOptional();
    }

    public void remove(UserMessageTypes userMessageTypes) {
        var messageType = findByUserIdAndType(userMessageTypes);
        if(messageType.isPresent())
            delete(messageType.get());
    }

}

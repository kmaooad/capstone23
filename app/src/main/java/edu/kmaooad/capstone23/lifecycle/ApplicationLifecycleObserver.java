package edu.kmaooad.capstone23.lifecycle;

import com.mongodb.client.model.IndexOptions;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.users.interfaces.services.UserService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.bson.Document;

@ApplicationScoped
public class ApplicationLifecycleObserver {
    @Inject
    MembersRepository membersRepository;
    @Inject
    UserService userService;

    void onStart(@Observes StartupEvent ev) {
        // Ensure uniqueness of User email
        IndexOptions options = new IndexOptions().unique(true).sparse(true);
        Document indexUser = new Document("unique_email", 1);
        userService.createIndex(indexUser, options);

        // drop index on "deprecated" email field of members collection field
        membersRepository.mongoCollection().listIndexes().forEach(x -> {
            if (x.getString("name").equals("email_1"))
                membersRepository.mongoCollection().dropIndex("email_1");
        });

        Document indexKeys = new Document();
        indexKeys.put("orgId", 1);
        indexKeys.put("userId", 1);

        membersRepository.mongoCollection().createIndex(indexKeys, options);
    }
}

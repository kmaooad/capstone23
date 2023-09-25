package edu.kmaooad.capstone23.lifecycle;

import com.mongodb.client.model.IndexOptions;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.bson.Document;

@ApplicationScoped
public class ApplicationLifecycleObserver {
    @Inject
    MembersRepository membersRepository;

    void onStart(@Observes StartupEvent ev) {
        membersRepository.mongoCollection().dropIndexes();
    }
}
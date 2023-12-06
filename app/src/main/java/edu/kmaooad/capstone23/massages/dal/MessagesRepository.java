package edu.kmaooad.capstone23.massages.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

    @ApplicationScoped
    public class MessagesRepository implements PanacheMongoRepository<Message> {
        public Message insert(Message notification){
            persist(notification);
            return notification;
        }


    }


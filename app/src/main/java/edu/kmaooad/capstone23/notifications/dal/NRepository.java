package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

public class NRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification n) {
        persist(n);
        return n;
    }
}
package edu.kmaooad.capstone23.feed_back.events;

public class FeedBackCreated {
    private final String id;

    public String getId() {
        return id;
    }

    public FeedBackCreated(String id) {
        this.id = id;
    }
}

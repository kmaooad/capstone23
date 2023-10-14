package edu.kmaooad.capstone23.communication.events;

public class ChatUpdated {
    private final String id;

    public ChatUpdated(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

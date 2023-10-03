package edu.kmaooad.capstone23.tag.events;

public class TagDeleted {
    private final String id;

    public TagDeleted(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

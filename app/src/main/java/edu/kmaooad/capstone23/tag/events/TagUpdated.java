package edu.kmaooad.capstone23.tag.events;

public class TagUpdated {
    public final String id;
    public final String name;

    public TagUpdated(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

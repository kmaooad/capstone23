package edu.kmaooad.capstone23.profiles.events;

public class ProfileImageCreated {
    private String id;

    public ProfileImageCreated(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

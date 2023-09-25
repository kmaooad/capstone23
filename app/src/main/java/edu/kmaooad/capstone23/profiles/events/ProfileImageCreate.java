package edu.kmaooad.capstone23.profiles.events;

public class ProfileImageCreate {
    private String base64ImageString;
    private String id;

    public ProfileImageCreate(String id, String base64ImageString) {
        this.base64ImageString = base64ImageString;
        this.id = id;
    }

    public String getBase64ImageString() {
        return base64ImageString;
    }

    public String getId() {
        return id;
    }
}

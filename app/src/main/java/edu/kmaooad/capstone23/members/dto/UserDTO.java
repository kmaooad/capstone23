package edu.kmaooad.capstone23.members.dto;

import org.bson.types.ObjectId;

public class UserDTO {
    private ObjectId id;

    public UserDTO() {
    }

    public UserDTO(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

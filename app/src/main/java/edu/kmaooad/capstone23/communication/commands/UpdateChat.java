package edu.kmaooad.capstone23.communication.commands;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import jakarta.validation.constraints.*;
import org.bson.types.ObjectId;

public class UpdateChat {
    @NotNull
    private ObjectId id;

    @Size(min = 3, max = 100)
    private String name;

    @Size(max = 150)
    private String description;

    @Pattern(regexp = "Private|Public")
    private String accessType;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getAccessType() {
        return accessType;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}

package edu.kmaooad.capstone23.activities.commands;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;


public class DeleteCourse {
    @NotNull
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
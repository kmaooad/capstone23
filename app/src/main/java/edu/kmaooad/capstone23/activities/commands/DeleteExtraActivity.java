package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;
public class DeleteExtraActivity {
    private ObjectId id;

    public void setId(ObjectId id) {
        this.id = id;
    }


    public ObjectId getId() {
        return id;
    }

}
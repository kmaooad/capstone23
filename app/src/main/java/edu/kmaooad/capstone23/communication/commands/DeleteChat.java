package edu.kmaooad.capstone23.communication.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class DeleteChat {

    @NotNull
    private ObjectId id;

    public ObjectId getChatId() {
        return this.id;
    }

    public void setChatId(ObjectId id) {
        this.id = id;
    }
}

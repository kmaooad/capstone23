package edu.kmaooad.capstone23.experts.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class DeleteExpert {

    @NotBlank
    private String expertId;

    public ObjectId getExpertId() {
        return new ObjectId(expertId);
    }
}

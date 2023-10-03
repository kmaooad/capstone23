package edu.kmaooad.capstone23.tag.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class UpdateTag {
    public ObjectId id;

    @NotBlank
    @Size(min = 2, max = 50)
    public String tagName;
}

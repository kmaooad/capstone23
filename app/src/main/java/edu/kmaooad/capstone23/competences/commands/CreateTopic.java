package edu.kmaooad.capstone23.competences.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTopic {
    @NotBlank
    @Size(min = 5, max = 100)
    public String name;
    public String parentId;
}

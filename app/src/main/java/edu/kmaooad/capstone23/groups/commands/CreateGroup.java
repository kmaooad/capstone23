package edu.kmaooad.capstone23.groups.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGroup {
    @NotBlank
    @Size(min = 1, max = 50)
    public String groupName;
    @NotBlank
    public String templateId;
}

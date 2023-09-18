package edu.kmaooad.capstone23.orgs.commands;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RejectOrg {
    @NotNull
    public String id;

    @Size(min = 10, max = 150)
    public String reason;
}

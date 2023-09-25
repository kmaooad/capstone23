package edu.kmaooad.capstone23.orgs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateOrg {

    @NotNull
    public String orgId;

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    public String orgName;

    @NotBlank
    public String industry;

    @NotBlank
    public String website;

    public String description;

    public String emailDomain;
}

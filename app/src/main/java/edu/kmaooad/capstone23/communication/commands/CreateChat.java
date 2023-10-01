package edu.kmaooad.capstone23.communication.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateChat {
  @NotBlank
  @Size(min = 3, max = 100)
  private String name;

  @Size(max = 150)
  private String description;

  @NotBlank
  @Pattern(regexp = "Private|Public")
  private String accessType;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getAccessType() {
    return accessType;
  }
}

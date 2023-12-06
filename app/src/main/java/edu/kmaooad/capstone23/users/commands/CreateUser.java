package edu.kmaooad.capstone23.users.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUser {
  @NotBlank
  @Size(min = 2, max = 50)
  private String firstName;

  @NotBlank
  @Size(min = 2, max = 50)
  private String lastName;

  @NotBlank
  @Email
  private String email;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailName() {
    return email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

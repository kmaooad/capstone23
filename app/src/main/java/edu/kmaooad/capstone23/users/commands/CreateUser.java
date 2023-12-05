package edu.kmaooad.capstone23.users.commands;

import edu.kmaooad.capstone23.notifications.models.NotificationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

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

    private String phoneNumber;

    private ArrayList<NotificationType> notificationTypes;

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

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

    public void setNotificationTypes(ArrayList<NotificationType> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }
}

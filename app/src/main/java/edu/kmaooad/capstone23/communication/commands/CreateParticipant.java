package edu.kmaooad.capstone23.communication.commands;

import jakarta.validation.constraints.NotBlank;

public class CreateParticipant {
  @NotBlank
  private String chatId;

  @NotBlank
  private String userId;

  public String getChatId() {
    return chatId;
  }

  public String getUserId() {
    return userId;
  }
}

package edu.kmaooad.capstone23.communication.commands;

import edu.kmaooad.capstone23.communication.handlers.EventType;
import jakarta.validation.constraints.NotBlank;

public class CreateParticipant {
  @NotBlank
  private String chatId;

  @NotBlank
  private String userId;

  private EventType eventType; // Added field

  public String getChatId() {
    return chatId;
  }

  public String getUserId() {
    return userId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
}

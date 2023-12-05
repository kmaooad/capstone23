package edu.kmaooad.capstone23.notifications.models;

public enum NotificationType {
  CHAT_PARTICIPANT_CREATED("CHAT_PARTICIPANT_CREATED"),
  PROFESSOR_ACTIVITY_ASSIGNED("PROFESSOR_ACTIVITY_ASSIGNED");

  private final String text;

  /**
   * @param text
   */
  NotificationType(final String text) {
    this.text = text;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return text;
  }
}

package edu.kmaooad.capstone23.communication.events;

import edu.kmaooad.capstone23.common.ManyEvents;

import java.util.List;

public class ChatsBulkDeleted extends ManyEvents<ChatDeleted> {
  public ChatsBulkDeleted(List<ChatDeleted> createdChats) {
    super(createdChats);
  }
}

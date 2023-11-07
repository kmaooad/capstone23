package edu.kmaooad.capstone23.communication.events;

import edu.kmaooad.capstone23.common.ManyEvents;

import java.util.List;

public class ChatsBulkCreated extends ManyEvents<ChatCreated> {
  public ChatsBulkCreated(List<ChatCreated> createdChats) {
    super(createdChats);
  }
}

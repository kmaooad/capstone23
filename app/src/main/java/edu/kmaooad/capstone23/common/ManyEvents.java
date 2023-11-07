package edu.kmaooad.capstone23.common;

import java.util.List;

public abstract class ManyEvents<EventType extends Event> {
  private final List<EventType> events;

  public ManyEvents(List<EventType> events) {
    this.events = events;
  }

  public List<EventType> getEvents() {
    return events;
  }
}



package edu.kmaooad.capstone23.common;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;

public abstract class HandlerTest<EntityType, CommandType, EventType extends Event> {
  protected CommandType command;

  @Inject
  protected CommandHandler<CommandType, EventType> handler;

  protected abstract void initCommand();

  protected abstract void insertPayload(EntityType entity);

  protected Result<EventType> run(EntityType entity) {
    initCommand();
    insertPayload(entity);

    return handler.handle(command);
  }

  protected void assertHandled(Result<EventType> result) {
    Assertions.assertTrue(result.isSuccess());
    Assertions.assertNotNull(result.getValue());
    Assertions.assertFalse(result.getValue().getId().isEmpty());
  }

  protected void assertDenied(Result<EventType> result) {
    Assertions.assertFalse(result.isSuccess());
  }
}

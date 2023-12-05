package edu.kmaooad.capstone23.communication.handlers;

public interface UnitOfWork {
    void configure();

    void notify(EventType eventType, String message);
}

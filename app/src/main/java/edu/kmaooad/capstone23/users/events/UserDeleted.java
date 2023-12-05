package edu.kmaooad.capstone23.users.events;

import edu.kmaooad.capstone23.common.Event;

public class UserDeleted extends Event {
    public UserDeleted(String id) {
        super(id);
    }
}

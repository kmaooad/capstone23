package edu.kmaooad.capstone23.communication.events;

import java.util.List;

public class ChatsBulkDeleted {

    private final List<ChatDeleted> deletedChats;

    public ChatsBulkDeleted(List<ChatDeleted> createdChats) {
        this.deletedChats = createdChats;
    }

    public List<ChatDeleted> getChats() {
        return deletedChats;
    }
}

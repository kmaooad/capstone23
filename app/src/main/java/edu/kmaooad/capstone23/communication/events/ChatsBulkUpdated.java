package edu.kmaooad.capstone23.communication.events;

import java.util.List;

public class ChatsBulkUpdated {
    private final List<ChatUpdated> updatedChats;

    public ChatsBulkUpdated(List<ChatUpdated> updatedChats) {
        this.updatedChats = updatedChats;
    }

    public List<ChatUpdated> getChats() {
        return updatedChats;
    }
}


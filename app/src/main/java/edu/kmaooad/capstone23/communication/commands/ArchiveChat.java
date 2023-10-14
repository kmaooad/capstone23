package edu.kmaooad.capstone23.communication.commands;

import org.bson.types.ObjectId;

public class ArchiveChat {
    private ObjectId chatId;

    // Getter and Setter
    public ObjectId getChatId() {
        return chatId;
    }

    public void setChatId(ObjectId chatId) {
        this.chatId = chatId;
    }
}

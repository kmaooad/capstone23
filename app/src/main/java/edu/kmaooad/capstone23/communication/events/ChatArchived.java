package edu.kmaooad.capstone23.communication.events;

import org.bson.types.ObjectId;

public class ChatArchived {
    private ObjectId chatId;

    public ChatArchived(ObjectId chatId) {
        this.chatId = chatId;
    }

    // Getter
    public ObjectId getChatId() {
        return chatId;
    }
}

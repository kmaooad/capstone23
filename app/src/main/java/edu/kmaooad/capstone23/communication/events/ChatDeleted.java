package edu.kmaooad.capstone23.communication.events;

import org.bson.types.ObjectId;

public class ChatDeleted {
    private ObjectId chatId;

    public ChatDeleted(ObjectId chatId) {
        this.chatId = chatId;
    }

    public ObjectId getChatId() {
        return chatId;
    }
}

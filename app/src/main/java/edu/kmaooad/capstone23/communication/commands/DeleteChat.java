package edu.kmaooad.capstone23.communication.commands;

import org.bson.types.ObjectId;

public class DeleteChat {
    private ObjectId chatId;

    public ObjectId getChatId() {
        return chatId;
    }

    public void setChatId(ObjectId chatId) {
        this.chatId = chatId;
    }
}

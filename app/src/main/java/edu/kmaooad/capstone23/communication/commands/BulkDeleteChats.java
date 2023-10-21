package edu.kmaooad.capstone23.communication.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkDeleteChats {
    @NotEmpty
    private List<@Valid DeleteChat> chats;

    public List<DeleteChat> getChats() {
        return chats;
    }

    public void setChats(List<@Valid DeleteChat> chats) {
        this.chats = chats;
    }
}

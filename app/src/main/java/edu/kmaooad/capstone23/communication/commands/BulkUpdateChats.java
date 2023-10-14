package edu.kmaooad.capstone23.communication.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class BulkUpdateChats {
    @NotEmpty
    private List<@Valid UpdateChat> chats;

    public List<UpdateChat> getChats() {
        return chats;
    }

    public void setChats(List<@Valid UpdateChat> chats) {
        this.chats = chats;
    }
}

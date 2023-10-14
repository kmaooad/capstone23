package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.communication.commands.ArchiveChat;
import edu.kmaooad.capstone23.communication.events.ChatArchived;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/chats/archive")
@APIResponse(responseCode = "200", content = {
        @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ChatArchived.class)
        )}
)
public class ArchiveChatController extends TypicalController<ArchiveChat, ChatArchived> {
}

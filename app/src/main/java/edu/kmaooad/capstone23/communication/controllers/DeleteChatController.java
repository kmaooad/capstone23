package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.communication.commands.DeleteChat;
import edu.kmaooad.capstone23.communication.events.ChatDeleted;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/chats/delete")
@APIResponse(responseCode = "200", content = {
        @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ChatDeleted.class)
        )}
)
public class DeleteChatController extends TypicalController<DeleteChat, ChatDeleted> {
}

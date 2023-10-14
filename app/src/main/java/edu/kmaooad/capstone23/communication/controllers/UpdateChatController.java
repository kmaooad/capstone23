package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.communication.commands.UpdateChat;
import edu.kmaooad.capstone23.communication.events.ChatUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/chats/update")
@APIResponse(responseCode = "200", content = {
        @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ChatUpdated.class)
        )}
)
public class UpdateChatController extends TypicalController<UpdateChat, ChatUpdated> {
}

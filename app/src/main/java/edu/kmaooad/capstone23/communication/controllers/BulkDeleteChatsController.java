package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.communication.commands.BulkCreateChats;
import edu.kmaooad.capstone23.communication.events.ChatsBulkCreated;
import edu.kmaooad.capstone23.communication.events.ChatsBulkDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/chats/bulk-delete")
@APIResponse(responseCode = "200", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ChatsBulkDeleted.class)
    )}
)
public class BulkDeleteChatsController extends TypicalController<BulkCreateChats, ChatsBulkCreated> {
}

package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/chats/create")
@APIResponse(responseCode = "200", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ChatCreated.class)
    )}
)
public class CreateChatController extends TypicalController<CreateChat, ChatCreated> {}

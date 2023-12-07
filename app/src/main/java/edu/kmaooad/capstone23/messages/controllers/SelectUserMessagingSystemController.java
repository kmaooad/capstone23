package edu.kmaooad.capstone23.messages.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingSystem;
import edu.kmaooad.capstone23.messages.events.UserMessagingSystemSelected;
import edu.kmaooad.capstone23.messages.events.UserMessagingTypeSelected;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/messages")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessagingSystemSelected.class)) })
public class SelectUserMessagingSystemController extends TypicalController<SelectUserMessagingSystem, UserMessagingTypeSelected> {
}

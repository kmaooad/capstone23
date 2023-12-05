package edu.kmaooad.capstone23.messages.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingType;
import edu.kmaooad.capstone23.messages.events.UserMessagingTypeSelected;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/messages")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessagingTypeSelected.class)) })
public class SelectUserMessagingTypeController extends TypicalController<SelectUserMessagingType, UserMessagingTypeSelected> {
}

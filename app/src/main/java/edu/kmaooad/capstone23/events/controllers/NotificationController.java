package edu.kmaooad.capstone23.events.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.events.commands.CreateNotification;
import edu.kmaooad.capstone23.events.events.NotificationCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/notification/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationCreated.class)) })
public class NotificationController extends TypicalController<CreateNotification, NotificationCreated> {
}


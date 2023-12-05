package edu.kmaooad.capstone23.notifications.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.notifications.commands.NotificationTrigger;
import edu.kmaooad.capstone23.notifications.events.NotificationTriggered;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/notifications")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationTrigger.class))})
public class NotificationTriggerController extends TypicalController<NotificationTrigger, NotificationTriggered> {
}

package edu.kmaooad.capstone23.notifications.controllers;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.notifications.commands.UserNotificationTrigger;
import edu.kmaooad.capstone23.notifications.events.UserNotificationTriggered;
import jakarta.ws.rs.Path;

@Path("/notifications")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserNotificationTriggered.class)) })
public class UserNotificationTriggerController extends TypicalController<UserNotificationTrigger, UserNotificationTriggered> {
}
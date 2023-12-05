package edu.kmaooad.capstone23.users.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationEvents;
import edu.kmaooad.capstone23.users.events.UserNotificationEventsSetted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/users/set-notification-events")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserNotificationEventsSetted.class))})
public class SetUserNotificationEventsController extends TypicalController<SetUserNotificationEvents, UserNotificationEventsSetted> {
}

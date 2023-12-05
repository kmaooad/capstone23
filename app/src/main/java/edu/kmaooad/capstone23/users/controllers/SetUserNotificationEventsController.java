package edu.kmaooad.capstone23.users.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationMethods;
import edu.kmaooad.capstone23.users.events.UserNotificationMethodsSetted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/users/set-notification-events")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserNotificationMethodsSetted.class))})
public class SetUserNotificationEventsController extends TypicalController<SetUserNotificationMethods, UserNotificationMethodsSetted> {
}

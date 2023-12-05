package edu.kmaooad.capstone23.users.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationPreferences;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.events.UserNotificationPreferencesSetted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/users/notification-preferences")
@APIResponse(responseCode = "200", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UserCreated.class)
    )}
)
public class SetUserNotificationPreferencesController extends TypicalController<SetUserNotificationPreferences, UserNotificationPreferencesSetted> {
}

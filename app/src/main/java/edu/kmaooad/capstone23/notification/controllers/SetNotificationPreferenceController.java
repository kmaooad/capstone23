package edu.kmaooad.capstone23.notification.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.notification.commands.SetNotificationPreferenceCommand;
import edu.kmaooad.capstone23.notification.events.UpdatedNotificationPreference;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/notification-preferences/set")
@APIResponse(responseCode = "200", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UpdatedNotificationPreference.class)
    )
})
public class SetNotificationPreferenceController extends TypicalController<SetNotificationPreferenceCommand, UpdatedNotificationPreference> {
}

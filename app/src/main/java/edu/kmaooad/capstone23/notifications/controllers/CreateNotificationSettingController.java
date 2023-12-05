package edu.kmaooad.capstone23.notifications.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.notifications.commands.CreateNotificationSetting;
import edu.kmaooad.capstone23.notifications.events.NotificationSettingCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/notifications/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json",
                schema = @Schema(implementation = NotificationSettingCreated.class))
})
public class CreateNotificationSettingController
        extends TypicalController<CreateNotificationSetting, NotificationSettingCreated > {
}

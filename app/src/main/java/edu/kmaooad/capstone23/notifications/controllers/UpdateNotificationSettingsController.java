package edu.kmaooad.capstone23.notifications.controllers;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.notifications.commands.UpdateNotificationSettings;
import edu.kmaooad.capstone23.notifications.events.NotificationSettingsUpdated;
import jakarta.ws.rs.Path;

@Path("/notification-preferences/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationSettingsUpdated.class)) })
public class UpdateNotificationSettingsController extends TypicalController<UpdateNotificationSettings, NotificationSettingsUpdated> {
}

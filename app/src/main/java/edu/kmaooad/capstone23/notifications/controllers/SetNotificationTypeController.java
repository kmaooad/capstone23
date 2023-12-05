package edu.kmaooad.capstone23.notifications.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.notifications.commands.SetNotificationType;
import edu.kmaooad.capstone23.notifications.events.NotificationTypeSet;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/notifications/set")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationTypeSet.class))})
public class SetNotificationTypeController extends TypicalController<SetNotificationType, NotificationTypeSet> {
}

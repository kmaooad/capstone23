package edu.kmaooad.capstone23.feed_back.notifications.controller;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.feed_back.notifications.command.CreateFeedBackNotification;
import edu.kmaooad.capstone23.feed_back.notifications.event.FeedBackNotificationCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/notification/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = FeedBackNotificationCreated.class)) })
public class FeedBackNotificationController extends TypicalController<CreateFeedBackNotification, FeedBackNotificationCreated> {
}
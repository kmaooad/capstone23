package edu.kmaooad.capstone23.feed_back.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.feed_back.commands.UpdateFeedBack;
import edu.kmaooad.capstone23.feed_back.events.FeedBackUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/feedback/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = FeedBackUpdated.class))})
public class UpdateFeedBackController extends TypicalController<UpdateFeedBack, FeedBackUpdated> {
}
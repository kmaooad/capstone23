package edu.kmaooad.capstone23.feed_back.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.DeleteSkillSet;
import edu.kmaooad.capstone23.competences.events.SkillSetDeleted;
import edu.kmaooad.capstone23.feed_back.commands.DeleteFeedBack;
import edu.kmaooad.capstone23.feed_back.events.FeedBackDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/feedback/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = FeedBackDeleted.class))})
public class DeleteFeedBackController extends TypicalController<DeleteFeedBack, FeedBackDeleted> {
}

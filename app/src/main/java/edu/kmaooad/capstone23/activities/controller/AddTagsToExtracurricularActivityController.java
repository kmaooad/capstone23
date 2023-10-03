package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.AddTagsToExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.TagsAddedToExtracurricularActivity;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/extracurricularActivity/add-tags")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TagsAddedToExtracurricularActivity.class))})
public class AddTagsToExtracurricularActivityController extends TypicalController<AddTagsToExtracurricularActivity, TagsAddedToExtracurricularActivity> {
}

package edu.kmaooad.capstone23.tag.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.tag.commands.DeleteTag;
import edu.kmaooad.capstone23.tag.events.TagDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/tags/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TagDeleted.class))})
public class DeleteTagController extends TypicalController<DeleteTag, TagDeleted> {
}

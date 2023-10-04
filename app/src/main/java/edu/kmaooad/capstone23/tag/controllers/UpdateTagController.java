package edu.kmaooad.capstone23.tag.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.tag.commands.UpdateTag;
import edu.kmaooad.capstone23.tag.events.TagUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/tags/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = GroupCreated.class)) })
public class UpdateTagController extends TypicalController<UpdateTag, TagUpdated> {
}

package edu.kmaooad.capstone23.tag.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.CreateInvitationLink;
import edu.kmaooad.capstone23.tag.commands.CreateTag;
import edu.kmaooad.capstone23.tag.events.TagCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/tags/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CreateTag.class))
})
public class CreateTagController extends TypicalController<CreateTag, TagCreated> {
}

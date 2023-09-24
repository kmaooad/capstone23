package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.cvs.commands.RemoveSkillFromCV;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/cvs/update/remove-skill")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json",
                schema = @Schema(implementation = CVUpdated.class)) })
public class RemoveSkillFromCVController extends TypicalController<RemoveSkillFromCV, CVUpdated> {
}

package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/expert/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertDeleted.class)) })
public class DeleteExpertController extends TypicalController<DeleteExpert, ExpertDeleted> {
}

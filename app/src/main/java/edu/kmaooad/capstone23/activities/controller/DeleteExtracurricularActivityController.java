package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.DeleteExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityDeleted;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/extracurricularActivity/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExtracurricularActivityDeleted.class)) })
public class DeleteExtracurricularActivityController extends TypicalController<DeleteExtracurricularActivity, ExtracurricularActivityDeleted> {
}

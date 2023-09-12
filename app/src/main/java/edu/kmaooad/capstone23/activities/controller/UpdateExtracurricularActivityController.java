package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.UpdateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityUpdated;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/extracurricularActivity/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExtracurricularActivityUpdated.class)) })
public class UpdateExtracurricularActivityController extends TypicalController<UpdateExtracurricularActivity, ExtracurricularActivityUpdated> {
}

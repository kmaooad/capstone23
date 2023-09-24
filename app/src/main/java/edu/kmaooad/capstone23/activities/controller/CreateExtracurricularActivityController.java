package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.CreateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityCreated;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;

@Path("/extracurricularActivity/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExtracurricularActivityCreated.class)) })
public class CreateExtracurricularActivityController extends TypicalController<CreateExtracurricularActivity, ExtracurricularActivityCreated> {
}

package edu.kmaooad.capstone23.projs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.projs.commands.CreateProj;
import edu.kmaooad.capstone23.projs.events.ProjCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/projs/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ProjCreated.class)) })
public class CreateProjController extends TypicalController<CreateProj, ProjCreated> {
}
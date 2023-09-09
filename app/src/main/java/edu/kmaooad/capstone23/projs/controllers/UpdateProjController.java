package edu.kmaooad.capstone23.projs.controllers;
import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.projs.commands.UpdateProj;
import edu.kmaooad.capstone23.projs.events.ProjUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/projs/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ProjUpdated.class)) })
public class UpdateProjController extends TypicalController<UpdateProj, ProjUpdated> {

}
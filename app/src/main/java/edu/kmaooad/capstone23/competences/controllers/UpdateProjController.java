package edu.kmaooad.capstone23.competences.controllers;
import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.UpdateProject;
import edu.kmaooad.capstone23.competences.events.ProjUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/projs/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ProjUpdated.class)) })
public class UpdateProjController extends TypicalController<UpdateProject, ProjUpdated> {

}
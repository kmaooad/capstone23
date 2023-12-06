package edu.kmaooad.capstone23.competences.controllers;
import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.events.ProjDeleted;
import edu.kmaooad.capstone23.competences.commands.DeleteProject;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/projs/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ProjDeleted.class)) })
public class DeleteProjController extends TypicalController<DeleteProject, ProjDeleted> {

}
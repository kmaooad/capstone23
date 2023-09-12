package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.cvs.commands.DeleteCV;
import edu.kmaooad.capstone23.cvs.events.CVDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/cvs/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json",
                schema = @Schema(implementation = CVDeleted.class)) })
public class DeleteCVController extends TypicalController<DeleteCV, CVDeleted> {
}

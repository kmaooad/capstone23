package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.DeleteOrg;
import edu.kmaooad.capstone23.orgs.events.OrgDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/orgs/delete")
@APIResponse(responseCode = "200", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = OrgDeleted.class)
    )}
)
public class DeleteOrgController extends TypicalController<DeleteOrg, OrgDeleted> {
}

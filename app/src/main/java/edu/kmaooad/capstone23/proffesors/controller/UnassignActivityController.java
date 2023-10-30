package edu.kmaooad.capstone23.proffesors.controller;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.proffesors.commands.AssignActivity;
import edu.kmaooad.capstone23.proffesors.commands.UnassignActivity;
import edu.kmaooad.capstone23.proffesors.events.ActivityAssigned;
import edu.kmaooad.capstone23.proffesors.events.ActivityUnassigned;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/proffesor/unassignActivity")
@APIResponse(responseCode = "200", content = {
        @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OrgCreated.class)
        )}
)
public class UnassignActivityController extends TypicalController<UnassignActivity, ActivityUnassigned> {
}


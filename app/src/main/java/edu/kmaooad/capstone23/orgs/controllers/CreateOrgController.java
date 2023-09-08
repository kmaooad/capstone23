package edu.kmaooad.capstone23.orgs.controllers;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.ws.rs.Path;

@Path("/orgs/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = OrgCreated.class)) })
public class CreateOrgController extends TypicalController<CreateOrg, OrgCreated> {
}

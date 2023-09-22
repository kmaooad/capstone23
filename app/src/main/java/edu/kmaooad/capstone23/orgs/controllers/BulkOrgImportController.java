package edu.kmaooad.capstone23.orgs.controllers;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.ImportOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.orgs.events.OrgImport;
import jakarta.ws.rs.Path;

@Path("/orgs/import")
@APIResponse(responseCode = "200", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = OrgCreated.class)
    )}
)
public class BulkOrgImportController extends TypicalController<ImportOrg, OrgImport> {
}

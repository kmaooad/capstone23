package edu.kmaooad.capstone23.access_rules.controllers;

import edu.kmaooad.capstone23.access_rules.commands.DeleteAccessRule;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleDeleted;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/accessrules/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = AccessRuleDeleted.class)) })
public class DeleteAccessRuleController extends TypicalController<DeleteAccessRule, AccessRuleDeleted> {
}

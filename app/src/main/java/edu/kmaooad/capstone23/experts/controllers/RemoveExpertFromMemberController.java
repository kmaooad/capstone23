package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromMember;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/experts/member/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertRemovedFromMember.class)) })
public class RemoveExpertFromMemberController
        extends TypicalController<RemoveExpertFromMemberController, ExpertRemovedFromMember> {
}

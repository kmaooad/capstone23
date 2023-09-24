package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToMember;
import edu.kmaooad.capstone23.experts.events.ExpertAssigned;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/experts/assign_expert_to_member")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertAssigned.class)) })
public class AssignExpertToMemberController extends TypicalController<AssignExpertToMember, ExpertAssigned> {
}

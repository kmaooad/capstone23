package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromDepartment;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromMember;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromDepartment;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromMember;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/experts/remove_expert_from_department")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertRemovedFromDepartment.class)) })
public class RemoveExpertFromDepartmentController extends TypicalController<RemoveExpertFromDepartment, ExpertRemovedFromDepartment> {
}

package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.AssignDepartmentToExpert;
import edu.kmaooad.capstone23.experts.events.DepartmentAssignedToExpert;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/experts/departments/assign")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentAssignedToExpert.class)) })
public class AssignDepartmentToExpertController extends TypicalController<AssignDepartmentToExpert, DepartmentAssignedToExpert> {
}


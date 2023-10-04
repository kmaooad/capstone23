package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToProject;
import edu.kmaooad.capstone23.experts.events.ExpertAssignedToProject;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/experts/assign_expert_to_project")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertAssignedToProject.class)) })
public class AssignExpertToProjectController extends TypicalController<AssignExpertToProject, ExpertAssignedToProject> {
}
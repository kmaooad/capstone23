package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromProject;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromProject;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/experts/remove_expert_from_project")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertRemovedFromProject.class)) })
public class RemoveExpertFromProjectController
        extends TypicalController<RemoveExpertFromProject, ExpertRemovedFromProject> {
}

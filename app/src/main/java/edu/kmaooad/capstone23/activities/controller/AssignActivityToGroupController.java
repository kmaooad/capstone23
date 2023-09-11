package edu.kmaooad.capstone23.activities.controller;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.activities.commands.AssignActivityToGroup;
import jakarta.ws.rs.Path;

@Path("/activities/assign/group")
@APIResponse(responseCode = "200", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = AssignActivityToGroup.class)) })
public class AssignActivityToGroupController {
    
}

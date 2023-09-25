package edu.kmaooad.capstone23.groups.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/groups/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = GroupCreated.class)) })
public class CreateGroupController extends TypicalController<CreateGroup,GroupCreated> {
}

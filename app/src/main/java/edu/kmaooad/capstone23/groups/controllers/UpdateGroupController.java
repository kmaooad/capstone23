package edu.kmaooad.capstone23.groups.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.groups.commands.UpdateGroup;
import edu.kmaooad.capstone23.groups.events.GroupUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/groups/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = GroupUpdated.class)) })
public class UpdateGroupController extends TypicalController<UpdateGroup, GroupUpdated> {
}

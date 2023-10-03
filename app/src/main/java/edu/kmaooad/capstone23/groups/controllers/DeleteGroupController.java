package edu.kmaooad.capstone23.groups.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.groups.commands.DeleteGroup;
import edu.kmaooad.capstone23.groups.events.GroupDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/groups/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = GroupDeleted.class)) })
public class DeleteGroupController extends TypicalController<DeleteGroup, GroupDeleted> {
}

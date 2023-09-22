package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.DeleteExtraActivity;
import edu.kmaooad.capstone23.activities.events.ExtraActivityDeleted;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/activities/extra/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExtraActivityDeleted.class)) })
public class DeleteExtraActivityController extends TypicalController<DeleteExtraActivity, ExtraActivityDeleted> {

}

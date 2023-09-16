package edu.kmaooad.capstone23.activities.controller;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.activities.commands.CreateActivity;
import edu.kmaooad.capstone23.activities.events.ActivityCreated;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;

@Path("/activities/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityCreated.class)) })
public class CreateActivityController extends TypicalController<CreateActivity, ActivityCreated> {

}
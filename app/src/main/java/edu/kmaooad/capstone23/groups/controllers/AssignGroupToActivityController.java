package edu.kmaooad.capstone23.groups.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.groups.commands.AssignGroupToActivity;
import edu.kmaooad.capstone23.groups.events.ActivityAssigned;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/jobs/assign_group_to_activities")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityAssigned.class)) })
public class AssignGroupToActivityController extends TypicalController<AssignGroupToActivity, ActivityAssigned> {
}

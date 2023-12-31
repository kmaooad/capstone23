package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.DeleteRelateJobToActivities;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.ActivityUnrelated;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/jobs/delete_relation_to_activities")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = JobDeleted.class)) })
public class DeleteRelationJobToActivitiesController extends TypicalController<DeleteRelateJobToActivities, ActivityUnrelated> {
}
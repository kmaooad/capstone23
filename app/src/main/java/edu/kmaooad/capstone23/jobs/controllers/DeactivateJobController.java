package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/jobs/deactivate")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = JobDeactivated.class)) })
public class DeactivateJobController extends TypicalController<DeactivateJob, JobDeactivated> {

}
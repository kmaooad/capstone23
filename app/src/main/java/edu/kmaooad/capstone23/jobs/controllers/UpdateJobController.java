package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.UpdateJob;
import edu.kmaooad.capstone23.jobs.events.JobUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/jobs/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = JobUpdated.class)) })
public class UpdateJobController extends TypicalController<UpdateJob, JobUpdated> {
}

package edu.kmaooad.capstone23.jobs.controllers;


import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.ActivateJob;
import edu.kmaooad.capstone23.jobs.events.JobActivated;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/jobs/activate")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = JobActivated.class)) })
public class ActivateJobController extends TypicalController<ActivateJob, JobActivated> {
}


package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.cvs.commands.CreateJobPref;
import edu.kmaooad.capstone23.cvs.events.JobPrefCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/cvs/preferences")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json",
                schema = @Schema(implementation = JobPrefCreated.class)) })
public class CreateJobPrefController extends TypicalController<CreateJobPref, JobPrefCreated> {
}

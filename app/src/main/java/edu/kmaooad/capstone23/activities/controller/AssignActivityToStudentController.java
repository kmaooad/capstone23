package edu.kmaooad.capstone23.activities.controller;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.activities.commands.AssignActivityToStudent;
import edu.kmaooad.capstone23.activities.events.AssignActivityToStudentEvent;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;

@Path("/activities/assign/student")
@APIResponse(responseCode = "200", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = AssignActivityToStudentEvent.class)) })
public class AssignActivityToStudentController extends TypicalController<AssignActivityToStudent, AssignActivityToStudentEvent> {
    
}

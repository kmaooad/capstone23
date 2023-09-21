package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/activities/extra/create")
@APIResponse(responseCode = "200", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = CourseCreated.class)) })
public class CreateActivityController extends TypicalController<CreateCourse, CourseCreated> {

}

package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.UpdateCourse;
import edu.kmaooad.capstone23.activities.events.CourseUpdated;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/activities/courses/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CourseUpdated.class)) })
public class UpdateCourseController extends TypicalController<UpdateCourse, CourseUpdated> {

}
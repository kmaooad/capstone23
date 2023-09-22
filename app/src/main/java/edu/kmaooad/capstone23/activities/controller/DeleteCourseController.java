package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.DeleteCourse;
import edu.kmaooad.capstone23.activities.events.CourseDeleted;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/activities/courses/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDeleted.class)) })
public class DeleteCourseController extends TypicalController<DeleteCourse, CourseDeleted> {

}
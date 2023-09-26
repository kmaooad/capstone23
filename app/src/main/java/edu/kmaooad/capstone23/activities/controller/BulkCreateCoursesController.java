package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.BulkCreateCourses;
import edu.kmaooad.capstone23.activities.events.BulkCoursesCreated;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/activities/courses/bulk/create")
@APIResponse(responseCode = "200", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = BulkCoursesCreated.class)) })
public class BulkCreateCoursesController extends TypicalController<BulkCreateCourses, BulkCoursesCreated> {
}

package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.AddTagToCourse;
import edu.kmaooad.capstone23.activities.commands.FindCoursesByTag;
import edu.kmaooad.capstone23.activities.events.CoursesFoundByTag;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/activities/courses/find/tag/")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = AddTagToCourse.class)) })
public class FindCoursesByTagNameController extends TypicalController<FindCoursesByTag, CoursesFoundByTag> {
}

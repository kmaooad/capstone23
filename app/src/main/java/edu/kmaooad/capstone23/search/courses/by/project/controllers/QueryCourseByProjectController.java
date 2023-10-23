package edu.kmaooad.capstone23.search.courses.by.project.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import edu.kmaooad.capstone23.search.courses.by.project.events.CourseQueriedByProject;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/search/courses/by/project")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CourseQueriedByProject.class))
})
public class QueryCourseByProjectController extends TypicalController<QueryByIdCommand, CourseQueriedByProject> {}

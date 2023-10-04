package edu.kmaooad.capstone23.search.courses.by.department.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import edu.kmaooad.capstone23.search.courses.by.department.events.CourseQueriedByDepartment;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/search/courses/by/dept")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CourseQueriedByDepartment.class)) })
public class QueryCourseByDepartmentController extends TypicalController<QueryByIdCommand, CourseQueriedByDepartment> {}
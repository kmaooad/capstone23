package edu.kmaooad.capstone23.search.courses.by.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import edu.kmaooad.capstone23.search.courses.by.orgs.events.CourseQueriedByOrg;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/search/courses/by/org")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CourseQueriedByOrg.class)) })
public class QueryCourseByOrgController extends TypicalController<QueryByIdCommand, CourseQueriedByOrg> {}
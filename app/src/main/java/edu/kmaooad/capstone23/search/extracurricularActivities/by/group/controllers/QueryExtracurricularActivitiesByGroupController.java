package edu.kmaooad.capstone23.search.extracurricularActivities.by.group.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import edu.kmaooad.capstone23.search.courses.by.group.events.CourseQueriedByGroup;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/search/extracurricularActivity/by/group")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CourseQueriedByGroup.class)) })
public class QueryExtracurricularActivitiesByGroupController extends TypicalController<QueryByIdCommand, CourseQueriedByGroup> {}
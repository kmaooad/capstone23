package edu.kmaooad.capstone23.search.courses.by.skillset.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import edu.kmaooad.capstone23.search.courses.by.group.events.CourseQueriedByGroup;
import edu.kmaooad.capstone23.search.courses.by.skillset.events.CourseQueriedBySkillSet;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/search/courses/by/skillset")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CourseQueriedByGroup.class)) })
public class QueryCourseBySkillSetController extends TypicalController<QueryByIdCommand, CourseQueriedBySkillSet> {}
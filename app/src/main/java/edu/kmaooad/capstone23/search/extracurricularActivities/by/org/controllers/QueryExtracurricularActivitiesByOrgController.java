package edu.kmaooad.capstone23.search.extracurricularActivities.by.org.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import edu.kmaooad.capstone23.search.extracurricularActivities.by.org.events.ExtracurricularActivityQueriedByOrg;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/search/extracurricularActivity/by/org")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExtracurricularActivityQueriedByOrg.class)) })
public class QueryExtracurricularActivitiesByOrgController extends TypicalController<QueryByIdCommand, ExtracurricularActivityQueriedByOrg> {}
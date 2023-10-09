package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.FindExtracurricularByTag;
import edu.kmaooad.capstone23.activities.events.ExtracurricularFoundByTag;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/extracurricularActivity/find/tag/")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ExtracurricularFoundByTag.class)) })
public class FindExtracurricularActivitiesByTagNameController extends TypicalController<FindExtracurricularByTag, ExtracurricularFoundByTag> {
}

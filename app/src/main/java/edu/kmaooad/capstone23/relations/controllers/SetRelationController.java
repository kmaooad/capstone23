package edu.kmaooad.capstone23.relations.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.relations.commands.SetRelation;
import edu.kmaooad.capstone23.relations.events.RelationSet;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/relations/set")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = RelationSet.class))
})
public class SetRelationController extends TypicalController<SetRelation, RelationSet> {}

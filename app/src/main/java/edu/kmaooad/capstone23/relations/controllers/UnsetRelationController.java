package edu.kmaooad.capstone23.relations.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.relations.commands.UnsetRelation;
import edu.kmaooad.capstone23.relations.events.RelationUnset;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/relations/unset")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = RelationUnset.class))
})
public class UnsetRelationController extends TypicalController<UnsetRelation, RelationUnset> {}

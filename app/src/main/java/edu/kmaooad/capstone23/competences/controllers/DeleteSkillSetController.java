package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.DeleteSkillSet;
import edu.kmaooad.capstone23.competences.events.SkillSetDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/skillset/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = SkillSetDeleted.class))})
public class DeleteSkillSetController extends TypicalController<DeleteSkillSet, SkillSetDeleted> {
}

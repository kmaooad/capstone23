package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.DeleteSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.competences.events.SkillDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/skills/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = SkillCreated.class)) })
public class DeleteSkillController extends TypicalController<DeleteSkill, SkillDeleted> {
}

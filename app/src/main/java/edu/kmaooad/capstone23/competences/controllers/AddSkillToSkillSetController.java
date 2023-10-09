package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.AddSkillToSkillSet;
import edu.kmaooad.capstone23.competences.events.SkillToSkillSetAdded;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/skillset/addskill")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = SkillToSkillSetAdded.class)) })
public class AddSkillToSkillSetController extends TypicalController<AddSkillToSkillSet, SkillToSkillSetAdded> {
}
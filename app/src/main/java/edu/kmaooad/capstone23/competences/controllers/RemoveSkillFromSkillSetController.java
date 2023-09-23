package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.RemoveSkillFromSkillSet;
import edu.kmaooad.capstone23.competences.events.SkillFromSkillSetRemoved;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/skillset/removeskill")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = SkillFromSkillSetRemoved.class)) })
public class RemoveSkillFromSkillSetController extends TypicalController<RemoveSkillFromSkillSet, SkillFromSkillSetRemoved> {
}
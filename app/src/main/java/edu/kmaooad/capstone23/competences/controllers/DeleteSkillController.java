package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.DeleteSkill;
import edu.kmaooad.capstone23.competences.events.SkillDeleted;
import jakarta.ws.rs.Path;

@Path("/skills/delete")
public class DeleteSkillController extends TypicalController<DeleteSkill, SkillDeleted> {
}

package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.UpdateSkill;
import edu.kmaooad.capstone23.competences.events.SkillUpdated;
import jakarta.ws.rs.Path;

@Path("/skills/update")
public class UpdateSkillController extends TypicalController<UpdateSkill, SkillUpdated> {
}

package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import jakarta.ws.rs.Path;

@Path("/skills/create")
public class CreateSkillsController extends TypicalController<CreateSkill, SkillCreated> {
}

package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.CreateTopic;
import edu.kmaooad.capstone23.competences.events.TopicCreated;
import jakarta.ws.rs.Path;

@Path("/topics/create")
public class CreateTopicsController extends TypicalController<CreateTopic, TopicCreated> {
}

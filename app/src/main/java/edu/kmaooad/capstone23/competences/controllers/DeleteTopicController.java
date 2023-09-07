package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.DeleteTopic;
import edu.kmaooad.capstone23.competences.events.TopicDeleted;
import jakarta.ws.rs.Path;

@Path("/topics/delete")
public class DeleteTopicController extends TypicalController<DeleteTopic, TopicDeleted> {
}

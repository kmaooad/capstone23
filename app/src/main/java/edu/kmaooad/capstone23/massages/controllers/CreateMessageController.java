package edu.kmaooad.capstone23.massages.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.massages.commands.CreateMessage;
import edu.kmaooad.capstone23.massages.events.MessageCreated;
import jakarta.ws.rs.Path;


    @Path("/message/create")
    public class CreateMessageController extends TypicalController<CreateMessage, MessageCreated> {

    }

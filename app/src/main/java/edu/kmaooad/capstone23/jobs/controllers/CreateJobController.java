package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import jakarta.ws.rs.Path;

@Path("/jobs/create")
public class CreateJobController extends TypicalController<CreateJob, JobCreated> {

}

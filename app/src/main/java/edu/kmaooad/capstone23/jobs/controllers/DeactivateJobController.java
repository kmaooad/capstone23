package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import jakarta.ws.rs.Path;

@Path("/jobs/create")
public class DeactivateJobController extends TypicalController<DeactivateJob, JobDeactivated> {

}
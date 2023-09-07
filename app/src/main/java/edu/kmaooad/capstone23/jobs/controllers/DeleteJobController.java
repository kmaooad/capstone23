package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.HandlingError;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.DeleteJob;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/jobs/delete")
public class DeleteJobController  extends TypicalDeleteController<DeleteJob, JobDeleted> {
}

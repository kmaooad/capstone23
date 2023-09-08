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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/jobs/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = JobDeleted.class)) })
public class DeleteJobController  extends TypicalController<DeleteJob, JobDeleted> {
//    @Inject
//    CommandHandler<DeleteJob, JobDeleted> commandHandler;
//
//    @DELETE
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @APIResponses(value = {
//            @APIResponse(responseCode = "200", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = JobDeleted.class)) }),
//            @APIResponse(responseCode = "400", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = HandlingError.class)) }),
//            @APIResponse(responseCode = "500")
//    })
//    public Response delete(@PathParam("id") ObjectId id) {
//        try {
//            Result<JobDeleted> result = commandHandler.handle(new DeleteJob(id));
//
//            if (!result.isSuccess()) {
//                System.out.println(Response.status(400).entity(result.toError()).build());
//                return Response.status(400).entity(result.toError()).build();
//            }
//            return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
//        } catch (Exception e) {
//            System.out.println(e);
//            return Response.status(500).build();
//        }
//    }
}

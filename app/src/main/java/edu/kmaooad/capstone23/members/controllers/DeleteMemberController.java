package edu.kmaooad.capstone23.members.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.HandlingError;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.common.ValidatingHandler;
import edu.kmaooad.capstone23.members.commands.DeleteMember;
import edu.kmaooad.capstone23.members.events.DeletedMember;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/members")
public class DeleteMemberController {
    @Inject
    CommandHandler<DeleteMember, DeletedMember> commandHandler;

    @DELETE
    @Path("/{id}")
    @APIResponse(responseCode = "204")
    @APIResponse(responseCode = "404")
    @APIResponse(responseCode = "400", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HandlingError.class))})
    @APIResponse(responseCode = "500")
    public Response deleteCommand(@PathParam("id") String id) {
        try {
            var command = new DeleteMember();
            command.setMemberId(id);
            Result<DeletedMember> result = commandHandler.handle(command);

            if (!result.isSuccess()) {
                return Response.status(400).entity(result.toError()).build();
            }
            if (result.getValue().isSuccess())
                return Response.noContent().build();
            else
                return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}

package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.AcceptInvitationLink;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


public class AcceptInvitationLinkController {
    @Inject
    CommandHandler<AcceptInvitationLink, ExpertCreated> commandHandler;

    @Path("/experts/invitation/accept")
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertCreated.class))
    })
    public Response acceptInvitationLink(@QueryParam("id") ObjectId invitationId) {
        try {
            var acceptInvitationLink = new AcceptInvitationLink(invitationId);
            Result<ExpertCreated> result = commandHandler.handle(acceptInvitationLink);

            if (!result.isSuccess()) {
                return Response.status(400).entity(result.toError()).build();
            }

            return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}

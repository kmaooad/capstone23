package edu.kmaooad.capstone23.common;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class TypicalController<T1, T2> {

    @Inject
    private CommandHandler<T1, T2> createOrgHandler;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrgCreated.class)) }),
            @APIResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HandlingError.class)) }),
            @APIResponse(responseCode = "500")
    })
    public Response createOrg(T1 command) {
        try {
            Result<T2> result = createOrgHandler.handle(command);

            if (!result.isSuccess()) {
                return Response.status(400).entity(result.toError()).build();
            }

            return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}
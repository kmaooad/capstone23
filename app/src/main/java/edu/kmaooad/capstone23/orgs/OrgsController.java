package edu.kmaooad.capstone23.orgs;

import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.jboss.resteasy.reactive.RestResponse;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orgs")
public class OrgsController {

    @Inject
    private CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = OrgCreated.class)) })
    public Response createOrg(CreateOrg command) {
        Result<OrgCreated> result = createOrgHandler.handle(command);
        if (!result.isSuccess()) {
            return Response.status(400, result.getMessage()).build();
        }
        return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
    }
}

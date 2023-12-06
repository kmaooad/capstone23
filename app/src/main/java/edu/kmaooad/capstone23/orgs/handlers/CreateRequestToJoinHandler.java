package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.orgs.services.OrgsServiceImpl;
import edu.kmaooad.capstone23.orgs.commands.RequestToJoinOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.Request;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateRequestToJoinHandler implements CommandHandler<RequestToJoinOrg, RequestCreated> {

    @Inject
    private OrgsServiceImpl orgService;

    @Inject
    private EntityBanService banService;

    private final String defaultStatus = "pending";

    public Result<RequestCreated> handle(RequestToJoinOrg command) {

       
        Org org = orgService.findById(command.getOrgId());
        if (org == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Org not found");
        }
        if (banService.findForEntity(IsEntityBannedV2.ORGANIZATION_BAN_ENTITY_TYPE, org.id.toString()).isPresent())
            return new Result<>(ErrorCode.EXCEPTION, "Org is banned");

        Request request = new Request();
        // TODO: validate userName once we have a user service
        request.userName = command.getUserName();
        request.orgId = command.getOrgId();
        request.status = defaultStatus;

        orgService.insertRequest(request);

        RequestCreated result = new RequestCreated(request.id.toString());

        return new Result<>(result);
    }
}

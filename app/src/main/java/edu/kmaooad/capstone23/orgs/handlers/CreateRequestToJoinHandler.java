package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.orgs.commands.RequestToJoinOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.dal.Request;
import edu.kmaooad.capstone23.orgs.dal.RequestsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateRequestToJoinHandler implements CommandHandler<RequestToJoinOrg, RequestCreated> {

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private RequestsRepository requestsRepository;

    private final String defaultStatus = "pending";

    public Result<RequestCreated> handle(RequestToJoinOrg command) {


        Org department = orgsRepository.findById(new ObjectId(command.getOrgId()));
        if (department == null) {
            return new Result(ErrorCode.EXCEPTION, "Org not found");
        }

        Request request = new Request();
        // TODO: validate userName once we have a user service
        request.userName = command.getUserName();
        request.orgId = command.getOrgId();
        request.status = defaultStatus;

        requestsRepository.insert(request);

        RequestCreated result = new RequestCreated(request.id.toString());

        return new Result(result);
    }
}

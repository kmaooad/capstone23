package edu.kmaooad.capstone23.commands.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.activities.commands.RequestToJoinExtraAct;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.dal.Request;
import edu.kmaooad.capstone23.activities.dal.RequestsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateRequestToJoinExtraActHandler implements CommandHandler<RequestToJoinOrg, RequestCreated> {

    @Inject
    private Repository extraArcRepository;

    @Inject
    private RequestsRepository requestsRepository;

    private final String defaultStatus = "pending";

    public Result<RequestCreated> handle(RequestToJoinExtraAct command) {


        ExtraAct act = extraArcRepository.findById(new ObjectId(command.getExtraActId()));
        if (act == null) {
            return new Result(ErrorCode.EXCEPTION, "Activity not found");
        }

        Request request = new Request();
        // TODO: validate userName once we have a user service
        request.userName = command.getUserName();
        request.extraActId = command.getExtraActId();
        request.status = defaultStatus;

        requestsRepository.insert(request);

        RequestCreated result = new RequestCreated(request.id.toString());

        return new Result(result);
    }
}

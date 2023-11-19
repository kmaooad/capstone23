package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;

import edu.kmaooad.capstone23.activities.commands.RequestToJoinExtraAct;
import edu.kmaooad.capstone23.activities.dal.Request;
import edu.kmaooad.capstone23.activities.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class CreateRequestToJoinExtraActHandler implements CommandHandler<RequestToJoinExtraAct, RequestCreated> {

    @Inject
    ExtracurricularActivityService extracurricularActivityService;

    @Inject
    RequestsRepository requestsRepository;

    public Result<RequestCreated> handle(RequestToJoinExtraAct command) {

        ExtracurricularActivity act = extracurricularActivityService.findById(command.getExtraActId());
        if (act == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Activity not found");
        }

        List<Request> existingRequests = requestsRepository.findListByUserNameAndExtraActId(command.getUserName(), command.getExtraActId());
        if (existingRequests != null && !existingRequests.isEmpty()) {
            return new Result<>(ErrorCode.EXCEPTION, "User is already part of the activity");
        }

        Request request = new Request();
        request.userName = command.getUserName();
        request.extraActId = command.getExtraActId();
        request.status = "pending";
        requestsRepository.insert(request);

        RequestCreated result = new RequestCreated(request.id.toString());
        return new Result<>(result);
    }
}

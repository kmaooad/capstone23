package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;

import edu.kmaooad.capstone23.activities.commands.RequestToJoinExtraAct;
import edu.kmaooad.capstone23.activities.dal.Request;
import edu.kmaooad.capstone23.activities.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateRequestToJoinExtraActHandler implements CommandHandler<RequestToJoinExtraAct, RequestCreated> {


    @Inject
    RequestsRepository requestsRepository;

    public Result<RequestCreated> handle(RequestToJoinExtraAct command) {

        Request request = new Request();
        request.userName = command.getUserName();
        request.extraActId = command.getExtraActId();
        request.status = "pending";
        requestsRepository.insert(request);

        RequestCreated result = new RequestCreated(request.id.toString());
        return new Result<>(result);
    }
}

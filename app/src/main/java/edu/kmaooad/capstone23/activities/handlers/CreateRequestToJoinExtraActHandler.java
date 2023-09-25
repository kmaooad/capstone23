package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.activities.commands.RequestToJoinExtraAct;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.dal.Request;
import edu.kmaooad.capstone23.activities.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateRequestToJoinExtraActHandler implements CommandHandler<RequestToJoinExtraAct, RequestCreated> {


    @Inject
    RequestsRepository requestsRepository;

    @Inject
    ExtracurricularActivityRepository extraActRepository;

    public Result<RequestCreated> handle(RequestToJoinExtraAct command) {

        ExtracurricularActivity act = extraActRepository.findById(new ObjectId(command.getExtraActId()));
        if (act == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Activity not found");
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

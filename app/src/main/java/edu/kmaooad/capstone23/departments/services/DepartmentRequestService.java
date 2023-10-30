package edu.kmaooad.capstone23.departments.services;

import edu.kmaooad.capstone23.departments.dal.*;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;


public class DepartmentRequestService {
    @Inject
    private RequestsRepository requestsRepository;

    public Request createRequest(String userName, String departmentId) {
        Request request = new Request();
        request.userName = userName;
        request.departmentId = departmentId;
        request.status = RequestStatus.PENDING;
        return requestsRepository.insert(request);
    }

    public Request getRequestById(String id) {
        return requestsRepository.findById(id);
    }

    public void deleteRequest(Request request) {
        requestsRepository.delete(request);
    }

    public void deleteAllRequests() {
        requestsRepository.deleteAll();
    }

    public void deleteRequestById(String id) {
        requestsRepository.deleteById(new ObjectId(id));
    }

    public Request approveRequest(String id) {
        Request request = requestsRepository.findById(id);
        request.status = RequestStatus.ACCEPTED;
        requestsRepository.update(request);
        return request;
    }
}

package edu.kmaooad.capstone23.departments.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApproveJoinRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    private String requestId;
    public String getRequestId() {
        return requestId;
    }
}

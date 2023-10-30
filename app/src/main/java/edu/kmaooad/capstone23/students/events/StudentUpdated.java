package edu.kmaooad.capstone23.students.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.kmaooad.capstone23.common.Result;
import org.bson.types.ObjectId;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentUpdated {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ObjectId studentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isNotified;

    private String notificationError;

    public StudentUpdated(ObjectId studentId, Result<StudentNotified> studentNotifiedResult) {
        this.studentId = studentId;
        this.isNotified = studentNotifiedResult.isSuccess();
        if (!isNotified) this.notificationError = studentNotifiedResult.getMessage();
    }

    public StudentUpdated(ObjectId studentId, boolean isNotified, String notificationError) {
        this.studentId = studentId;
        this.isNotified = isNotified;
        this.notificationError = notificationError;
    }

}

package edu.kmaooad.capstone23.students.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.kmaooad.capstone23.common.Result;
import org.bson.types.ObjectId;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StudentCreated(@JsonInclude(JsonInclude.Include.NON_NULL) ObjectId studentId,
                             @JsonInclude(JsonInclude.Include.NON_NULL) boolean isNotified,
                             String notificationError) {
    public StudentCreated(ObjectId studentId, Result<StudentNotified> studentNotifiedResult) {
        this(studentId, studentNotifiedResult.isSuccess(), !studentNotifiedResult.isSuccess() ? studentNotifiedResult.getMessage() : null);
    }
}
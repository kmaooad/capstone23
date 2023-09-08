package edu.kmaooad.capstone23.members.commands;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class DeleteMember {

    @NotNull
    private ObjectId memberId;

    public ObjectId getMemberId() {
        return memberId;
    }

    public void setMemberId(ObjectId memberId) {
        this.memberId = memberId;
    }

    public DeleteMember(String memberId) {
        this.memberId = new ObjectId(memberId);
    }

    public DeleteMember() {
    }
}

package edu.kmaooad.capstone23.experts.commands;

        import jakarta.validation.constraints.NotBlank;
        import org.bson.types.ObjectId;

public class AssignExpertToMember {
    @NotBlank
    private ObjectId memberId;

    public ObjectId getMemberId() {
        return memberId;
    }

    public void setMemberId(ObjectId memberId) {
        this.memberId = memberId;
    }
}
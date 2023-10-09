package edu.kmaooad.capstone23.mail.commands;

import edu.kmaooad.capstone23.mail.dal.TypeOfDistribution;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MailDistribution {
    @NotBlank
    @Size(min = 8, max = 250)
    private String messageBody;

    private TypeOfDistribution targetGroup;

    public TypeOfDistribution getDistributionTarget() {
        return targetGroup;
    }

    public void setDistributionTarget(TypeOfDistribution targetGroup) {
        this.targetGroup = targetGroup;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}

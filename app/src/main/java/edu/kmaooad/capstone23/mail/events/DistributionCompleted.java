package edu.kmaooad.capstone23.mail.events;

import edu.kmaooad.capstone23.mail.dal.TypeOfDistribution;

public class DistributionCompleted {
    private String bodyMessage;
    private TypeOfDistribution targetGroup;

    public TypeOfDistribution getTargetGroup() {return targetGroup;}
    public String getBodyMessage() {return bodyMessage;}

    public DistributionCompleted(String bodyMessage, TypeOfDistribution targetGroup) {
        this.bodyMessage = bodyMessage;
        this.targetGroup = targetGroup;
    }
}
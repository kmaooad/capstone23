package edu.kmaooad.capstone23.mail.events;

import edu.kmaooad.capstone23.mail.dal.TypeOfDistribution;

public class DistributionCompleted {
    private String targetGroup;

    public String getTargetGroup() {return targetGroup;}

    public DistributionCompleted(String targetGroup) {
        this.targetGroup = targetGroup;
    }
}
package edu.kmaooad.capstone23.experts.events;

import edu.kmaooad.capstone23.experts.dal.Expert;

public class ExpertUpdated {
    private Expert expert;

    public Expert getExpertId() {
        return expert;
    }

    public ExpertUpdated(Expert expert) {
        this.expert = expert;
    }
}

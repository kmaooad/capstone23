package edu.kmaooad.capstone23.experts.events;

import edu.kmaooad.capstone23.experts.dal.Expert;

public class DepartmentAssignedToExpert {
    private Expert expert;

    public Expert getExpert() {
        return expert;
    }

    public DepartmentAssignedToExpert(Expert expert) {
        this.expert = expert;
    }
}

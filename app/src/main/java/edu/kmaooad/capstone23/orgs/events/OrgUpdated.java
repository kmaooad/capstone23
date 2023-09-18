package edu.kmaooad.capstone23.orgs.events;

public class OrgUpdated {
    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public OrgUpdated(boolean success) {
        this.success = success;
    }
}

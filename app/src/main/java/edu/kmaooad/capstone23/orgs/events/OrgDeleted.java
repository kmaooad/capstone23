package edu.kmaooad.capstone23.orgs.events;

public class OrgDeleted {
    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OrgDeleted(boolean success) {
        this.success = success;
    }
}

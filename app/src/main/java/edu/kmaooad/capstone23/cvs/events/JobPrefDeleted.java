package edu.kmaooad.capstone23.cvs.events;

public class JobPrefDeleted {

    private final boolean deleted;

    public JobPrefDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }
}

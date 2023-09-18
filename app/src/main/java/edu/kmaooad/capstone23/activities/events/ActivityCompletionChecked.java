package edu.kmaooad.capstone23.activities.events;

public class ActivityCompletionChecked {
    private final String id;
    private final boolean inProgress;
    private final boolean completed;

    public ActivityCompletionChecked(String id, boolean inProgress, boolean completed) {
        this.id = id;
        this.inProgress = inProgress;
        this.completed = completed;

    }

    public String getId() {
        return id;
    }

    public boolean getInProgress() {
        return inProgress;
    }

    public boolean getCompleted() {
        return completed;
    }
}

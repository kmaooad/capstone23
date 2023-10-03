package edu.kmaooad.capstone23.activities.events;

public class ActivityCompletionChecked {
    private final boolean inProgress;
    private final boolean completed;

    public ActivityCompletionChecked(boolean inProgress, boolean completed) {
        this.inProgress = inProgress;
        this.completed = completed;

    }

    public boolean getInProgress() {
        return inProgress;
    }

    public boolean getCompleted() {
        return completed;
    }
}

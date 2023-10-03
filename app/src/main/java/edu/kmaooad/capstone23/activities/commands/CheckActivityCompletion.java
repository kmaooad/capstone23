package edu.kmaooad.capstone23.activities.commands;

import java.util.Date;

import edu.kmaooad.capstone23.activities.dal.Activity;

public class CheckActivityCompletion {

    private Activity activity;
    private Date actualDate;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }
}

package edu.kmaooad.capstone23.feed_back.events;

import edu.kmaooad.capstone23.feed_back.dal.FeedBack;

public class FeedBackDeleted {
    private FeedBack feedBack;

    public FeedBackDeleted(FeedBack feedBack) {
        this.feedBack = feedBack;
    }

    public FeedBack getFeedBack() {
        return feedBack;
    }
}

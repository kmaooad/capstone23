package edu.kmaooad.capstone23.feed_back.notifications.service;

import edu.kmaooad.capstone23.feed_back.notifications.dal.FeedBackNotificationRepository;
import jakarta.inject.Inject;

public class FeedBackNotificationServiceImpl implements  FeedBackNotificationService {

    @Inject
    FeedBackNotificationRepository feedBackNotificationRepository;

    @Override
    public void notify(String to) {
        var latest = feedBackNotificationRepository.getLatest();
        switch (latest.to) {
            case "mail":
                System.out.println(latest.type + " feed back " + latest.feedBackId + " to mail notification ");
                break;
            case "sms":
                System.out.println(latest.type + " feed back " + latest.feedBackId + " to sms notification ");
                break;
            case "telegram":
                System.out.println(latest.type + " feed back " + latest.feedBackId + " to telegram notification ");
                break;
        }
    };
}

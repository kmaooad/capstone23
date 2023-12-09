package edu.kmaooad.capstone23.feed_back.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.feed_back.commands.CreateFeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.events.FeedBackCreated;
import edu.kmaooad.capstone23.feed_back.services.FeedBackService;
import edu.kmaooad.capstone23.notifications.commands.NotificationTrigger;
import edu.kmaooad.capstone23.notifications.services.NotifyService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Date;


@RequestScoped
public class CreateFeedBackHandler implements CommandHandler<CreateFeedBack, FeedBackCreated> {

    @Inject
    private FeedBackService service;

    @Inject
    private NotifyService notifyService;

    @Override
    public Result<FeedBackCreated> handle(CreateFeedBack command) {

        FeedBack feedBack = new FeedBack();
        feedBack.date = new Date();
        feedBack.text = command.getText();
        feedBack.topic = command.getTopic();

        notifyService.notify(NotificationTrigger.FEEDBACK_CREATED, "Feedback created " + feedBack.toString());

        return new Result<FeedBackCreated>(new FeedBackCreated(service.insert(feedBack).id.toString()));
    }
}

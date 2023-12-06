package edu.kmaooad.capstone23.feed_back.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.feed_back.commands.UpdateFeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.events.FeedBackUpdated;
import edu.kmaooad.capstone23.feed_back.services.FeedBackService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.Optional;


@RequestScoped
public class UpdateFeedBackHandler implements CommandHandler<UpdateFeedBack, FeedBackUpdated> {
    @Inject
    FeedBackService service;

    public Result<FeedBackUpdated> handle(UpdateFeedBack command) {
        var id = command.getFeedBackId();
        Optional<FeedBack> feedBack = service.findById(id);

        if (feedBack.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, String.format("Feedback with id: %s does not exist", id));

        FeedBack feedBackItem = feedBack.get();
        feedBackItem.topic = command.getTopic();
        feedBackItem.text = command.getText();
        feedBackItem.date = new Date();

        service.update(feedBackItem);

        return new Result<>(new FeedBackUpdated(feedBackItem.id.toString(), feedBackItem.topic, feedBackItem.text));
    }
}
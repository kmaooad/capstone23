package edu.kmaooad.capstone23.feed_back.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.feed_back.commands.CreateFeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import edu.kmaooad.capstone23.feed_back.events.FeedBackCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


import java.util.Date;


@RequestScoped
public class CreateFeedBackHandler implements CommandHandler<CreateFeedBack, FeedBackCreated> {

    @Inject
    private FeedBackRepository repository;


    @Override
    public Result<FeedBackCreated> handle(CreateFeedBack command) {

        FeedBack feedBack = new FeedBack();
        feedBack.date = new Date();
        feedBack.text = command.getText();
        feedBack.topic = command.getTopic();

        return new Result<FeedBackCreated>(new FeedBackCreated(repository.insert(feedBack).id.toString()));
    }
}
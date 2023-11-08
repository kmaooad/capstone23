package edu.kmaooad.capstone23.feed_back.services;

import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import jakarta.inject.Inject;

import java.util.Optional;

public class FeedBackServiceImpl implements FeedBackService {
    @Inject
    private FeedBackRepository feedBackRepository;

    @Override
    public Optional<FeedBack> findById(String id) {
        return feedBackRepository.findById(id);
    }

    @Override
    public FeedBack insert(FeedBack feedBack) {
        return feedBackRepository.insert(feedBack);
    }
}

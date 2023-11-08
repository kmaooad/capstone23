package edu.kmaooad.capstone23.feed_back.services;

import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

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

    @Override
    public void delete(FeedBack feedBack) {
        feedBackRepository.delete(feedBack);
    }

    @Override
    public FeedBack findById(ObjectId id) {
        return feedBackRepository.findById(id);
    }

    @Override
    public void update(FeedBack feedBack) {
        feedBackRepository.update(feedBack);
    }

}

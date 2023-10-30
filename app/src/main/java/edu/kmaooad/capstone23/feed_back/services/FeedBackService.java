package edu.kmaooad.capstone23.feed_back.services;

import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface FeedBackService {
    public Optional<FeedBack> findById(String id);

    public FeedBack insert(FeedBack feedBack);

    public void delete (FeedBack feedBack);

    public FeedBack findById(ObjectId id);


}


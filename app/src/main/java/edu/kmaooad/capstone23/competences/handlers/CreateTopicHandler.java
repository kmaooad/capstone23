package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicsRepository;
import edu.kmaooad.capstone23.competences.events.TopicCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateTopicHandler implements CommandHandler<CreateTopic, TopicCreated> {


    @Inject
    private TopicsRepository repository;


    @Override
    public Result<TopicCreated> handle(CreateTopic command) {
        var t = new Topic();
        t.name = command.getTopicName();
        t.parentTopic = command.getParentTopic();
        var result = repository.insert(t);

        return new Result<>(new TopicCreated(result.id));
    }
}

package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicsRepository;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateTopicHandler implements CommandHandler<UpdateTopic, TopicUpdated> {


    @Inject
    private TopicsRepository repository;


    @Override
    public Result<TopicUpdated> handle(UpdateTopic command) {
        Topic topic = new Topic();
        topic.id = command.getId();
        topic.name = command.getTopicName();
        topic.parentTopic = command.getParentTopic();
        return new Result<>(new TopicUpdated(repository.modify(topic)));
    }
}

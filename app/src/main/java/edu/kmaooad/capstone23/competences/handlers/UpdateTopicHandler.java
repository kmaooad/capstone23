package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class UpdateTopicHandler implements CommandHandler<UpdateTopic, TopicUpdated> {
    @Inject
    private TopicRepository repository;

    public Result<TopicUpdated> handle(UpdateTopic command) {

        Optional<Topic> topic = repository.findById(command.getId());
        Topic topicItem = topic.get();
        topicItem.name = command.getTopicName();
        topicItem.parentId = command.getParentId();

        repository.update(topicItem);

        TopicUpdated result = new TopicUpdated(topicItem.id.toString());

        return new Result<>(result);
    }
}

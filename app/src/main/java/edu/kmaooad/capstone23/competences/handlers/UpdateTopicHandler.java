package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
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
    TopicRepository repository;

    public Result<TopicUpdated> handle(UpdateTopic command) {
        Optional<Topic> topic = repository.findById(command.getId());
        if (topic.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Topic with this ID does not exist");

        Topic topicItem = topic.get();
        topicItem.name = command.getTopicName();
        if (command.getParentId() != null) {
            Optional<Topic> parentTopic = repository.findById(command.getParentId());
            if (parentTopic.isPresent())
                topicItem.parentId = command.getParentId();
            else
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Parent Topic with this ID does not exist");
        } else {
            topicItem.parentId = null;
        }

        repository.update(topicItem);

        return new Result<>(new TopicUpdated(topicItem.id.toString(), topicItem.name, topicItem.parentId));
    }
}

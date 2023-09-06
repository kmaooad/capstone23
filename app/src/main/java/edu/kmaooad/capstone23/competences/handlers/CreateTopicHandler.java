package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.competences.events.TopicCreated;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateTopicHandler implements CommandHandler<CreateTopic, TopicCreated> {

    @Inject
    private TopicRepository repository;

    @Override
    public Result<TopicCreated> handle(CreateTopic command) {
        var topic = new Topic();

        var parentTopic = command.parentId;
        if (parentTopic == null) {
            topic.name = command.name;
        }
        else {
            var findParentTopicOptional = repository.findById(parentTopic);
            if (findParentTopicOptional.isEmpty()) {
                return new Result<>(ErrorCode.EXCEPTION, "Parent topic not found");
            }
            else {
                topic.parentId = findParentTopicOptional.get().id.toHexString();
            }
        }

        repository.insert(topic);

        TopicCreated result = new TopicCreated(topic.id.toString());
        return new Result<>(result);
    }
}
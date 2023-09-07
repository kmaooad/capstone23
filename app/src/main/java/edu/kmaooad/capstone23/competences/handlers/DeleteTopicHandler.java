package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicsRepository;
import edu.kmaooad.capstone23.competences.events.TopicDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteTopicHandler implements CommandHandler<DeleteTopic, TopicDeleted> {


    @Inject
    private TopicsRepository repository;


    @Override
    public Result<TopicDeleted> handle(DeleteTopic command) {
        Topic topic = repository.findById(command.getId());

        System.out.println(topic);


        repository.delete(topic);

        return new Result<>(new TopicDeleted(topic));
    }
}

package edu.kmaooad.capstone23.relations.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.relations.commands.SetRelation;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.relations.events.RelationSet;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class SetRelationHandler implements CommandHandler<SetRelation, RelationSet> {
    @Inject
    RelationRepository repository;

    @Override
    public Result<RelationSet> handle(SetRelation command) {
        var firstObjectId = command.objectToConnectId1;
        var secomdObjectId = command.objectToConnectId2;
        var relation = new Relation(firstObjectId, secomdObjectId);

        var collectionName1 = command.collectionName1;
        var collectionName2 = command.collectionName2;
        var resultOfCreation = repository.createRelation(collectionName1, collectionName2, relation);

        return resultOfCreation
                .map(createdRelation -> new RelationSet(createdRelation.getId()))
                .map(Result::new)
                .orElse(new Result<>(ErrorCode.EXCEPTION, "Unable to create a relation."));
    }
}

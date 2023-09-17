package edu.kmaooad.capstone23.relations.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.relations.commands.UnsetRelation;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.relations.events.RelationUnset;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnsetRelationHandler implements CommandHandler<UnsetRelation, RelationUnset> {
    @Inject
    RelationRepository repository;

    @Override
    public Result<RelationUnset> handle(UnsetRelation command) {
        var relationToUnsetId = command.getId();
        var firstCollectionName = command.getFirstCollectionName();
        var secondCollectionName = command.getSecondCollectionName();

        return repository.deleteRelation(firstCollectionName, secondCollectionName, relationToUnsetId)
                .map(Relation::getId)
                .map(RelationUnset::new)
                .map(Result::new)
                .orElse(new Result<>(ErrorCode.EXCEPTION, "Unable to create a non-existent relation."));
    }
}

package edu.kmaooad.capstone23.search;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.relations.dal.QueryableRelationRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class QueryEssenceHandler <
        EntityToFind,
        EntityToFindBy,
        EntityToFindListable extends PanacheMongoRepository<EntityToFind>,
        EntityToFindByListable extends PanacheMongoRepository<EntityToFindBy>,
        QueryEvent
        > implements CommandHandler<QueryByIdCommand, QueryEvent> {
    @Inject
    QueryableRelationRepository relationRepository;

    @Inject
    EntityToFindListable essenceToFindListable;

    @Inject
    EntityToFindByListable essenceToFindByListable;

    private final Function<List<EntityToFind>, QueryEvent> constructQueryEvent;
    private final Function<EntityToFind, ObjectId> getIdOfEssenceToFind;
    private final Function<EntityToFindBy, ObjectId> getIdOfEssenceToFindBy;
    private final Map<ObjectId, EntityToFind> idsOfEssencesToFind;
    private final Set<ObjectId> idsOfEssencesToFindBy;

    public QueryEssenceHandler
    (
            Function<EntityToFind, ObjectId> getIdOfEssenceToFind,
            Function<EntityToFindBy, ObjectId> getIdOfEssenceToFindBy,
            Function<List<EntityToFind>, QueryEvent> constructQueryEvent
    )
    {
        this.constructQueryEvent = constructQueryEvent;
        this.getIdOfEssenceToFind = getIdOfEssenceToFind;
        this.getIdOfEssenceToFindBy = getIdOfEssenceToFindBy;
        this.idsOfEssencesToFindBy = new HashSet<>();
        this.idsOfEssencesToFind = new HashMap<>();
    }

    private void init() {
        idsOfEssencesToFind.clear();
        idsOfEssencesToFindBy.clear();

        essenceToFindByListable.listAll()
                .stream()
                .map(getIdOfEssenceToFindBy)
                .forEach(idsOfEssencesToFindBy::add);

        essenceToFindListable.listAll()
                .forEach(course -> idsOfEssencesToFind.put(getIdOfEssenceToFind.apply(course), course));
    }

    @Override
    public Result<QueryEvent> handle(QueryByIdCommand command) {
        init();
        return resolve(command);
    }

    private Result<QueryEvent> resolve(QueryByIdCommand command) {
        var wantedGroupId = command.id;
        Predicate<ObjectId> isGroupId = idsOfEssencesToFindBy::contains;
        Predicate<ObjectId> isCourseId = idsOfEssencesToFind::containsKey;
        Predicate<Relation> isWantedRelation =
                relation -> (relation.firstObjectId.equals(wantedGroupId) && isCourseId.test(relation.secondObjectId)) ||
                        (relation.secondObjectId.equals(wantedGroupId) && isCourseId.test(relation.firstObjectId));

        if(!isGroupId.test(wantedGroupId))
            return new Result<>(ErrorCode.EXCEPTION, "The provided id: " + wantedGroupId +
                    " does not represent an id of essence to query by.");

        var relations = relationRepository.readAllRelations();
        var wantedCourses = relations.stream()
                .filter(isWantedRelation)
                .map(relation -> isCourseId.test(relation.firstObjectId)
                        ? relation.firstObjectId
                        : relation.secondObjectId)
                .map(idsOfEssencesToFind::get)
                .toList();

        var event = constructQueryEvent.apply(wantedCourses);
        return new Result<>(event);
    }
}

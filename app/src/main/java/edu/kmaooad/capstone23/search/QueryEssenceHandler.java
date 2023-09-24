package edu.kmaooad.capstone23.search;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.relations.dal.QueryableRelationRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class QueryEssenceHandler <
        EssenceToFind,
        EssenceToFindBy,
        EssenceToFindRepository extends PanacheMongoRepository<EssenceToFind>,
        EssenceToFindByRepository extends PanacheMongoRepository<EssenceToFindBy>,
        QueryEvent
        > implements CommandHandler<QueryByIdCommand, QueryEvent> {
    private final Function<List<EssenceToFind>, QueryEvent> constructQueryEvent;
    private final QueryableRelationRepository relationRepository;
    private final Map<ObjectId, EssenceToFind> idsOfEssencesToFind;
    private final Set<ObjectId> idsOfEssencesToFindBy;

    public QueryEssenceHandler(Function<EssenceToFind, ObjectId> getIdOfEssenceToFind,
                               Function<EssenceToFindBy, ObjectId> getIdOfEssenceToFindBy,
                               Function<List<EssenceToFind>, QueryEvent> constructQueryEvent,
                               QueryableRelationRepository relationRepository,
                               EssenceToFindRepository courseRepository,
                               EssenceToFindByRepository groupRepository) {
        this.constructQueryEvent = constructQueryEvent;
        this.relationRepository = relationRepository;
        this.idsOfEssencesToFindBy = new HashSet<>();
        this.idsOfEssencesToFind = new HashMap<>();

        groupRepository.listAll()
                .stream()
                .map(getIdOfEssenceToFindBy)
                .forEach(idsOfEssencesToFindBy::add);

        courseRepository.listAll()
                .forEach(course -> idsOfEssencesToFind.put(getIdOfEssenceToFind.apply(course), course));
    }

    @Override
    public Result<QueryEvent> handle(QueryByIdCommand command) {
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

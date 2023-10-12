package edu.kmaooad.capstone23.search.extracurricularActivities.by.group.handlers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.extracurricularActivities.by.group.events.ExtracurricularActivityQueriedByGroup;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryExtracurricularActivitiesByGroupHandler extends QueryEssenceHandler<
        ExtracurricularActivity,
        Group,
        ExtracurricularActivityRepository,
        GroupsRepository,
        ExtracurricularActivityQueriedByGroup
        >
{
    public QueryExtracurricularActivitiesByGroupHandler() {
        super (
                extracurricularActivity -> extracurricularActivity.id,
                group -> group.id,
                ExtracurricularActivityQueriedByGroup::new
        );
    }
}
package edu.kmaooad.capstone23.search.extracurricularActivities.by.org.handlers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.extracurricularActivities.by.org.events.ExtracurricularActivityQueriedByOrg;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryExtracurricularActivitiesByOrgHandler extends QueryEssenceHandler<
        ExtracurricularActivity,
        Org,
        ExtracurricularActivityRepository,
        OrgsRepository,
        ExtracurricularActivityQueriedByOrg
        >
{
    public QueryExtracurricularActivitiesByOrgHandler() {
        super (
                extracurricularActivity -> extracurricularActivity.id,
                org -> org.id,
                ExtracurricularActivityQueriedByOrg::new
        );
    }
}
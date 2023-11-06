package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class AccessRuleServiceImpl implements AccessRuleService {

    @Inject
    private AccessRuleRepository repository;
    @Override
    public AccessRule insert(AccessRule accessRule) {
        return repository.insert(accessRule);
    }

    @Override
    public long delete(String query, Object... params) {
        return repository.delete(query,params);
    }

    @Override
    public void update(AccessRule accessRule) {
        repository.update(accessRule);
    }

    @Override
    public Optional<AccessRule> findByIdOptional(String id) {
        return repository.findByIdOptional(new ObjectId(id));
    }
}

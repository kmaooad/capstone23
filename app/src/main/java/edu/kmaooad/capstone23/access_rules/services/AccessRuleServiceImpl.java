package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@RequestScoped
public class AccessRuleServiceImpl implements AccessRuleService {

  @Inject
  private AccessRuleRepository accessRuleRepository;


  @Override
  public AccessRule insert(AccessRule accessRule) {
    return this.accessRuleRepository.insert(accessRule);
  }

  @Override
  public void deleteRule(ObjectId id) {
    this.accessRuleRepository.deleteRule(id);
  }

  @Override
  public List<AccessRule> findByEntityIdAndType(ObjectId entityId, AccessRuleFromEntityType entityType) {
    return this.accessRuleRepository.findByEntityIdAndType(entityId, entityType);
  }

  @Override
  public void ban(ObjectId entityId, AccessRuleFromEntityType entityType) {
    this.accessRuleRepository.updateOnBan(entityId, entityType);
  }

  @Override
  public boolean existsById(ObjectId id) {
    return this.accessRuleRepository.findByIdOptional(id).isPresent();
  }
}

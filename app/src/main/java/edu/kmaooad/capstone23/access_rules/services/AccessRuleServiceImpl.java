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
  public void deleteRule(String id) {
    this.accessRuleRepository.deleteRule(new ObjectId(id));
  }

  @Override
  public List<AccessRule> findByEntityIdAndType(String entityId, AccessRuleFromEntityType entityType) {
    return this.accessRuleRepository.findByEntityIdAndType(new ObjectId(entityId), entityType);
  }

  @Override
  public void ban(ObjectId entityId, AccessRuleFromEntityType entityType) {
    this.accessRuleRepository.updateOnBan(entityId, entityType);
  }
  
  @Override
  public void updateRule(AccessRule accessRule) {
    this.accessRuleRepository.update(accessRule);
  }

  @Override
  public AccessRule findRuleById(String id) {
    return this.accessRuleRepository.findById(new ObjectId(id));
  }

  @Override
  public boolean existsById(String id) {
    return this.accessRuleRepository.findByIdOptional(new ObjectId(id)).isPresent();
  }
}

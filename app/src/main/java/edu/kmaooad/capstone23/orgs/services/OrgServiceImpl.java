package edu.kmaooad.capstone23.orgs.services.impl;

import edu.kmaooad.capstone23.orgs.dal.Job;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.services.OrgService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrgServiceImpl extends OrgService {
  @Inject
  private OrgsRepository orgsRepository;

  public Org createOrg(String name, String description, String industry, String website, String emailDomain) {
    Org org = new Org();
    org.name = name;
    org.description = description;
    org.jobs = new ArrayList<>();
    org.industry = industry;
    org.website = website;
    org.emailDomain = emailDomain;
    org.isActive=true;
    return orgsRepository.insert(org);
  }

  public Org getOrgByName(String name) {
    return orgsRepository.findByName(name);
  }

  public Org getOrgById(String id) {
    return orgsRepository.findById(id);
  }

  public void deleteOrg(Org department) {
    orgsRepository.delete(department);
  }

  public void deleteOrgById(String id) {
    orgsRepository.deleteById(new ObjectId(id));
  }

  public void updateOrg(Org department) {
    orgsRepository.update(department);
  }


  public void deleteAllOrgs() {
    orgsRepository.deleteAll();
  }

  @Override
  public List<Job> findJobsByOrgId(String id) {
    return null;
  }

  @Override
  public void updateJob(Job job) {

  }
}

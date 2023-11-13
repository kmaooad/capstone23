package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrgServiceImpl implements OrgService {
  @Inject
  private OrgsRepository orgsRepository;

  @Override
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

  @Override
  public Org getOrgByName(String name) {
    return orgsRepository.findByName(name);
  }

  @Override
  public Org getOrgById(String id) {
    return orgsRepository.findById(id);
  }

  @Override
  public Optional<Org> getOrgByIdOptional(String id) {
    return orgsRepository.findByIdOptional(new ObjectId(id));
  }

  @Override
  public void deleteOrg(Org department) {
    orgsRepository.delete(department);
  }

  @Override
  public void deleteOrgById(String id) {
    orgsRepository.deleteById(new ObjectId(id));
  }

  @Override
  public void updateOrg(Org department) {
    orgsRepository.update(department);
  }

  @Override
  public void deleteAllOrgs() {
    orgsRepository.deleteAll();
  }

  @Override
  public List<Org> listAll() {
    return this.orgsRepository.listAll();
  }
}

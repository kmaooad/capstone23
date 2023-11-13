package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrgsServiceImpl implements OrgsService {

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    JobsRepository jobRepository;

    @Inject
    RequestsRepository requestsRepository;

    @Override
    public Org insert(Org org) {
        return orgsRepository.insert(org);
    }

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
    public List<Org> bulkInsert(List<Org> orgs) {
        return orgsRepository.bulkInsert(orgs);
    }
    
    @Override
    public Org findByName(String name){
        return orgsRepository.findByName(name);
    }

    @Override
    public Org findById(ObjectId objectId) {
        return orgsRepository.findById(objectId);
    }

    @Override
    public Org findById(String objectId) {
        return orgsRepository.findById(objectId);
    }

    @Override
    public Org getByPos(int position) {
        return orgsRepository.listAll().get(position);
    }
    
    @Override
    public Optional<Org> findByEmailDomainOptional(String email){
        return orgsRepository.findByEmailDomainOptional(email);
    }

    @Override
    public Optional<Org> findByIdOptional(String id){
        return orgsRepository.findByIdOptional(id);
    }

    @Override
    public boolean deleteById(ObjectId id) {
        return orgsRepository.deleteById(id);
    }

    @Override
    public boolean isNotNull(){
        return orgsRepository != null;
    }

    @Override
    public long count(){
        return orgsRepository.count();
    }

    @Override
    public void update(Org org){
        orgsRepository.update(org);
    }

//Request
    @Override
    public Request insertRequest(Request request){
        return requestsRepository.insert(request);
    }

//Jobs
    @Override
    public List<Job> findJobsByOrgId(String id) {
        return jobRepository.findByOrgId(id);
    }

    @Override
    public void updateJob(Job job){
        jobRepository.update(job);
    }
}

package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrgsServiceImpl implements OrgsService {

    @Inject
    OrgsRepository repository;

    @Override
    public Org insert(Org org) {
        return repository.insert(org);
    }

    @Override
    public List<Org> bulkInsert(List<Org> orgs) {
        return repository.bulkInsert(orgs);
    }
    
    @Override
    public Org findByName(String name){
        return repository.findByName(name);
    }

    @Override
    public Org findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Org getByPos(int position) {
        return repository.listAll().get(position);
    }
    
    @Override
    public Optional<Org> findByEmailDomainOptional(String email){
        return repository.findByEmailDomainOptional(email);
    }

    @Override
    public Optional<Org> findByIdOptional(String id){
        return repository.findByIdOptional(id);
    }

    @Override
    public boolean deleteById(ObjectId id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean isNotNull(){
        return repository != null;
    }

    @Override
    public long count(){
        return repository.count();
    }
}

package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.search.Listable;

import java.util.Optional;

public interface OrgService extends Listable<Org> {
    Org createOrg(String name, String description, String industry, String website, String emailDomain);

    Org getOrgByName(String name);

    Org getOrgById(String id);

    Optional<Org> getOrgByIdOptional(String id);

    void deleteOrg(Org department);

    void deleteOrgById(String id);

    void updateOrg(Org department);

    void deleteAllOrgs();
}
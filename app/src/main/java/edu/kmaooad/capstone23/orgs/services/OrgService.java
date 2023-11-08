package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.search.Listable;

public interface OrgService extends Listable<Org> /*please implement this*/
{
     Org createOrg(String name, String description, String industry, String website, String emailDomain);

    public Org getOrgByName(String name);

    public Org getOrgById(String id);

    public void deleteOrg(Org department);
    public void deleteOrgById(String id);

    public void updateOrg(Org department);

    public void deleteAllOrgs();
}
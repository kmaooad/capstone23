package edu.kmaooad.capstone23.orgs.drivers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.services.OrgService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrgDriver {

    @Inject
    OrgService orgService;

    public Org createOrg() {
        orgService.deleteAllOrgs();
        return orgService.createOrg("Org to Delete", "This is a org to be deleted", "NaUKMA","www.naukma.com","naukma@ukma.edu.ua");
    }
}

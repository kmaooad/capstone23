package edu.kmaooad.capstone23.orgs;

import java.util.function.Function;

import edu.kmaooad.capstone23.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

@RequestScoped
public class CreateOrgHandler implements Function<CreateOrg, Result<OrgCreated>> {

    @Inject
    private Validator validator;

    public Result<OrgCreated> apply(CreateOrg upd) {
        OrgCreated result = new OrgCreated();
        return new Result<OrgCreated>(result);
    }
}
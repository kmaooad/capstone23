package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.departments.dal.*;
import edu.kmaooad.capstone23.departments.events.HiringStatusSettedOn;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class SetHiringStatusOnHandler implements CommandHandler<SetHiringStatusOn, HiringStatusSettedOn> {
    @Inject
    private DepartmentsRepository departmentsRepository;

    private final String hiringStatusOn = "We are hiring";


    public Result<HiringStatusSettedOn> handle(SetHiringStatusOn command) {
        String departmentId = command.getDepartmentId();

        Department department = departmentsRepository.findById(departmentId);

        if (department == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Department with such Id doesn't exist");
        }

        department.hiringStatus = hiringStatusOn;

        departmentsRepository.update(department);

        HiringStatusSettedOn result = new HiringStatusSettedOn(department.id.toString());

        return new Result(result);
    }
}

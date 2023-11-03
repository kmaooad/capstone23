package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOff;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.HiringStatusSettedOff;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;


@RequestScoped
public class SetHiringStatusOffHandler implements CommandHandler<SetHiringStatusOff, HiringStatusSettedOff> {
    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    private JobRepository jobRepository;

    @Inject
    private EntityBanService banService;

    private final String hiringStatusOff = "Off";


    public Result<HiringStatusSettedOff> handle(SetHiringStatusOff command) {
        String departmentId = command.getDepartmentId();

        Department department = departmentsRepository.findById(departmentId);

        if (department == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Department with such Id doesn't exist");
        }

        var ban = banService.findForEntity(IsEntityBannedV2.DEPARTMENT_BAN_ENTITY_TYPE, department.id.toString());
        if (ban.isPresent()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Department is banned");
        }

        department.hiringStatus = hiringStatusOff;

        if (department.jobs != null) {
            department.jobs.forEach(jobId -> {
                Job job = jobRepository.findById(new ObjectId(jobId));
                if (job == null) {
                    return;
                }
                job.active = false;
                jobRepository.update(job);
            });
        }


        departmentsRepository.update(department);

        HiringStatusSettedOff result = new HiringStatusSettedOff(department.id.toString());

        return new Result<>(result);
    }
}

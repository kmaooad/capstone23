package edu.kmaooad.capstone23.departments.handlers;

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

import java.util.Optional;


@RequestScoped
public class SetHiringStatusOffHandler implements CommandHandler<SetHiringStatusOff, HiringStatusSettedOff> {
    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    private JobRepository jobRepository;


    private final String hiringStatusOff = "Off";


    public Result<HiringStatusSettedOff> handle(SetHiringStatusOff command) {
        String departmentId = command.getDepartmentId();

        Department department = departmentsRepository.findById(departmentId);

        if (department == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Department with such Id doesn't exist");
        }

        department.hiringStatus = hiringStatusOff;

        department.jobs.forEach(jobId -> {
            Optional<Job> job = jobRepository.findByIdOptional(new ObjectId(jobId));
            Job j = job.get();
            j.active = false;
            jobRepository.update(j);
        });

        departmentsRepository.update(department);

        HiringStatusSettedOff result = new HiringStatusSettedOff(department.id.toString());

        return new Result(result);
    }
}

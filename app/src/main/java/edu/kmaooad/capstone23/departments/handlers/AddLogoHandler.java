package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.AddLogo;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.dal.Logo;
import edu.kmaooad.capstone23.departments.events.LogoAdded;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.File;


@RequestScoped
public class AddLogoHandler implements CommandHandler<AddLogo, LogoAdded> {
    @Inject
    private DepartmentsRepository departmentsRepository;

    public Result<LogoAdded> handle(AddLogo command) {

        String departmentId = command.getDepartmentId();

        Department department = departmentsRepository.findById(departmentId);

        if (department == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Department with such Id doesn't exist");
        }


        String fileName = command.getLogoName();
        File logoFile = command.getLogo();

        if (logoFile == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Logo file is required");
        }

        if (fileName == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Logo file name is required");
        }

        Logo logo = new Logo();
        logo.fileName = fileName;


        byte[] fileContent;
        try {
            fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(logoFile);
        } catch (Exception e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Error while reading logo file");
        }

        logo.file = fileContent;

        department.logo = logo;

        departmentsRepository.update(department);

        LogoAdded result = new LogoAdded(departmentId);

        return new Result(result);
    }
}

package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.services.CVService;
import edu.kmaooad.capstone23.massages.commands.CreateMessage;
import edu.kmaooad.capstone23.massages.dal.Message;
import edu.kmaooad.capstone23.massages.dal.MessagesRepository;
import edu.kmaooad.capstone23.massages.services.MessageService;
import edu.kmaooad.capstone23.massages.services.MessageServiceImplementation;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@RequestScoped
public class CreateCVHandler implements CommandHandler<CreateCV, CVCreated> {

    @Inject
    CVService cvService;
    MessageService messageService;
    @Inject
    StudentRepository studentRepository;
    MessagesRepository messagesRepository;

    @Override
    public Result<CVCreated> handle(CreateCV command) {
        CV cv = new CV();

        if (command.getDateTimeCreated() == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Creation time is not set");
        } else if (command.getDateTimeCreated().isAfter(LocalDateTime.now())) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid time of creation");
        } else if (command.getTextInfo() != null && command.getTextInfo().isBlank()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Text info cannot be blank");
        }


        if (command.getStatus() == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Status cannot be null");
        }

        if (command.getVisibility() == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Visibility cannot be null");
        }

        if (command.getStudentId() != null) {
            if (!ObjectId.isValid(command.getStudentId()))
                return new Result<>(ErrorCode.VALIDATION_FAILED, "invalid student id");
            Student st = studentRepository.findById(new ObjectId(command.getStudentId()));
            if (st == null)
                return new Result<>(ErrorCode.NOT_FOUND, "student with id " + command.getStudentId() + " not found");


        }
        Message m = new Message();
        m.method_of_sending = "sms";
        m.event_type = "cvCreated";
        messageService.insert(m);
        messageService.send(m);


        cv.dateTimeCreated = command.getDateTimeCreated();
        cv.textInfo = command.getTextInfo();
        cv.status = command.getStatus();
        cv.visibility = command.getVisibility();
        cv.autoAddCompetences = command.isAutoAddCompetences();
        cv.studentId = command.getStudentId();

        cvService.create(cv);

        CVCreated result = new CVCreated(cv.id);
        return new Result<>(result);
    }

}

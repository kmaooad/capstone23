package edu.kmaooad.capstone23.students.parser;

import edu.kmaooad.capstone23.students.parser.exceptions.IncorrectValuesAmount;
import edu.kmaooad.capstone23.students.parser.exceptions.NotEnoughValues;
import edu.kmaooad.capstone23.students.parser.exceptions.TooManyValues;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@ApplicationScoped
public class UpdateCSVStudentParser {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    public UpdateCSVStudentParser() {
        FORMATTER.setTimeZone(TimeZone.getTimeZone("GMT"));
        FORMATTER.setLenient(false);
    }

    private static final int STUDENT_FIELDS = 6;

    public List<UpdateCSVStudent> parse(File content) throws ParseException, FileNotFoundException, IOException, IncorrectValuesAmount {
        FileInputStream stream = new FileInputStream(content);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader text = new BufferedReader(reader);

        List<UpdateCSVStudent> result = new ArrayList<>();

        int line = 0;

        while (text.ready()) {
            ++line;
            String input = text.readLine();
            if (input.isBlank()) continue;
            var values = input.split(",");
            if (values.length < STUDENT_FIELDS) throw new NotEnoughValues(line);
            if (values.length > STUDENT_FIELDS) throw new TooManyValues(line);
            result.add(parse(values));
        }

        stream.close();
        reader.close();
        text.close();

        return result;
    }

    private UpdateCSVStudent parse(String[] values) throws ParseException {
        var student = new UpdateCSVStudent();
        for (int i = 0; i < values.length; i++) {
            if (!values[i].isBlank()) {
                build(student, i, values[i]);
            }
        }
        return student;
    }

    private void build(UpdateCSVStudent student, int index, String field) throws ParseException {
        try {
            if (index == 0) {
                student.setId(new ObjectId(field));
            }
            if (index == 1) {
                student.setFirstName(field);
            }
            if (index == 2) {
                student.setMiddleName(field);
            }
            if (index == 3) {
                student.setLastName(field);
            }
            if (index == 4) {
                student.setDOBTimestamp(FORMATTER.parse(field).getTime());
            }
            if (index == 5) {
                student.setEmail(field);
            }
        } catch (ParseException ex) {
            throw new ParseException("Incorrect data format for field", index);
        }
//        return student;
    }
}

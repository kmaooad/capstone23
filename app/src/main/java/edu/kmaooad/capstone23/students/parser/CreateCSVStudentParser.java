package edu.kmaooad.capstone23.students.parser;

import edu.kmaooad.capstone23.students.parser.exceptions.IncorrectValuesAmount;
import edu.kmaooad.capstone23.students.parser.exceptions.InvalidEmail;
import edu.kmaooad.capstone23.students.parser.exceptions.NotEnoughValues;
import edu.kmaooad.capstone23.students.parser.exceptions.TooManyValues;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@ApplicationScoped
public class CreateCSVStudentParser {
    @Inject
    Validator validator;

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    public CreateCSVStudentParser() {
        FORMATTER.setTimeZone(TimeZone.getTimeZone("GMT"));
        FORMATTER.setLenient(false);
    }

    private static final int STUDENT_FIELDS = 5;
    public List<CSVStudent> parse(File content) throws ParseException, FileNotFoundException, IOException, IncorrectValuesAmount, InvalidEmail {
        try (FileInputStream stream = new FileInputStream(content)) {
        try (InputStreamReader reader = new InputStreamReader(stream)) {
        try (BufferedReader text = new BufferedReader(reader)) {

            List<CSVStudent> result = new ArrayList<>();

            int line = 0;

            while (text.ready()) {
                ++line;
                String input = text.readLine();
                if (input.isBlank()) continue;
                var values = input.split(",");
                if (values.length < STUDENT_FIELDS) throw new NotEnoughValues(line);
                if (values.length > STUDENT_FIELDS) throw new TooManyValues(line);
                CSVStudent createdStudent = parse(values);
                if (!validator.validate(createdStudent).isEmpty())
                    throw new InvalidEmail("Invalid email provided: ", createdStudent.email());
                result.add(parse(values));
            }
            return result;
        }}}
    }

    private CSVStudent parse(String[] values) throws ParseException {
        return new CSVStudent(values[0], values[1], values[2], FORMATTER.parse(values[3]).getTime(), values[4]);
    }
}

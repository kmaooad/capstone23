package edu.kmaooad.capstone23.students.parser;

import edu.kmaooad.capstone23.students.parser.exceptions.IncorrectValuesAmount;
import edu.kmaooad.capstone23.students.parser.exceptions.NotEnoughValues;
import edu.kmaooad.capstone23.students.parser.exceptions.TooManyValues;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@ApplicationScoped
public class CSVStudentParser {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    public CSVStudentParser() {
        FORMATTER.setTimeZone(TimeZone.getTimeZone("GMT"));
        FORMATTER.setLenient(false);
    }

    private static final int STUDENT_FIELDS = 5;
    public List<CSVStudent> parse(File content) throws ParseException, FileNotFoundException, IOException, IncorrectValuesAmount {
        FileInputStream stream = new FileInputStream(content);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader text = new BufferedReader(reader);

        List<CSVStudent> result = new ArrayList<>();

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

    private CSVStudent parse(String[] values) throws ParseException {
        return new CSVStudent(values[0], values[1], values[2], FORMATTER.parse(values[3]).getTime(), values[4]);
    }
}

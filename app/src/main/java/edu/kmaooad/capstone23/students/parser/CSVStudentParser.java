package edu.kmaooad.capstone23.students.parser;

import edu.kmaooad.capstone23.students.parser.exceptions.IncorrectValuesAmount;
import edu.kmaooad.capstone23.students.parser.exceptions.NotEnoughValues;
import edu.kmaooad.capstone23.students.parser.exceptions.TooManyValues;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CSVStudentParser {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    private static final int STUDENT_FIELDS = 5;
    public List<CSVStudent> parse(File content) throws ParseException, FileNotFoundException, IOException, IncorrectValuesAmount {
        FileInputStream stream = new FileInputStream(content);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader text = new BufferedReader(reader);

        List<CSVStudent> result = new ArrayList<>();

        int line = 0;

        while (text.ready()){
            ++line;
            var values = text.readLine().split(",");
            if (values.length < STUDENT_FIELDS) throw new NotEnoughValues(line);
            if (values.length > STUDENT_FIELDS) throw new TooManyValues(line);
            result.add(parse(values));
        }
        return result;
    }

    private CSVStudent parse(String[] values) throws ParseException {
        return new CSVStudent(values[0], values[1], values[2], FORMATTER.parse(values[3]).getTime(), values[4]);
    }
}

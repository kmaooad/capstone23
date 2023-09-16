package edu.kmaooad.capstone23.students.parser;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CSVStudentParser {
    public List<CSVStudent> parse(File content) throws ParseException, FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(content);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader text = new BufferedReader(reader);

        List<CSVStudent> result = new ArrayList<>();

        while (text.ready()){
            var values = text.readLine().split(",");
            result.add(parse(values));
        }
        return result;
    }

    private CSVStudent parse(String[] values) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new CSVStudent(values[0], values[1], values[2], formatter.parse(values[3]).getTime(), values[4]);
    }
}

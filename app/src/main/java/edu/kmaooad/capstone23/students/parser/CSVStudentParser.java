package edu.kmaooad.capstone23.students.parser;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CSVStudentParser {
    public List<CSVStudent> parse(File content){
        return new ArrayList<>();
    }
}

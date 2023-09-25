package edu.kmaooad.capstone23.students.parser.exceptions;

public class TooManyValues extends IncorrectValuesAmount{
    public TooManyValues(int line) {
        super("Too many values on line ", line);
    }
}

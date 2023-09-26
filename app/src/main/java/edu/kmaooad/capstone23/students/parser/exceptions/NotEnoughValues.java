package edu.kmaooad.capstone23.students.parser.exceptions;

public class NotEnoughValues extends IncorrectValuesAmount{
    public NotEnoughValues(int line) {
        super("Not enough values on line ", line);
    }
}

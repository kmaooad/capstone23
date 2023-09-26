package edu.kmaooad.capstone23.students.parser.exceptions;

public class IncorrectValuesAmount extends Exception{
    IncorrectValuesAmount(String message, int line){
        super(message + line);
    }
}

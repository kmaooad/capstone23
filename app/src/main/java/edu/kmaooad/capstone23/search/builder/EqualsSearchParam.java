package edu.kmaooad.capstone23.search.builder;

public class EqualsSearchParam extends SearchParam {
    private static final String FORMAT = "'%1$s': :%1$s";


    public EqualsSearchParam(String paramName, String paramValue) {
        super(paramName, paramValue);
        query = String.format(FORMAT, paramName);
    }
}

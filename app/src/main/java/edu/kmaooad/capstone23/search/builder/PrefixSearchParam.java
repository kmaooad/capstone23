package edu.kmaooad.capstone23.search.builder;

public class PrefixSearchParam extends SearchParam {
    private static final String FORMAT = "'%s': /^%s/";

    public PrefixSearchParam(String paramName, String paramValue) {
        super(paramValue);
        query = String.format(FORMAT, paramName, paramValue);
    }
}

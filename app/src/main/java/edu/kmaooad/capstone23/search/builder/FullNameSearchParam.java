package edu.kmaooad.capstone23.search.builder;

public class FullNameSearchParam extends SearchParam {
    private static final String FORMAT = """
    $or: [
        { "firstName": /^%1$s/ },
        { "middleName": /^%1$s/ },
        { "lastName": /^%1$s/ },
    ]""";


    public FullNameSearchParam(String paramValue) {
        super(paramValue);
        query = String.format(FORMAT, paramValue);
    }
}

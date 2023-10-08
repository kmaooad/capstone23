package edu.kmaooad.capstone23.search.builder;

public abstract class SearchParam {
    protected String paramName;
    protected Object paramValue;

    protected SearchParam(String paramName, Object paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public Object getParamValue() {
        return paramValue;
    }

    public abstract String getQuery();
}

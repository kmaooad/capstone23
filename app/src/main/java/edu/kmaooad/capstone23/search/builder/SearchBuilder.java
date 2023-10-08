package edu.kmaooad.capstone23.search.builder;

import io.quarkus.panache.common.Parameters;
import org.antlr.v4.runtime.misc.Pair;

public class SearchBuilder {
    private StringBuilder query;
    private Parameters parameters;

    public SearchBuilder() {
        query = new StringBuilder();
        parameters = new Parameters();
    }

    public SearchBuilder and(SearchParam param) {
        if (!query.isEmpty()) query.append(", ");
        query.append(param.getQuery());

        parameters
                .and(param.getParamName(), param.getParamValue());
        return this;
    }

    public Pair<String, Parameters> build() {
        return new Pair<>('{' + query.toString() + '}', parameters);
    }
}

package com.epam.esm.specification;

import java.util.List;
import java.util.Optional;

/**
 * class which contains query to db and params which are optional
 * transferred to repository
 */
public class Specification {

    private String query;
    private Optional<List<Object>> params = Optional.empty();

    public Specification() {

    }

    public Specification(String query) {
        this.query = query;
    }

    public Specification(String query, Optional<List<Object>> params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Optional<List<Object>> getParams() {
        return params;
    }

    public void setParams(Optional<List<Object>> params) {
        this.params = params;
    }
}

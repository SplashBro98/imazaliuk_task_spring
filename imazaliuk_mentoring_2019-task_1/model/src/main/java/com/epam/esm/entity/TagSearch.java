package com.epam.esm.entity;

public class TagSearch {

    private String name;
    private String field;

    public TagSearch() {
    }

    public TagSearch(String name, String field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

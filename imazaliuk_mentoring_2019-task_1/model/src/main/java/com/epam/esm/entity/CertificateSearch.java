package com.epam.esm.entity;

import java.time.LocalDate;

public class CertificateSearch {

    private String name;
    private String description;
    private LocalDate date;
    private String tagName;
    private String field = "certificate_id";

    public CertificateSearch() {

    }

    public CertificateSearch(String name, String description, LocalDate date, String tagName, String field) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.tagName = tagName;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

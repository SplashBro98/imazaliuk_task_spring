package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Certificate {

    private Long certificateId;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate dateOfCreation;
    private LocalDate dateOfModification;
    private int duration;
    private List<Tag> tags;

    public Certificate() {

    }

    public Certificate(String name, String description, BigDecimal price,
                       LocalDate dateOfCreation, LocalDate dateOfModification, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dateOfCreation = dateOfCreation;
        this.dateOfModification = dateOfModification;
        this.duration = duration;
    }

    public Certificate(Long certificateId, String name, String description, BigDecimal price,
                       LocalDate dateOfCreation, LocalDate dateOfModification, int duration) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dateOfCreation = dateOfCreation;
        this.dateOfModification = dateOfModification;
        this.duration = duration;
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(LocalDate dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return duration == that.duration &&
                Objects.equals(certificateId, that.certificateId) &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                Objects.equals(price, that.price) &&
                dateOfCreation.equals(that.dateOfCreation) &&
                Objects.equals(dateOfModification, that.dateOfModification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certificateId, name, description, price, dateOfCreation, dateOfModification, duration);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificateId=" + certificateId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateOfCreation=" + dateOfCreation +
                ", dateOfModification=" + dateOfModification +
                ", duration=" + duration +
                '}';
    }
}

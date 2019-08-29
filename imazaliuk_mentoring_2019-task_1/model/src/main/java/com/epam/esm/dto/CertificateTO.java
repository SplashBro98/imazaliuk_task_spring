package com.epam.esm.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CertificateTO {

    private Long certificateId;

    @NotNull(message = "name can`t be a null")
    @Size(min = 2, max = 100, message = "Size of certificate name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "description can`t be a null")
    @Size(min = 2, max = 1000, message = "Description must be between 2 and 100 characters")
    private String description;

    @NotNull(message = "price can`t be a null")
    @DecimalMin(value = "0", message = "price must be above 0")
    @DecimalMax(value = "100000", message = "price must be below 100000")
    private BigDecimal price;

    @NotNull(message = "date of creation can`t be a null")
    @PastOrPresent(message = "Date of creation must be in the past")
    private LocalDate dateOfCreation;

    @NotNull(message = "date of modification can`t be a null")
    @PastOrPresent(message = "Date of modification must be in the past")
    private LocalDate dateOfModification;

    @NotNull(message = "duration can`t be a null")
    @Min(value = 0, message = "Duration must be above 0")
    @Max(value = 100, message = "Duration must be below 100")
    private int duration;

    private List<@Valid TagTO> tags;

    public CertificateTO() {

    }

    public CertificateTO(String name, String description, BigDecimal price,
                         LocalDate dateOfCreation, LocalDate dateOfModification, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dateOfCreation = dateOfCreation;
        this.dateOfModification = dateOfModification;
        this.duration = duration;
    }

    public CertificateTO(Long certificateId, String name, String description, BigDecimal price,
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

    public List<TagTO> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public void setTags(List<TagTO> tags) {
        this.tags = tags;
    }

    public void addTag(TagTO tag) {
        tags.add(tag);
    }

    public void removeTag(TagTO tag) {
        tags.remove(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateTO that = (CertificateTO) o;
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

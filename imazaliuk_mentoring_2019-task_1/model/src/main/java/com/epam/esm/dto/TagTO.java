package com.epam.esm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class TagTO {

    private Long tagId;

    @NotNull(message = "name can`t be a null")
    @Size(min = 2, max = 100, message = "Size of tag name must be between 2 and 100")
    private String name;


    public TagTO() {

    }

    public TagTO(String name) {
        this.name = name;
    }

    public TagTO(Long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagTO tag = (TagTO) o;
        return Objects.equals(tagId, tag.tagId) &&
                name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, name);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                '}';
    }
}

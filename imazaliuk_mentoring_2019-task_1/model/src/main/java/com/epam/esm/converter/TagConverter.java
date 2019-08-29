package com.epam.esm.converter;

import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.Tag;

import java.util.LinkedList;
import java.util.List;

public final class TagConverter {

    private TagConverter() {

    }

    public static List<TagTO> fromEntityToDTO(List<Tag> list) {
        List<TagTO> result = new LinkedList<>();
        for (Tag tag : list) {

            result.add(fromEntityToDTO(tag));
        }
        return result;
    }

    public static TagTO fromEntityToDTO(Tag tag) {
        TagTO dto = new TagTO();
        dto.setTagId(tag.getTagId());
        dto.setName(tag.getName());
        return dto;
    }

    public static Tag fromDTOToEntity(TagTO dto) {
        Tag tag = new Tag();
        tag.setTagId(dto.getTagId());
        tag.setName(dto.getName());
        return tag;
    }

    public static List<Tag> fromDTOToEntity(List<TagTO> list) {
        List<Tag> result = new LinkedList<>();
        for (TagTO dto : list) {
            result.add(fromDTOToEntity(dto));
        }
        return result;
    }
}

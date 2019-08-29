package com.epam.esm.service.impl;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.CertificateTO;
import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.TagSearch;
import com.epam.esm.handling.CertificateNotFoundException;
import com.epam.esm.handling.TagNotFoundException;
import com.epam.esm.query.CertificateQuery;
import com.epam.esm.query.TagQuery;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.specification.Specification;
import com.epam.esm.util.DbStringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private static final int START = 0;
    private static final int AND_LENGTH = 4;

    private TagRepository tagRepository;
    private CertificateRepository certificateRepository;

    @Autowired
    public TagServiceImpl(TagRepository repository, CertificateRepository certificateRepository) {
        this.tagRepository = repository;
        this.certificateRepository = certificateRepository;
    }


    @Override
    public List<TagTO> findTags(TagSearch search) {
        Map<String, String> map = new HashMap<>();
        if (search.getName() != null) {
            map.put(DbStringConstant.TAG_NAME, search.getName());
        }
        StringBuilder query = new StringBuilder(TagQuery.SELECT_ALL);
        Specification spec = new Specification();
        if (!map.isEmpty()) {
            query.append(DbStringConstant.WHERE);
            Set<String> stringSet = map.keySet();
            for (String key : stringSet) {
                query.append(key + DbStringConstant.AND);
            }
            query = new StringBuilder(query.substring(START, query.length() - AND_LENGTH));
            spec.setParams(Optional.of(map.
                    values().stream().collect(Collectors.toList())));
        }
        if (search.getField() != null) {
            query.append(DbStringConstant.ORDER_BY + search.getField());
        }

        spec.setQuery(query.toString());
        return TagConverter.fromEntityToDTO(tagRepository.query(spec));
    }


    @Override
    public TagTO findById(Long id) {
        Optional<Tag> maybeTag = tagRepository.findById(id);
        if (maybeTag.isPresent()) {
            return TagConverter.fromEntityToDTO(maybeTag.get());
        }
        throw new TagNotFoundException("Tag with id: " + id + " not found");
    }

    @Override
    public List<CertificateTO> findByTag(String tagName) {
        try {
            Specification spec = new Specification();
            spec.setQuery(CertificateQuery.SELECT_BY_TAG_NAME);
            spec.setParams(Optional.of(Arrays.asList(tagName)));
            return CertificateConverter.fromEntityToDTO(certificateRepository.query(spec));
        } catch (RuntimeException ex) {
            throw new CertificateNotFoundException("Certificates with tag: " + tagName + " not found");
        }
    }

    @Override
    public List<CertificateTO> findByTag(Long id) {
        try {
            Specification spec = new Specification();
            spec.setQuery(CertificateQuery.SELECT_BY_TAG_ID);
            spec.setParams(Optional.of(Arrays.asList(id)));
            return CertificateConverter.fromEntityToDTO(certificateRepository.query(spec));
        } catch (RuntimeException ex) {
            throw new CertificateNotFoundException("Certificates with tag: " + id + " not found");
        }
    }


    @Override
    public TagTO updateTag(Long id, TagTO tagTO) {

        Specification spec = new Specification();
        spec.setQuery(TagQuery.UPDATE_TAG);
        spec.setParams(Optional.of(Arrays.asList(tagTO.getName(), id)));
        tagRepository.update(spec);

        return TagConverter.fromEntityToDTO(tagRepository.findById(id).get());
    }

    @Override
    public void save(TagTO tagDTO) {
        tagRepository.save(TagConverter.fromDTOToEntity(tagDTO));
    }

    @Override
    public void deleteById(Long id) {
        Specification spec = new Specification();
        spec.setQuery(TagQuery.DELETE_BY_ID);
        spec.setParams(Optional.of(Arrays.asList(id)));
        tagRepository.delete(spec);
    }

}

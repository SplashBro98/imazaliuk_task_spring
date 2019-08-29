package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.query.TagQuery;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.rowmapper.TagRowMapper;
import com.epam.esm.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private JdbcTemplate template;

    @Autowired
    public TagRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Tag> findById(long id) {
        List<Tag> tags = template.query(TagQuery.SELECT_BY_ID, new Object[]{id},
                new TagRowMapper());
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        List<Tag> tags = template.query(TagQuery.SELECT_BY_NAME, new Object[]{name},
                new TagRowMapper());
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    @Override
    public void save(Tag tag) {
        template.update(TagQuery.INSERT, tag.getName());
    }

    @Override
    public void update(Specification spec) {
        if (spec.getParams().isPresent()) {
            template.update(spec.getQuery(), spec.getParams().get().toArray());
        }
    }

    @Override
    public void delete(Specification spec) {
        if (spec.getParams().isPresent()) {
            template.update(spec.getQuery(), spec.getParams().get().toArray());
        }
    }

    @Override
    public List<Tag> query(Specification spec) {
        List<Tag> result;
        if (!spec.getParams().isPresent()) {
            result = template.query(spec.getQuery(), new TagRowMapper());
        } else {
            result = template.query(spec.getQuery(), spec.getParams().get().toArray(),
                    new TagRowMapper());
        }
        return result.stream().filter(t -> t.getTagId() != 0).collect(Collectors.toList());
    }

    @Override
    public Optional<Long> findTagId(String name) {

        return Optional.of(template.queryForObject(TagQuery.SELECT_ID, new Object[]{name}, Long.class));
    }
}

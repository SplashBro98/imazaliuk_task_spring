package com.epam.esm.repository;

import com.epam.esm.entity.Tag;

import java.util.Optional;

/**
 * interface to work with tags
 */
public interface TagRepository extends CrudRepository<Tag> {

    /**
     * find tag id by tag name
     *
     * @param name is name of tag
     * @return Optional with id
     */
    Optional<Long> findTagId(String name);

    /**
     * find tag by name
     *
     * @param name is name of tag
     * @return Optional with tag
     */
    Optional<Tag> findByName(String name);

}

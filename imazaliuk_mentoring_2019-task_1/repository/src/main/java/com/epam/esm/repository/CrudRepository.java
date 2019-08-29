package com.epam.esm.repository;

import com.epam.esm.handling.RepositoryException;
import com.epam.esm.specification.Specification;

import java.util.List;
import java.util.Optional;

/**
 * interface to access to db
 *
 * @param <T> is entity class
 */
public interface CrudRepository<T> {

    /**
     * @param id is id of the entity from db
     * @return Optional with entity
     */
    Optional<T> findById(long id);

    /**
     * save instance to db
     *
     * @param t is entity instance
     * @throws RepositoryException
     */
    void save(T t);

    /**
     * update instance
     *
     * @param spec contains query and params for query
     */
    void update(Specification spec);

    /**
     * delete instance using params of specification
     *
     * @param spec contains query and params for query
     */
    void delete(Specification spec);


    /**
     * find objects using params of specification
     *
     * @param spec is instance of specification which contains query and params
     * @return list of target objects
     */
    List<T> query(Specification spec);

}

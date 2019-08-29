package com.epam.esm.service;

/**
 * common interface for all services in the project
 *
 * @param <T> is entity class
 */
public interface GiftService<T> {

    /**
     * find object by id
     *
     * @param id is id of searching entity
     * @return instance with this id
     */
    T findById(Long id);

    /**
     * save entity to db
     *
     * @param t is instance of gift entity
     */
    void save(T t);

    /**
     * delete object by id
     *
     * @param id is id of searching entity
     */
    void deleteById(Long id);

}

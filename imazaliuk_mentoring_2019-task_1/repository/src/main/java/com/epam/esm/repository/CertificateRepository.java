package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;

import java.util.Optional;

/**
 * interface for work with certificates
 */
public interface CertificateRepository extends CrudRepository<Certificate> {

    /**
     * save all tags of current certificate
     *
     * @param certId is id of certificate
     * @param tagId  is id of tag
     */
    void saveTagCertificate(Long certId, Long tagId);

    /**
     * find id of current certificate
     *
     * @param name is name of certificate
     * @return Optional with id
     */
    Optional<Long> findCertificateId(String name);

}

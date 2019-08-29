package com.epam.esm.service;

import com.epam.esm.dto.CertificateTO;
import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.CertificateSearch;

import java.util.List;

/**
 * special interface for certificate entities
 */
public interface CertificateService extends GiftService<CertificateTO> {


    /**
     * find certificate tags by id of this certificate
     *
     * @param id is id of certificate
     * @return list of tags of current certificate
     */
    List<TagTO> findCertificateTags(Long id);

    /**
     * find certificate by id and update it in db
     *
     * @param id            is id of certificate
     * @param certificateTO is instance with updating values
     * @return updated certificate
     */
    CertificateTO updateCertificate(Long id, CertificateTO certificateTO);

    /**
     * find certificates by criteria
     *
     * @param certificateSearch is object with criteria values
     * @return list of certificates
     */
    List<CertificateTO> findCertificates(CertificateSearch certificateSearch);
}

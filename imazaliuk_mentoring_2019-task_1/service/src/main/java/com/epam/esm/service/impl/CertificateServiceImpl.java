package com.epam.esm.service.impl;


import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.CertificateTO;
import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificateSearch;
import com.epam.esm.entity.Tag;
import com.epam.esm.handling.CertificateNotFoundException;
import com.epam.esm.query.CertificateQuery;
import com.epam.esm.query.TagQuery;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.impl.CertificateRepositoryImpl;
import com.epam.esm.repository.impl.TagRepositoryImpl;
import com.epam.esm.service.CertificateService;
import com.epam.esm.specification.Specification;
import com.epam.esm.util.DbStringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {

    private static final String PERCENT_SYMBOL = "%";
    private static final int START = 0;
    private static final int AND_LENGTH = 4;

    private CertificateRepository certificateRepository;
    private TagRepository tagRepository;

    @Autowired
    public CertificateServiceImpl(CertificateRepositoryImpl certificateRepository, TagRepositoryImpl tagRepository) {
        this.certificateRepository = certificateRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<CertificateTO> findCertificates(CertificateSearch certificateSearch) {
        Map<String, String> criteriaMap = new HashMap<>();
        if (certificateSearch.getName() != null) {
            criteriaMap.put(DbStringConstant.CERTIFICATE_NAME, certificateSearch.getName());
        }
        if (certificateSearch.getDescription() != null) {
            criteriaMap.put(DbStringConstant.DESCRIPTION_LIKE, PERCENT_SYMBOL + certificateSearch.getDescription()
                    + PERCENT_SYMBOL);
        }
        if (certificateSearch.getDate() != null) {
            criteriaMap.put(DbStringConstant.DATE, certificateSearch.getDate().toString());
        }
        if (certificateSearch.getTagName() != null) {
            criteriaMap.put(DbStringConstant.TAG_NAME, certificateSearch.getTagName());
        }
        StringBuilder query = new StringBuilder(CertificateQuery.SELECT_ALL);
        Specification spec = new Specification();
        if (!criteriaMap.isEmpty()) {
            query.append(DbStringConstant.WHERE);
            Set<String> stringSet = criteriaMap.keySet();
            for (String key : stringSet) {
                query.append(key + DbStringConstant.AND);
            }
            query = new StringBuilder(query.substring(START, query.length() - AND_LENGTH));
            spec.setParams(Optional.of(criteriaMap.
                    values().stream().collect(Collectors.toList())));
        }
        if (certificateSearch.getField() != null) {
            query.append(DbStringConstant.ORDER_BY + certificateSearch.getField());
        }

        spec.setQuery(query.toString());
        return CertificateConverter.fromEntityToDTO(certificateRepository.query(spec));
    }


    @Override
    public CertificateTO findById(Long id) {
        Optional<Certificate> maybeCert = certificateRepository.findById(id);
        if (maybeCert.isPresent()) {
            return CertificateConverter.fromEntityToDTO(maybeCert.get());
        }
        throw new CertificateNotFoundException("Certificate with id: " + id + " not found");
    }


    @Override
    public List<TagTO> findCertificateTags(Long id) {

        Specification spec = new Specification();
        spec.setQuery(TagQuery.SELECT_BY_CERTIFICATE_ID);
        spec.setParams(Optional.of(Arrays.asList(id)));
        return TagConverter.fromEntityToDTO(tagRepository.query(spec));
    }

    @Override
    public void save(CertificateTO certificateDTO) {

        Certificate certificate = CertificateConverter.fromDTOToEntity(certificateDTO);
        certificateRepository.save(certificate);
        Long certId = certificateRepository.findCertificateId(certificate.getName()).get();
        certificate.getTags().forEach(tag -> {
                    Optional<Tag> maybeTag = tagRepository.findByName(tag.getName());
                    if (maybeTag.isPresent()) {
                        certificateRepository.saveTagCertificate(certId, maybeTag.get().getTagId());
                    } else {
                        tagRepository.save(tag);
                        Long id = tagRepository.findTagId(tag.getName()).get();
                        certificateRepository.saveTagCertificate(certId, id);
                    }
                }
        );
    }

    @Override
    public CertificateTO updateCertificate(Long id, CertificateTO certificateTO) {

        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.UPDATE_CERTIFICATE);
        Certificate certificate = CertificateConverter.fromDTOToEntity(certificateTO);
        spec.setParams(Optional.of(Arrays.asList(certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDateOfCreation().toString(),
                certificate.getDateOfModification().toString(), certificate.getDuration(), id)));
        certificateRepository.update(spec);
        certificate.getTags().forEach(tag -> {
                    Optional<Tag> maybeTag = tagRepository.findByName(tag.getName());
                    if (!maybeTag.isPresent()) {
                        tagRepository.save(tag);
                    }
                }
        );
        spec = new Specification();
        spec.setQuery(CertificateQuery.DELETE_CERTIFICATE_TAG_BY_CERTIFICATE_ID);
        spec.setParams(Optional.of(Arrays.asList(id)));
        certificateRepository.delete(spec);

        certificate.getTags().forEach(tag -> {
            Long tagId = tagRepository.findTagId(tag.getName()).get();
            certificateRepository.saveTagCertificate(id, tagId);
        });

        return CertificateConverter.fromEntityToDTO(certificateRepository.findById(id).get());
    }

    @Override
    public void deleteById(Long id) {

        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.DELETE_BY_ID);
        spec.setParams(Optional.of(Arrays.asList(id)));
        certificateRepository.delete(spec);
    }

}

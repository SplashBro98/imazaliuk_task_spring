package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.handling.RepositoryException;
import com.epam.esm.query.CertificateQuery;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.rowmapper.CertificateRowMapper;
import com.epam.esm.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class CertificateRepositoryImpl implements CertificateRepository {

    private JdbcTemplate template;

    /**
     * constructor injection
     *
     * @param template is instance of JdbcTemplate
     */
    @Autowired
    public CertificateRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Certificate> findById(long id) {
        List<Certificate> certs = template.query(CertificateQuery.SELECT_BY_ID, new Object[]{id},
                new CertificateRowMapper());
        certs.forEach(c -> c.setTags(c.getTags().stream().filter(t -> t.getTagId() != 0)
                .collect(Collectors.toList())));
        return certs.isEmpty() ? Optional.empty() : Optional.of(certs.get(0));
    }


    @Override
    public void save(Certificate cert) {
        try {
            template.update(CertificateQuery.INSERT, cert.getName(), cert.getDescription(), cert.getPrice(),
                    cert.getDateOfCreation().toString(), cert.getDateOfModification().toString(),
                    cert.getDuration());
        } catch (BadSqlGrammarException ex) {
            throw new RepositoryException(ex);
        }
    }

    @Override
    public void update(Specification spec) {
        if (spec.getParams().isPresent()) {
            List<Object> params = spec.getParams().get();
            template.update(spec.getQuery(), params.toArray());
        }
    }

    @Override
    public void delete(Specification spec) {
        if (spec.getParams().isPresent()) {
            template.update(spec.getQuery(), spec.getParams().get().toArray());
        }
    }

    @Override
    public List<Certificate> query(Specification spec) {
        List<Certificate> result;
        try {
            if (!spec.getParams().isPresent()) {
                result = template.query(spec.getQuery(), new CertificateRowMapper());
            } else {
                result = template.query(spec.getQuery(), spec.getParams().get().toArray(),
                        new CertificateRowMapper());
            }
            result = result.stream().filter(new Predicate<Certificate>() {
                Set<Long> set = new TreeSet<>();

                @Override
                public boolean test(Certificate cert) {
                    Long certId = cert.getCertificateId();
                    if (!set.contains(certId)) {
                        set.add(certId);
                        return true;
                    }
                    return false;
                }
            }).collect(Collectors.toList());
            result.forEach(c -> c.setTags(c.getTags().stream().filter(t -> t.getTagId() != 0).
                    collect(Collectors.toList())));
            return result;
        } catch (BadSqlGrammarException ex) {
            throw new RepositoryException(ex);
        }
    }

    @Override
    public void saveTagCertificate(Long certId, Long tagId) {

        template.update(CertificateQuery.INSERT_CERTIFICATE_TAG, certId, tagId);
    }

    @Override
    public Optional<Long> findCertificateId(String name) {

        return Optional.of(template.queryForObject(CertificateQuery.SELECT_ID, new Object[]{name}, Long.class));
    }

}

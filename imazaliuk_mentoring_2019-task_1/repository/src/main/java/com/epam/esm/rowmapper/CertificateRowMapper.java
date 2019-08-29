package com.epam.esm.rowmapper;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.util.DbStringConstant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * custom mapper which builds certificate from ResultSet
 */
public class CertificateRowMapper implements RowMapper<Certificate> {

    private Map<Long, Certificate> certificateHashMap = new HashMap<>();

    @Override
    public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong(DbStringConstant.CERTIFICATE_ID);


        if (!certificateHashMap.containsKey(id)) {
            Certificate certificate = new Certificate();
            certificate.setCertificateId(id);
            certificate.setName(resultSet.getString(DbStringConstant.NAME));
            certificate.setPrice(resultSet.getBigDecimal(DbStringConstant.PRICE));
            certificate.setDescription(resultSet.getString(DbStringConstant.DESCRIPTION));
            certificate.setDateOfCreation(resultSet.getDate(DbStringConstant.DATE_OF_CREATION).toLocalDate());
            certificate.setDateOfModification(resultSet.getDate(DbStringConstant.DATE_OF_MODIFICATION).toLocalDate());
            certificate.setDuration(resultSet.getInt(DbStringConstant.DURATION));
            certificate.setTags(new LinkedList<>());
            certificateHashMap.put(id, certificate);
        }

        Tag tag = new Tag();
        tag.setTagId(resultSet.getLong(DbStringConstant.TAG_ID));
        tag.setName(resultSet.getString(DbStringConstant.TNAME));
        certificateHashMap.get(id).addTag(tag);


        return certificateHashMap.get(id);

    }
}

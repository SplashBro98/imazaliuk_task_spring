package com.epam.esm.rowmapper;

import com.epam.esm.entity.Tag;
import com.epam.esm.util.DbStringConstant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * custom mapper which builds tag from ResultSet
 */
public class TagRowMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag tag = new Tag();
        tag.setTagId(resultSet.getLong(DbStringConstant.TAG_ID));
        tag.setName(resultSet.getString(DbStringConstant.TNAME));
        return tag;
    }
}

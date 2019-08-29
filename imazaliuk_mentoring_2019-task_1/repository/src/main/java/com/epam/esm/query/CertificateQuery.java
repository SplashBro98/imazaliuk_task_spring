package com.epam.esm.query;

public final class CertificateQuery {

    //language=sql
    public static final String SELECT_ALL = "select c.certificate_id, c.name, c.description, c.price, " +
            " c.date_of_creation, " +
            "c.date_of_modification, c.duration, t.tag_id, t.name as tname " +
            "from certificates c " +
            "full join certificate_tag ct  on c.certificate_id = ct.certificate_id " +
            "left join tags t on ct.tag_id = t.tag_id";
    //language=sql
    public static final String SELECT_BY_ID = "select c.certificate_id, c.name, c.description, c.price, " +
            "c.date_of_creation, " +
            "c.date_of_modification, c.duration, t.tag_id, t.name as tname " +
            "from certificates c " +
            "full join certificate_tag ct  on c.certificate_id = ct.certificate_id " +
            "left join tags t on ct.tag_id = t.tag_id where c.certificate_id = ?";
    //language=sql
    public static final String INSERT = "insert into certificates (name, description, price, date_of_creation, " +
            "date_of_modification, duration) VALUES (?,?,?,cast(? as date),cast(? as date),?)";
    //language=sql
    public static final String UPDATE_CERTIFICATE = "update certificates set name = ?, description = ?, price = ?, " +
            "date_of_creation = cast(? as date), " +
            "date_of_modification = cast(? as date), duration = ? where certificate_id = ?";
    //language=sql
    public static final String DELETE_BY_ID = "delete from certificates where certificate_id = ?";
    //language=sql
    public static final String DELETE_CERTIFICATE_TAG_BY_CERTIFICATE_ID = "delete from certificate_tag where certificate_id = ?";
    //language=sql
    public static final String SELECT_ID = "select c.certificate_id from certificates c where c.name = ?";
    //language=sql
    public static final String INSERT_CERTIFICATE_TAG = "insert into certificate_tag(certificate_id, tag_id) VALUES " +
            "(?,?)";
    //language=sql
    public static final String SELECT_BY_TAG_ID = "select c.certificate_id, c.name, c.description, c.price, " +
            "c.date_of_creation, " +
            "c.date_of_modification, c.duration, t.tag_id, t.name as tname " +
            "from certificates c " +
            "full join certificate_tag ct  on c.certificate_id = ct.certificate_id " +
            "full join tags t on ct.tag_id = t.tag_id where t.tag_id = ?";
    //language=sql
    public static final String SELECT_BY_TAG_NAME = "select c.certificate_id, c.name, c.description, c.price, " +
            "c.date_of_creation, " +
            "c.date_of_modification, c.duration, t.tag_id, t.name as tname " +
            "from certificates c " +
            "full join certificate_tag ct  on c.certificate_id = ct.certificate_id " +
            "full join tags t on ct.tag_id = t.tag_id where t.name = ?";

    private CertificateQuery() {

    }


}

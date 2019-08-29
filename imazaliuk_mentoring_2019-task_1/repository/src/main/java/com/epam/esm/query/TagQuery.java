package com.epam.esm.query;

public final class TagQuery {

    //language=sql
    public static final String SELECT_ALL = "select t.tag_id, t.name as tname from tags t";

    //language=sql
    public static final String SELECT_BY_ID = "select t.tag_id, t.name as tname from tags t where t.tag_id = ?";

    //language=sql
    public static final String SELECT_BY_NAME = "select t.tag_id, t.name as tname from tags t where t.name = ?";

    //language=sql
    public static final String INSERT = "insert into tags(name) values (?)";

    //language=sql
    public static final String UPDATE_TAG = "update tags set name = ? where tag_id = ?";

    //language=sql
    public static final String DELETE_BY_ID = "delete from tags where tag_id = ?";

    //language=sql
    public static final String SELECT_ID = "select t.tag_id from tags t where t.name = ?";

    //language=sql
    public static final String SELECT_BY_CERTIFICATE_ID = "select c.certificate_id, c.name, c.description, c.price, " +
            "c.date_of_creation, " +
            "c.date_of_modification, c.duration, t.tag_id, t.name as tname " +
            "from certificates c " +
            "full join certificate_tag ct  on c.certificate_id = ct.certificate_id " +
            "full join tags t on ct.tag_id = t.tag_id where c.certificate_id = ?";


    private TagQuery() {

    }
}

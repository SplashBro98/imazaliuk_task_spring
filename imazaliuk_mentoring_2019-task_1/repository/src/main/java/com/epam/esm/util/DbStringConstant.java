package com.epam.esm.util;

public final class DbStringConstant {

    public static final String NAME = "name";
    public static final String CERTIFICATE_ID = "certificate_id";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String DURATION = "duration";
    public static final String DATE_OF_CREATION = "date_of_creation";
    public static final String DATE_OF_MODIFICATION = "date_of_modification";
    public static final String TAG_ID = "tag_id";
    public static final String TNAME = "tname";
    public static final String WHERE = " where ";
    public static final String ORDER_BY = " order by ";
    public static final String AND = " and ";
    public static final String CERTIFICATE_NAME = " c.name = ? ";
    public static final String DESCRIPTION_LIKE = " c.description like ? ";
    public static final String TAG_NAME = " t.name = ? ";
    public static final String DATE = " c.date_of_creation = cast(? as date) ";

    private DbStringConstant() {

    }
}

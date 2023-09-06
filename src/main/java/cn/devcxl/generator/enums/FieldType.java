package cn.devcxl.generator.enums;

/**
 * 字段类型
 */
public enum FieldType {
    TINYINT("java.lang.Boolean", "tinyint"),
    SMALLINT("java.lang.Integer", "smallint"),
    INTEGER("java.lang.Integer", "int"),
    BIGINT("java.lang.Long", "bigint"),
    FLOAT("java.lang.Float", "float"),
    DOUBLE("java.lang.Double", "double"),
    DECIMAL("java.math.BigDecimal", "decimal"),
    CHAR("java.lang.String", "char"),
    VARCHAR("java.lang.String", "varchar"),
    TEXT("java.lang.String", "text"),
    DATE("java.sql.Date", "date"),
    TIME("java.sql.Time", "time"),
    TIMESTAMP("java.sql.Timestamp", "timestamp");
    private String javaType;
    private String sqlType;

    FieldType(String javaType, String sqlType) {
        this.javaType = javaType;
        this.sqlType = sqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getSqlType() {
        return sqlType;
    }

}

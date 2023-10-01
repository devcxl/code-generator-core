package cn.devcxl.generator.enums;

/**
 * 字段类型
 *
 * @author devcxl
 */
public enum FieldType {

    TINYINT("java.lang.Boolean", "tinyint", "boolean"),
    SMALLINT("java.lang.Integer", "smallint", "number"),
    INTEGER("java.lang.Integer", "int", "number"),
    BIGINT("java.lang.Long", "bigint", "number"),
    FLOAT("java.lang.Float", "float", "number"),
    DOUBLE("java.lang.Double", "double", "number"),
    DECIMAL("java.math.BigDecimal", "decimal", "number"),
    CHAR("java.lang.String", "char", "string"),
    VARCHAR("java.lang.String", "varchar", "string"),
    TEXT("java.lang.String", "text", "string"),
    DATE("java.util.Date", "date", "Date"),
    TIME("java.sql.Time", "time", "Date"),
    TIMESTAMP("java.util.Date", "timestamp", "Date");
    /**
     * Java类型
     */
    private String javaType;
    /**
     * SQL类型
     */
    private String sqlType;
    /**
     * TypeScript类型
     */
    private String tsType;

    FieldType(String javaType, String sqlType, String tsType) {
        this.javaType = javaType;
        this.sqlType = sqlType;
        this.tsType = tsType;
    }

    /**
     * 获取Java类型全类名
     *
     * @return
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * 获取Java类型短类名
     *
     * @return
     */
    public String getJavaShortType() {
        String[] split = javaType.split("\\.");
        return split[split.length - 1];
    }

    /**
     * 获取数据库字段类型名
     *
     * @return
     */
    public String getSqlType() {
        return sqlType;
    }


    /**
     * 获取TypeScript字段类型名
     *
     * @return
     */
    public String getTsType() {
        return sqlType;
    }

}

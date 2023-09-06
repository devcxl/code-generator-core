package cn.devcxl.generator.domain;

import cn.devcxl.generator.enums.FieldType;
import lombok.Getter;
import lombok.Setter;

/**
 * 生成表列名相关信息
 *
 * @author devcxl
 */
@Getter
@Setter
public class FieldInfo {
    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段注释
     */
    private String comment;

    /**
     * 字段类型
     */
    private FieldType fieldType;

    /**
     * 获取Java字段类型
     *
     * @return
     */
    public String getJavaType() {
        String[] temp = fieldType.getJavaType().split("\\.");
        return temp[temp.length - 1];
    }

    public String getJavaField() {
        return name;
    }

    /**
     * 可在字段类型后定义的自定义sql
     */
    private String customSql;

    /**
     * 是否主键
     */
    private boolean isPk;

    /**
     * 是否自增
     */
    private boolean isIncrement;

    /**
     * 是否必填
     */
    private boolean isRequired;


    /**
     * 是否为插入字段
     */
    private boolean isInsert;

    /**
     * 是否编辑字段
     */
    private boolean isEdit;

    /**
     * 是否列表字段
     */
    private boolean isList;

    /**
     * 是否查询字段
     */
    private boolean isQuery;

    /**
     * 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
     */
    private String queryType;

    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、upload上传控件）
     */
    private String htmlType;

    /**
     * 字典类型
     */
    private String dictType;


    public FieldInfo(String name, String comment, FieldType fieldType, String customSql, boolean isPk, boolean isIncrement, boolean isRequired, boolean isInsert, boolean isEdit, boolean isList, boolean isQuery, String queryType, String htmlType, String dictType) {
        this.name = name;
        this.comment = comment;
        this.fieldType = fieldType;
        this.customSql = customSql;
        this.isPk = isPk;
        this.isIncrement = isIncrement;
        this.isRequired = isRequired;
        this.isInsert = isInsert;
        this.isEdit = isEdit;
        this.isList = isList;
        this.isQuery = isQuery;
        this.queryType = queryType;
        this.htmlType = htmlType;
        this.dictType = dictType;
    }
}

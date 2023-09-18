package cn.devcxl.generator.domain;

import cn.devcxl.generator.utils.GeneratorUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成代码表相关信息
 *
 * @author devcxl
 */
@Getter
@Setter
public class EntityInfo {

    /**
     * 实体ID
     */
    private Integer entityId;

    /**
     * 实体名称
     */
    private String name;

    /**
     * 实体描述
     */
    private String comment;

    /**
     * 生成的类名称(首字母大写)
     */
    private String className;

    /**
     * 生成的表名称
     */
    private String tableName;

    /**
     * 实体属性信息
     */
    private List<FieldInfo> fields;


    /**
     * 构造方法
     *
     * @param configuration 生成器配置
     * @param name          实体名称
     * @param className     Java类名（必须符合驼峰命名法）
     * @param comment       实体注释
     * @param fields
     */
    public EntityInfo(Configuration configuration, String name, String className, String comment, List<FieldInfo> fields) {
        String tablePrefix = configuration.getTablePrefix();
        this.className = GeneratorUtils.toUpperCaseCamelCase(className);
        this.name = name;
        this.tableName = tablePrefix + GeneratorUtils.toSnakeCase(this.className);
        this.comment = comment;
        this.fields = fields;
    }


    /**
     * 必须的字段
     *
     * @return
     */
    public List<FieldInfo> requiredFields() {
        return this.fields.stream().filter(FieldInfo::isRequired).collect(Collectors.toList());
    }

    /**
     * 列表展示的字段
     *
     * @return
     */
    public List<FieldInfo> showListFields() {
        return this.fields.stream().filter(FieldInfo::isList).collect(Collectors.toList());
    }

    /**
     * 作为条件查询的字段
     *
     * @return
     */
    public List<FieldInfo> queryFields() {
        return this.fields.stream().filter(FieldInfo::isQuery).collect(Collectors.toList());
    }


    /**
     * 获取主键字段
     *
     * @return
     */
    public List<FieldInfo> primaryKeyFields() {
        return this.fields.stream().filter(FieldInfo::isPrimaryKey).collect(Collectors.toList());
    }

    /**
     * 获取非主键字段
     *
     * @return
     */
    public List<FieldInfo> noPrimaryKeyFields() {
        return this.fields.stream().filter(fieldInfo -> !fieldInfo.isPrimaryKey()).collect(Collectors.toList());
    }


    /**
     * 获取主键字段
     *
     * @return
     */
    public FieldInfo primaryKeyField() {
        List<FieldInfo> fieldInfos = this.primaryKeyFields();
        int size = fieldInfos.size();
        if (fieldInfos.size() == 1) {
            return fieldInfos.get(0);
        } else {
            throw new RuntimeException(MessageFormat.format("primaryKeyFields count {0}", size));
        }
    }


}

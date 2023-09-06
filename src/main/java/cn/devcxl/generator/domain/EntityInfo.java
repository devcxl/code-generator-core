package cn.devcxl.generator.domain;

import lombok.Getter;
import lombok.Setter;

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
     * 表名称
     */
    private String name;

    /**
     * 表描述
     */
    private String comment;

    /**
     * 实体类名称(首字母大写)
     */
    private String className;

    /**
     * 生成路径
     */
    private String outPath;

    /**
     * 主键信息
     */
    private FieldInfo pkColumn;

    /**
     * 表列信息
     */
    private List<FieldInfo> fields;

    public EntityInfo(Configuration configuration, String name, String comment, String className, FieldInfo pkColumn, List<FieldInfo> fields) {
        if (configuration.isAutoRemovePre()) {
            String tablePrefix = configuration.getTablePrefix();
            boolean b = className.startsWith(tablePrefix);
            if (b) {
                this.className = className.substring(tablePrefix.length());
            } else {
                this.className = className;
            }
        }
        String projectSrcPath = configuration.getProjectSrcPath();
        String packageName = configuration.getPackageNameWithSlash();
        this.outPath = projectSrcPath + packageName;
        this.name = name;
        this.comment = comment;
        this.pkColumn = pkColumn;
        this.fields = fields;
    }


    /**
     * 必须的字段
     * @return
     */
    public List<FieldInfo> requiredFields(){
        return this.fields.stream().filter(FieldInfo::isRequired).collect(Collectors.toList());
    }
    /**
     * 列表展示的字段
     * @return
     */
    public List<FieldInfo> showListFields(){
        return this.fields.stream().filter(FieldInfo::isList).collect(Collectors.toList());
    }
}

package cn.devcxl.generator.constant;

/**
 * 代码生成器相关常量信息
 *
 * @author devcxl
 */
public class GeneratorConstant {

    /**
     * Java代码生成目录
     */
    public static final String JAVA_CODE_PATH = "main/java/";
    /**
     * 资源生成目录
     */
    public static final String RESOURCES_PATH = "main/resources";

    /**
     * 插入数据时排除的属性
     */
    public static final String[] INSERT_EXCLUDE_FIELDS = {"createAt", "createTime", "updateAt", "updateTime", "id", "ID"};

}

package cn.devcxl.generator.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 生成器配置
 *
 * @author devcxl
 */
@Getter
@Setter
public class Configuration {

    /**
     * 作者
     **/
    private String author;

    /**
     * 项目源码路径 eg: /path/to/project/src/
     */
    private String projectSrcPath;

    /**
     * 包名
     **/
    private String packageName;

    /**
     * 表前缀
     **/
    private String tablePrefix = "";

    /**
     * 假删除 添加deleted字段
     */
    private boolean deletedFlag = true;

    /**
     * 包名路径模式 eg: cn/devcxl/cms
     * @return
     */
    public String getPackageNameWithSlash() {
        return packageName.replace(".", "/");
    }
}

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
     * 自动去除表前缀，默认是false
     **/
    private boolean autoRemovePre = false;

    /**
     * 表前缀(类名不会包含表前缀)
     **/
    private String tablePrefix;

    /**
     * 包名路径模式 eg: cn/devcxl/cms
     * @return
     */
    public String getPackageNameWithSlash() {
        return packageName.replace(".", "/");
    }
}

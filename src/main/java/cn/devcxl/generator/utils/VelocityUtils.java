package cn.devcxl.generator.utils;

import cn.devcxl.generator.domain.Configuration;
import cn.devcxl.generator.domain.FieldInfo;
import cn.devcxl.generator.domain.EntityInfo;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author devcxl
 */
public class VelocityUtils {
    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * 设置模板变量信息
     */
    public static VelocityContext prepareContext(Configuration configuration, EntityInfo entityInfo) {
        String packageName = configuration.getPackageName();
        String author = configuration.getAuthor();
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("entity", entityInfo);
        velocityContext.put("tableName", entityInfo.getName());
        velocityContext.put("ClassName", entityInfo.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(entityInfo.getClassName()));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", author);
        velocityContext.put("datetime", DateUtil.today());
        velocityContext.put("importList", getImportList(entityInfo));
        velocityContext.put("fields", entityInfo.getFields());
        velocityContext.put("primaryKeyFields",entityInfo.primaryKeyFields());
        velocityContext.put("noPrimaryKeyFields",entityInfo.noPrimaryKeyFields());
        velocityContext.put("primaryKeyField",entityInfo.primaryKeyField());
        velocityContext.put("showListFields", entityInfo.showListFields());
        velocityContext.put("requiredFields", entityInfo.requiredFields());
        velocityContext.put("queryFields", entityInfo.queryFields());
        return velocityContext;
    }


    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList() {
        List<String> templates = new ArrayList<String>();
        // 实体类
        templates.add("templates/java/domain.java.vm");
        // 展示类
        templates.add("templates/java/projection.java.vm");
        // 查询条件
        templates.add("templates/java/query.java.vm");
//        templates.add("templates/java/service.java.vm");
//        templates.add("templates/java/serviceImpl.java.vm");
//        templates.add("templates/java/controller.java.vm");
//
//        templates.add("templates/java/mapper.java.vm");
        templates.add("templates/xml/mapper.xml.vm");

//        templates.add("templates/html/list.html.vm");
//        templates.add("templates/html/add.html.vm");
//        templates.add("templates/html/edit.html.vm");
//        templates.add("templates/sql/sql.vm");
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, Configuration configuration, EntityInfo entityInfo) {
        String fileName = "";
        String packageName = configuration.getPackageName();
        String className = entityInfo.getClassName();
        String businessName = entityInfo.getComment();
        String javaPath = PROJECT_PATH + "/" + packageName.replace(".", "/");
        if (template.contains("domain.java.vm")) {
            fileName = String.format("%s/domain/%s.java", javaPath, className);
        }
        if (template.contains("mapper.java.vm")) {
            fileName = String.format("%s/mapper/%sMapper.java", javaPath, className);
        } else if (template.contains("service.java.vm")) {
            fileName = String.format("%s/service/I%sService.java", javaPath, className);
        } else if (template.contains("serviceImpl.java.vm")) {
            fileName = String.format("%s/service/impl/%sServiceImpl.java", javaPath, className);
        } else if (template.contains("controller.java.vm")) {
            fileName = String.format("%s/controller/%sController.java", javaPath, className);
        } else if (template.contains("mapper.xml.vm")) {
            fileName = String.format("%s/mapper/xml/%sMapper.xml", javaPath, className);
        } else if (template.contains("sql.vm")) {
            fileName = businessName + "Menu.sql";
        }
        return fileName;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param entityInfo 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(EntityInfo entityInfo) {
        List<FieldInfo> fieldInfos = entityInfo.getFields();
        HashSet<String> importList = new HashSet<String>();
        for (FieldInfo field : fieldInfos) {
            importList.add(field.getFieldType().getJavaType());
        }
        return importList;
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName   模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName) {
        return String.format("%s:%s", moduleName, businessName);
    }
}

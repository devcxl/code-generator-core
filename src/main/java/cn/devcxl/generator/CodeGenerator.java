package cn.devcxl.generator;

import cn.devcxl.generator.domain.Configuration;
import cn.devcxl.generator.domain.FieldInfo;
import cn.devcxl.generator.domain.EntityInfo;
import cn.devcxl.generator.enums.FieldType;
import cn.devcxl.generator.utils.VelocityUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 创建生成配置
        Configuration configuration = new Configuration();
        configuration.setAuthor("devcxl");
        configuration.setPackageName("cn.devcxl.cms");
        configuration.setAutoRemovePre(true);
        configuration.setTablePrefix("cms_");

        List<FieldInfo> fieldInfos = new ArrayList<>();

        FieldInfo idField = new FieldInfo("id", "用户ID", FieldType.INTEGER, "", true, true, true, false, false, false, true, "EQ", "input", "");
        FieldInfo usernameField = new FieldInfo("username", "用户名", FieldType.VARCHAR, "", false, false, true, true, true, true, true, "LIKE", "input", "");
        FieldInfo passwordField = new FieldInfo("password", "密码", FieldType.VARCHAR, "", false, false, true, false, true, false, false, "", "input", "");
        FieldInfo emailField = new FieldInfo("email", "邮箱", FieldType.VARCHAR, "", false, false, false, true, true, true, true, "LIKE", "input", "");
        FieldInfo ageField = new FieldInfo("age", "年龄", FieldType.INTEGER, "", false, false, false, true, true, true, true, "EQ", "input", "");
        FieldInfo genderField = new FieldInfo("gender", "性别", FieldType.VARCHAR, "", false, false, false, true, true, true, true, "EQ", "select", "genderDict");
        FieldInfo birthdayField = new FieldInfo("birthday", "生日", FieldType.DATE, "", false, false, false, true, true, true, true, "BETWEEN", "datetime", "");

        fieldInfos.add(idField);
        fieldInfos.add(usernameField);
        fieldInfos.add(passwordField);
        fieldInfos.add(emailField);
        fieldInfos.add(ageField);
        fieldInfos.add(genderField);
        fieldInfos.add(birthdayField);

        // 创建表信息
        EntityInfo entityInfo = new EntityInfo(configuration, "cms_user", "用户表", "User", fieldInfos);

        VelocityInitializer.initVelocity();
        VelocityContext context = VelocityUtils.prepareContext(configuration, entityInfo);

        List<String> templates = VelocityUtils.getTemplateList();
        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Velocity.ENCODING_DEFAULT);
            tpl.merge(context, sw);
            System.out.printf("%s\n%s", template, sw.toString());
            System.out.print("\n===============================\n");
        }
    }

}

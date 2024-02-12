# code-generator-core

Java CRUD 代码生成器

使用示例： CodeGenerator

```java
package cn.devcxl.generator;

import cn.devcxl.generator.domain.Configuration;
import cn.devcxl.generator.domain.EntityInfo;
import cn.devcxl.generator.domain.FieldInfo;
import cn.devcxl.generator.enums.FieldType;
import cn.devcxl.generator.utils.VelocityUtils;
import cn.hutool.core.io.FileUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static cn.devcxl.generator.enums.QueryType.*;

/**
 * 代码生成器
 *
 * @author devcxl
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 创建生成配置
        Configuration configuration = new Configuration();
        configuration.setAuthor("devcxl");
        configuration.setPackageName("cn.devcxl.code");
        configuration.setTablePrefix("cms_");
        configuration.setProjectSrcPath("/home/devcxl/IdeaProjects/code-gen-template/src/");

        List<FieldInfo> fieldInfos = new ArrayList<>();

        FieldInfo idField = new FieldInfo("id", "用户ID", FieldType.INTEGER, "(11)", "", true, true, true, false, false, false, true, EQ, "input", "");
        FieldInfo usernameField = new FieldInfo("userName", "用户名", FieldType.VARCHAR, "(30)", "", false, false, true, true, true, true, true, LIKE, "input", "");
        FieldInfo passwordField = new FieldInfo("password", "密码", FieldType.VARCHAR, "(64)", "", false, false, true, false, true, false, false, NONE, "input", "");
        FieldInfo emailField = new FieldInfo("email", "邮箱", FieldType.VARCHAR, "(30)", "", false, false, false, true, true, true, true, LIKE, "input", "");
        FieldInfo ageField = new FieldInfo("age", "年龄", FieldType.INTEGER, "(3)", "", false, false, false, true, true, true, true, EQ, "input", "");
        FieldInfo genderField = new FieldInfo("gender", "性别", FieldType.TINYINT, "(1)", "", false, false, false, true, true, true, true, EQ, "select", "genderDict");
        FieldInfo birthdayField = new FieldInfo("birthday", "生日", FieldType.DATE, "", "", false, false, false, true, true, true, true, BETWEEN, "datetime", "");
        FieldInfo createAt = new FieldInfo("createAt", "创建时间", FieldType.TIMESTAMP, "", "CURRENT_TIMESTAMP", false, false, true, false, false, false, true, BETWEEN, "datetime", "");
        FieldInfo updateAt = new FieldInfo("updateAt", "更新时间", FieldType.TIMESTAMP, "", "CURRENT_TIMESTAMP", false, false, true, false, false, false, false, BETWEEN, "datetime", "");

        fieldInfos.add(idField);
        fieldInfos.add(usernameField);
        fieldInfos.add(passwordField);
        fieldInfos.add(emailField);
        fieldInfos.add(ageField);
        fieldInfos.add(genderField);
        fieldInfos.add(birthdayField);
        fieldInfos.add(createAt);
        fieldInfos.add(updateAt);

        // 创建表信息
        EntityInfo entityInfo = new EntityInfo(configuration, "用户测试", "User", "管理用户信息", fieldInfos);

        run(configuration, List.of(entityInfo));
    }

    public static void run(Configuration configuration, List<EntityInfo> entityInfos) {
        VelocityInitializer.initVelocity();

        for (EntityInfo entityInfo : entityInfos) {
            VelocityContext context = VelocityUtils.prepareContext(configuration, entityInfo);
            List<String> templateList = VelocityUtils.getTemplateList();
            for (String s : templateList) {
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(s, Velocity.ENCODING_DEFAULT);
                tpl.merge(context, sw);
                String fileName = VelocityUtils.getFileName(s, configuration, entityInfo);
                System.out.println(fileName);
                FileUtil.writeUtf8String(sw.toString(), configuration.getProjectSrcPath() + fileName);
            }
        }
    }
}
```
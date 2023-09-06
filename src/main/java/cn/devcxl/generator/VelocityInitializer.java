package cn.devcxl.generator;

import org.apache.velocity.app.Velocity;

import java.util.Properties;

public class VelocityInitializer {
    /**
     * 初始化模板引擎
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

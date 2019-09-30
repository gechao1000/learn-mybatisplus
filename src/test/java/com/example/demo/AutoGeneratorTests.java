package com.example.demo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class AutoGeneratorTests {

    @Test
    public void test() {
        generate("user", "mobile");
    }

    private void generate(String... tables) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig global = new GlobalConfig();
        global.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
//        global.setOutputDir("d:/codeGen");
        global.setAuthor("gexc");
        global.setOpen(false);
        //默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        global.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        generator.setGlobalConfig(global);

        // 数据源配置
        DataSourceConfig ds = new DataSourceConfig();
        ds.setUrl("jdbc:mysql://192.168.80.128:3306/test");
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
        // ds.setSchemaName("public");
        ds.setUsername("root");
        ds.setPassword("123456");
        generator.setDataSource(ds);

        // 包配置
        PackageConfig pkg = new PackageConfig();
        pkg.setModuleName("bmcp");
        pkg.setParent("com.example.demo");
        generator.setPackageInfo(pkg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(tables);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pkg.getModuleName() + "_");
        strategy.setEntityTableFieldAnnotationEnable(true);
//        strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
//        strategy.setSuperEntityColumns("id");
//        strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        generator.setStrategy(strategy);

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

//        configCustomizedCodeTemplate(generator);
//        configInjection(generator);

        generator.execute();
    }

    /**
     * 配置 自定义模板
     * @param generator
     */
    private void configCustomizedCodeTemplate(AutoGenerator generator){
        TemplateConfig template = new TemplateConfig();
        // 指定Entity生成使用自定义模板
        template.setEntity("templates/MyEntityTemplate.java");
        // 不生成xml
        template.setXml(null);
        generator.setTemplate(template);
    }

    /**
     * 配置 自定义参数/属性
     *
     * @param generator
     */
    private void configInjection(AutoGenerator generator){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
                /*
                自定义属性注入: 模板配置：abc=${cfg.abc}
                 */
            }
        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/MyEntityTemplate.java.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 指定模板生，自定义生成文件到哪个地方
//                return "D:/abc";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
    }
}

package com.example.demo;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class _AutoGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir") + "/learn-mybatisplus/";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig global = new GlobalConfig();
        global.setOutputDir(PROJECT_DIR + "src/main/java");
        global.setAuthor("gexc");
        global.setOpen(false);
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
        pkg.setModuleName("db");
        pkg.setParent("com.example.demo");
        generator.setPackageInfo(pkg);

        // 自定义配置
        InjectionConfig inject = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PROJECT_DIR + "/src/main/resources/mapper/" + pkg.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        inject.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        inject.setFileOutConfigList(focList);
        generator.setCfg(inject);


        // 配置模板
        TemplateConfig template = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        template.setXml(null);
        generator.setTemplate(template);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.example.demo.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude("mobile,user");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pkg.getModuleName() + "_");
        generator.setStrategy(strategy);
        // generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}

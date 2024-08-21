package com.paradise.user.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @author aaron
 * @version 1.0
 * @since 2020-07-20 09:16
 */
public class PlusGenerator {
    private static final String DATABASE = "boss-user";
    private static final String URL = "jdbc:mysql://192.168.1.36:3306/" + DATABASE + "?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root36";

    //private static String[] tables = new String[]{"users","user_profiles"};

    private static String basePath = "";
    private static String mapperPath = "";

    public static void main(String[] args) {
        String author = "jiangrenfeng";
        String packageName = "com.paradise.user";
        String[] tablePrefix = null;
        execute(author,packageName,tablePrefix);
    }


    private static void execute(String author , String packageName , String... tablePrefix){
        AutoGenerator autoGenerator = new AutoGenerator()
                //配置数据源
                .setDataSource(initDataSourceConfig())
                //全局配置
                .setGlobalConfig(initGlobalConfig(author,packageName))
                //策略配置项
                .setStrategy(initStrategyConfig())
                //跟包相关的配置项
                .setPackageInfo(initPackageConfig(packageName))
                .setCfg(initInjectionConfig(packageName))
                //設置 mapper 接口包下 不生成 xml的包
//                .setTemplate(new TemplateConfig().setXml(null).setMapper(null))
                .setTemplate(new TemplateConfig().setXml(null))
                .setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

    /**
     * 配置数据源
     * @return DataSourceConfig
     */
    private static DataSourceConfig initDataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl(URL)
                .setDriverName(DRIVER)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD);
    }

    /**
     * 全局配置
     * @return GlobalConfig
     */
    private static GlobalConfig initGlobalConfig(String author, String packageName) {
        GlobalConfig gc = new GlobalConfig();
        String tmp = PlusGenerator.class.getResource("").getPath();
        String codeDir = tmp.substring(0, tmp.indexOf("/target"));
        basePath = codeDir + "/src/main/java";
        mapperPath = basePath + "/" + packageName.replace(".", "/") + "/mapper";
//        System.out.println("basePath = " + basePath + "\nmapperPath = " + mapperPath);
        gc.setOutputDir(basePath);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setFileOverride(true);// 是否覆盖文件
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setOpen(false);//生成后打开文件夹
//        gc.setSwagger2(true);
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");// 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        //gc.setControllerName("%sController");
        return gc;
    }

    //策略配置项
    private static StrategyConfig initStrategyConfig(){
        return new StrategyConfig()
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
//                .setInclude("business_card_scan_record", "job_business_card", "professional_skill", "project_experience")
//                .setInclude("scan_record", "recruitment_info")
//                .setInclude("attachments")
                .setInclude("user_info")
                //Boolean类型字段是否移除is前缀
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setRestControllerStyle(true)
                .setEntityLombokModel(true);
    }

    //跟包相关的配置项
    private static PackageConfig initPackageConfig(String packageName){
        return new PackageConfig()
                // 自定义包路径
                .setParent(packageName)
                //.setController("controller")
                .setEntity("entity.po")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl");

    }

    //抽象的对外接口
    private static InjectionConfig initInjectionConfig(String packageName){
        return new InjectionConfig() {// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(32);
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
                "/templates/mapper.xml.ftl" ) {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {

                String tmp = PlusGenerator.class.getResource("").getPath();
                String codeDir = tmp.substring(0, tmp.indexOf("/target"));
                basePath = codeDir + "/src/main/resources/mapper/";

                // 自定义输入文件名称
                String s = basePath +tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return s;
            }
        }));
    }
}


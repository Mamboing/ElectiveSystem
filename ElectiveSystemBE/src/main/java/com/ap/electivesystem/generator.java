//package com.ap.electivesystem;
//
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.fill.Column;
//
//import java.util.Collections;
//
//public class generator {
//    public static void main(String[] args) {
//        FastAutoGenerator.create("jdbc:mysql://localhost:3306/elecsysserver?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true","root", "123456")
//                .globalConfig(builder -> {
//                    builder.outputDir(System.getProperty("user.dir") + "/src/main/java")
//                            .fileOverride()
//                            .enableSwagger()
//                            .disableOpenDir();
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com")
//                            .moduleName("ap.electivesystem")
//                            .entity("entity")
//                            .service("service")
//                            .serviceImpl("serviceImpl")
//                            .mapper("mapper")
//                            .xml("mapper")
//                            .controller("controller")
//                            .other("utils")
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper"));
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tb_admin", "tb_course", "tb_score", "tb_select", "tb_student", "tb_teacher")
//                            .addTablePrefix("tb_")
//                            .mapperBuilder()
//                            .superClass(BaseMapper.class)
//                            .formatMapperFileName("%sMapper")
//                            .enableMapperAnnotation()
//                            .formatXmlFileName("%sXml")
//                            .serviceBuilder()
//                            .formatServiceFileName("%sService")
//                            .formatServiceImplFileName("%sServiceImpl")
//                            .entityBuilder()
//                            .enableLombok()
//                            .logicDeleteColumnName("deleted")
//                            .naming(NamingStrategy.underline_to_camel)
//                            .columnNaming(NamingStrategy.underline_to_camel)
//                            .addTableFills(
//                                    new Column("create_time", FieldFill.INSERT),
//                                    new Column("modify_time", FieldFill.INSERT_UPDATE)
//                            )
//                            .enableTableFieldAnnotation()
//                            .controllerBuilder()
//                            .formatFileName("%sController")
//                            .enableRestStyle();
//                }).execute();
//    }
//}
